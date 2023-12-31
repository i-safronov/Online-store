package com.safronov_original_app_online_store.presentation.fragment.all_content.home_page

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.safronov_original_app_online_store.R
import com.safronov_original_app_online_store.core.extensions.logE
import com.safronov_original_app_online_store.core.extensions.thisClassName
import com.safronov_original_app_online_store.databinding.FragmentHomePageBinding
import com.safronov_original_app_online_store.domain.model.product.Product
import com.safronov_original_app_online_store.presentation.fragment.all_content.home_page.rcv.RcvAllProducts
import com.safronov_original_app_online_store.presentation.fragment.all_content.home_page.rcv.RcvAllProductsInt
import com.safronov_original_app_online_store.presentation.fragment.all_content.home_page.view_model.FragmentHomePageVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.product_details.FragmentProductDetails
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHomePage : Fragment(), RcvAllProductsInt {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val rcvAllProducts = RcvAllProducts(this)

    private val fragmentHomePageVM by viewModel<FragmentHomePageVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        try {
            showUserThatDataIsLoading()
            loadAllProducts()
            initRcv()
            initSearchView()
        } catch (e: Exception) {
            logE("${thisClassName()}, ${object{}.javaClass.enclosingMethod?.name}, -> ${e.message}")
        }
        return binding.root
    }

    private fun showUserThatDataIsLoading() {
        binding.rcvProducts.visibility = View.GONE
        binding.srchSearchProduct.visibility = View.GONE
        binding.btnGoToProductCategories.visibility = View.GONE
        binding.rcvProducts.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initSearchView() {
        binding.srchSearchProduct.inputType = InputType.TYPE_NULL
    }

    private fun initRcv() {
        binding.rcvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvProducts.adapter = rcvAllProducts
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            mainSwipeToRefreshListener()
            srchSearchProductListener()
            btnGoToProductCategoriesListener()
            allProductsListener()
            progressBarListener()
        } catch (e: Exception) {
            logE("${this.javaClass.name} -> ${object{}.javaClass.enclosingMethod?.name}, ${e.message}")
        }
    }

    private fun progressBarListener() {
        binding.progressBar.setOnClickListener {
            loadAllProducts()
        }
    }

    private fun allProductsListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            fragmentHomePageVM.allProducts.onEach { allProducts ->
                if (allProducts != null) {
                    rcvAllProducts.submitList(allProducts.products)
                    showUserThatDataLoaded()
                }
            }.collect()
        }
    }

    private fun showUserThatDataLoaded() {
        binding.rcvProducts.visibility = View.VISIBLE
        binding.srchSearchProduct.visibility = View.VISIBLE
        binding.btnGoToProductCategories.visibility = View.VISIBLE
        binding.rcvProducts.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun srchSearchProductListener() {
        binding.srchSearchProduct.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHomePage_to_fragmentOnlineProductSearch)
        }
    }

    private fun btnGoToProductCategoriesListener() {
        binding.btnGoToProductCategories.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentHomePage_to_fragmentProductCategory)
        }
    }

    private fun mainSwipeToRefreshListener() {
        binding.mainSwipeToRefresh.setOnRefreshListener {
            loadAllProducts()
            binding.mainSwipeToRefresh.isRefreshing = false
        }
    }
    
    override fun onProductClick(product: Product) {
        try {
            goToFragmentDetails(product)
        } catch (e: Exception) {
            logE("${thisClassName()} -> ${object{}.javaClass.enclosingMethod?.name}, ${e.message}")
        }
    }

    private fun goToFragmentDetails(product: Product) {
        fragmentHomePageVM.insertSelectedProduct(product = product)
        findNavController().navigate(
            R.id.action_fragmentHomePage_to_product_details_graph,
            bundleOf(
                FragmentProductDetails.PRODUCT_ID_TO_SHOW_PRODUCT_DETAILS to product.id
            )
        )
    }

    private fun loadAllProducts() {
        fragmentHomePageVM.loadAllProducts()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentHomePage()
    }

}