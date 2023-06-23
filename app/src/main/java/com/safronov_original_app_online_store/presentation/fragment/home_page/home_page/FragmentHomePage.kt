package com.safronov_original_app_online_store.presentation.fragment.home_page.home_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.safronov_original_app_online_store.core.extensions.logE
import com.safronov_original_app_online_store.core.extensions.thisClassName
import com.safronov_original_app_online_store.databinding.FragmentHomePageBinding
import com.safronov_original_app_online_store.domain.model.product.Product
import com.safronov_original_app_online_store.presentation.fragment.home_page.home_page.rcv.RcvAllProducts
import com.safronov_original_app_online_store.presentation.fragment.home_page.home_page.rcv.RcvAllProductsInt
import com.safronov_original_app_online_store.presentation.fragment.home_page.home_page.view_model.FragmentHomePageVM
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentHomePage : Fragment(), RcvAllProductsInt {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val rcvAllProducts = RcvAllProducts(this)

    private val fragmentHomePageVM by viewModel<FragmentHomePageVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            fragmentHomePageVM.getAllProducts()
        } catch (e: Exception) {
            logE("${thisClassName()}, ${object{}.javaClass.enclosingMethod?.name}, ${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        try {
            initRcv()
            vmAllProductsListener()
        } catch (e: Exception) {
            logE("${thisClassName()}, ${object{}.javaClass.enclosingMethod?.name}, -> ${e.message}")
        }
        return binding.root
    }

    private fun initRcv() {
        binding.rcvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcvProducts.adapter = rcvAllProducts
    }

    private fun vmAllProductsListener() {
        viewLifecycleOwner.lifecycleScope.launch {
            fragmentHomePageVM.allProducts.onEach { allProducts ->
                if (allProducts != null) {
                    rcvAllProducts.submitList(allProducts.products)
                }
            }.collect()
        }
        try {
            throw Exception()
        } catch (e: Exception) {
            logE("${thisClassName()} -> ${object{}.javaClass.enclosingMethod?.name}, ${e.message}")
        }
    }

    override fun onProductClick(product: Product) {
        try {
            //TODO write logic for onProductClick method!
        } catch (e: Exception) {
            logE("${thisClassName()} -> ${object{}.javaClass.enclosingMethod?.name}, ${e.message}")
        }
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