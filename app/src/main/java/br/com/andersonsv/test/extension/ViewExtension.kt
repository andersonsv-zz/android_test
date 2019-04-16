package br.com.andersonsv.test.extension

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.andersonsv.test.R
import br.com.andersonsv.test.network.model.product.Photo
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide.with
import java.text.MessageFormat

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadPrimaryPhotoImage(photos: MutableList<Photo>?) {
    photos?.get(0)?.let {
        val imageUrl = generateImageUrl(it.crop, it.gravity, it.publicId)

        with(this.context)
            .load(imageUrl)
            .apply(RequestOptions()
            .placeholder(startCircularProgress()))
            .into(this)
    }
}

inline fun <T:Any, R> whenNotNull(input: T?, callback: (T)->R): R? {
    return input?.let(callback)
}

fun ImageView.loadImage(imageUrl: String) {
    with(this.context)
        .load(imageUrl)
        .apply(RequestOptions().placeholder(startCircularProgress()))
        .into(this)
}


private fun generateImageUrl(crop: String, gravity: String, publicId: String): String {
    //TODO - constants
    val template = "http://res.cloudinary.com/demo/image/upload/c_{0},g_{1},w_150,h_200/{2}.jpg"
    return MessageFormat.format(template, crop, gravity, publicId)

}

private fun ImageView.startCircularProgress(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this.context)

    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}