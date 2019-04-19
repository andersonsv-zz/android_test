package br.com.andersonsv.test.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.andersonsv.test.R
import br.com.andersonsv.test.extension.loadPrimaryPhotoImage
import br.com.andersonsv.test.network.model.product.Product
import kotlinx.android.synthetic.main.activity_product_detail.*
import android.view.Menu





class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

       val product = intent.getParcelableExtra<Product>(INTENT_PRODUCT)
            ?: throw IllegalStateException("field $INTENT_PRODUCT missing in Intent")

        imageViewBackdropProduct.loadPrimaryPhotoImage(product.photos)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return true
    }

    companion object {
        //TODO - constants
        private val INTENT_PRODUCT = "product"

        fun newIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(INTENT_PRODUCT, product)
            return intent
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}