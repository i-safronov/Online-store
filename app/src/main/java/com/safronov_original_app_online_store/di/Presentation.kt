package com.safronov_original_app_online_store.di

import com.safronov_original_app_online_store.presentation.fragment.all_content.home_page.view_model.FragmentHomePageVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.online_search_product.view_model.FragmentOnlineProductSearchVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.product_cart.product_purchase.bank_card.view_model.FragmentAddUserBankCardVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.product_cart.product_purchase.view_model.FragmentProductPurchaseVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.product_cart.view_model.FragmentProductCartHomePageVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.product_category.view_model.FragmentProductCategoryVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.product_details.view_model.FragmentProductDetailsVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.selected_product_history.view_model.FragmentSelectedProductHistoryViewModel
import com.safronov_original_app_online_store.presentation.fragment.all_content.sell_product.add_product_photo.view_model.FragmentAddProductPhotoVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.sell_product.view_model.FragmentSellProductVM
import com.safronov_original_app_online_store.presentation.fragment.all_content.user_profile.user_bank_cards.view_model.FragmentUserBankCardsVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationDi = module {

    viewModel<FragmentHomePageVM> {
        FragmentHomePageVM(
            productsServiceInt = get(),
            productConverter = get(),
            productCategoriesServiceInt = get()
        )
    }
    
    viewModel<FragmentProductDetailsVM> {
        FragmentProductDetailsVM(
            productsServiceInt = get(),
            cartServiceInt = get()
        )
    }

    viewModel<FragmentProductCategoryVM> {
        FragmentProductCategoryVM(
            productsServiceInt = get(),
            productCategoriesServiceInt = get()
        )
    }

    viewModel<FragmentOnlineProductSearchVM> {
        FragmentOnlineProductSearchVM(
            productsServiceInt = get(),
            productConverter = get()
        )
    }

    viewModel<FragmentSelectedProductHistoryViewModel> {
        FragmentSelectedProductHistoryViewModel(
            productsServiceInt = get()
        )
    }

    viewModel<FragmentSellProductVM> {
        FragmentSellProductVM()
    }

    viewModel<FragmentAddProductPhotoVM> {
        FragmentAddProductPhotoVM()
    }

    viewModel<FragmentProductCartHomePageVM> {
        FragmentProductCartHomePageVM(
            cartServiceInt = get()
        )
    }

    viewModel<FragmentProductPurchaseVM> {
        FragmentProductPurchaseVM(
            bankCardServiceInt = get(),
            productsServiceInt = get(),
            cartProductConverter = get(),
            cartServiceInt = get()
        )
    }

    viewModel<FragmentAddUserBankCardVM> {
        FragmentAddUserBankCardVM(
            bankCardsServiceInt = get()
        )
    }

    viewModel<FragmentUserBankCardsVM> {
        FragmentUserBankCardsVM(
            bankCardsServiceInt = get()
        )
    }

}