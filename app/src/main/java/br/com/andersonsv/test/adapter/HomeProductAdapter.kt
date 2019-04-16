package br.com.andersonsv.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andersonsv.test.R
import br.com.andersonsv.test.extension.asBRL
import br.com.andersonsv.test.extension.loadPrimaryPhotoImage
import br.com.andersonsv.test.network.model.product.Product
import kotlinx.android.synthetic.main.item_home_product.view.*

class HomeProductAdapter(private val context: Context, private var results: MutableList<Product>) :
RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(results[position])
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(product: Product) {

            itemView.imageViewProduct.loadPrimaryPhotoImage(product.photos)
            itemView.textViewTitle.text = product.title
            itemView.textViewPrice.text = product.price.asBRL(true)
        }
    }
}
