package com.safronov_original_app_online_store.presentation.fragment.home_page.product_cart.product_purchase.rcv

import com.safronov_original_app_online_store.domain.model.bank_card.BankCard

interface RcvBankCardsInt {

    fun onBankCardClick(bankCard: BankCard)

}