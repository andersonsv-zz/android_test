package br.com.andersonsv.test.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.andersonsv.test.R
import br.com.andersonsv.test.extension.*
import br.com.andersonsv.test.network.model.product.Product
import com.google.android.material.resources.TextAppearance
import kotlinx.android.synthetic.main.item_home_product.view.*
import java.text.DecimalFormat

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
                itemView.textViewPrice.gravity = Gravity.RIGHT
            }

            itemView.imageViewAvatar.loadAvatar(product.user)
            itemView.textViewYeahs.text = product.likesCount.toString()
        }
    }
}