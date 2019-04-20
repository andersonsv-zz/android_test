package br.com.andersonsv.test.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.PagerAdapter

open class ViewPagerAdapter<T>(
    @LayoutRes private val layoutResource: Int,
    private val contentList: List<T>,
    private val binderFunction: (view: View, content: T) -> Unit
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater
            .from(container.context)
            .inflate(
                layoutResource,
                container,
                false
            )
        binderFunction(view, contentList[position])

        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = contentList.size

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) =
        container.removeView(view as View)
}