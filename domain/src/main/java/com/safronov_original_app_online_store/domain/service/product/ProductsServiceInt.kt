package com.safronov_original_app_online_store.domain.service.product

import com.safronov_original_app_online_store.domain.model.product.AllProducts
import com.safronov_original_app_online_store.domain.model.product.SelectedProduct
import kotlinx.coroutines.flow.Flow

interface ProductsServiceInt {

    suspend fun getAllProducts(): AllProducts?
    suspend fun getAllSelectedProducts(): Flow<List<SelectedProduct>>
    suspend fun getSelectedProductById(productId: String): SelectedProduct
    suspend fun getSelectedProductsByTitle(productTitle: String): List<SelectedProduct>
    suspend fun insertSelectedProduct(selectedProduct: SelectedProduct)
    suspend fun deleteSelectedProduct(selectedProduct: SelectedProduct)

}