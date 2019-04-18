package br.com.andersonsv.test.feature.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.andersonsv.test.network.model.product.Product
import br.com.andersonsv.test.network.model.user.User

class ProductDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val product = intent.getParcelableExtra<Product>(INTENT_PRODUCT)
            ?: throw IllegalStateException("field $INTENT_PRODUCT missing in Intent")
    }

    companion object {

        private val INTENT_PRODUCT = "product"

        fun newIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(INTENT_PRODUCT, product)
            return intent
        }
    }
}