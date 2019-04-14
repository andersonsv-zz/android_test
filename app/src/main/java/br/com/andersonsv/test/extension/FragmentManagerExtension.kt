package br.com.andersonsv.test.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


inline fun FragmentManager.runTransaction(block: FragmentTransaction.() -> Unit) {
    with(beginTransaction()) {
        block()
        commit()
    }
}

fun FragmentManager.hideAndShow(
    fragmentToHide: Fragment,
    fragmentToShow: Fragment
) {
    runTransaction {
        hide(fragmentToHide)
        show(fragmentToShow)
    }
}

fun FragmentManager.addAndHide(
    @IdRes containerId: Int,
    tag: String,
    fragmentToAdd: Fragment,
    fragmentToHide: Fragment? = null
) {
    runTransaction {
        add(containerId, fragmentToAdd, tag)
        fragmentToHide?.let { hide(fragmentToHide) }
    }
}
