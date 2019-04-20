package br.com.andersonsv.test.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andersonsv.test.R
import br.com.andersonsv.test.extension.*
import br.com.andersonsv.test.network.model.product.Product
import kotlinx.android.synthetic.main.item_home_product.view.*
import java.text.DecimalFormat

class HomeProductAdapter(var results: MutableList<Product>, private val clickListener: (Product) -> Unit) :
RecyclerView.Adapter<HomeProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(results[position], clickListener)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(product: Product,  clickListener: (Product) -> Unit) {

            itemView.setOnClickListener { clickListener(product)}

            itemView.imageViewProduct.loadPrimaryPhotoImage(product.photos)
            itemView.textViewTitle.text = product.title
            itemView.textViewOriginalPrice.text = product.price.asBRLNoFraction(true)

            if (product.discountPorcentage != 0.0) {

                val df = DecimalFormat("#.###")
                val discount = "-" + df.format(product.discountPorcentage) + "%"

                itemView.textViewDiscountPorcentage.makeVisible()
                itemView.textViewDiscountPorcentage.text = discount
            }

            if(!product.size.isNullOrBlank()){
                itemView.textViewSize.text =  " - " + itemView.context.getString(R.string.commom_size) + " " + product.size
                itemView.textViewSize.makeVisible()
                itemView.textViewSize.gravity = Gravity.LEFT
                itemView.textViewOriginalPrice.gravity = Gravity.RIGHT
            }

            itemView.imageViewAvatar.loadAvatar(product.user)
            itemView.textViewYeahs.text = product.likesCount.toString()
        }
    }
}