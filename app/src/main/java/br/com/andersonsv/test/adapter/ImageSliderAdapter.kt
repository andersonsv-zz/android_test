package br.com.andersonsv.test.adapter

import android.view.View
import android.view.ViewGroup
import br.com.andersonsv.test.R
import br.com.andersonsv.test.util.ViewPagerAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ImageSliderAdapter(
    images: List<String>,
    binderFunction: (view: View, content: String) -> Unit
) : ViewPagerAdapter<String>(R.layout.item_product_image, images, binderFunction) {

    private val clickItem = PublishSubject.create<Int>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = super.instantiateItem(container, position) as View
        view.setOnClickListener { clickItem.onNext(position) }
        return view
    }

    fun clickItem(): Observable<Int> = clickItem.hide()

}