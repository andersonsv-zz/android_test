package br.com.andersonsv.test.extension

import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.andersonsv.test.network.model.product.Photo
import br.com.andersonsv.test.network.model.user.User
import br.com.andersonsv.test.util.Constants
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.text.MessageFormat

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun ImageView.loadPrimaryPhotoImage(photos: MutableList<Photo>?) {
    photos?.get(0)?.let {
        val imageUrl = generateImageUrl(it.crop, it.gravity, it.publicId)

        val px = Math.round(16 * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))

        val multi = MultiTransformation<Bitmap>(
            RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.TOP_LEFT),
                    RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT))

        with(this.context)
            .load(imageUrl)
            .apply(RequestOptions()
                .placeholder(startCircularProgress()
            ))
            .apply(bitmapTransform(multi))
            .into(this)
    }
}


fun ImageView.loadAvatar(user: User) {
    val imageUrl = generateImageUrl(user.avatar.crop, user.avatar.gravity, user.avatar.publicId)


    with(this.context)
        .load(imageUrl)
        .apply(RequestOptions()
            .placeholder(startCircularProgress()
            ))
        .apply(bitmapTransform(CircleCrop()))
        .into(this)


}

fun ImageView.loadImage(imageUrl: String) {
    with(this.context)
        .load(imageUrl)
        .apply(RequestOptions().placeholder(startCircularProgress()))
        .into(this)
}


private fun generateImageUrl(crop: String, gravity: String, publicId: String): String {
    return MessageFormat.format(Constants.TEMPLATE_IMAGE_URL, crop, gravity, publicId)
}

fun Photo.generateImageUrl(): String {
    return MessageFormat.format(Constants.TEMPLATE_IMAGE_URL, this.crop, this.gravity, this.publicId)
}

private fun ImageView.startCircularProgress(): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(this.context)

    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}