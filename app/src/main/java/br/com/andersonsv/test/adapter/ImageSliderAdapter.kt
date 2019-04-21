package br.com.andersonsv.test.adapter

import android.view.View
import android.view.ViewGroup
import br.com.andersonsv.test.R
import br.com.andersonsv.test.util.ViewPagerAdapter

class ImageSliderAdapter(
    images: List<String>,
    binderFunction: (view: View, content: String) -> Unit
) : ViewPagerAdapter<String>(R.layout.item_product_image, images, binderFunction) {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position) as View
    }

}