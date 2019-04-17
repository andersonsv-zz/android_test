package br.com.andersonsv.test.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.andersonsv.test.R
import br.com.andersonsv.test.extension.addAndHide
import br.com.andersonsv.test.extension.hideAndShow
import br.com.andersonsv.test.feature.home.HomeFragment
import br.com.andersonsv.test.network.enjoei.ProductApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setUpFragments()

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_camera -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mail -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setUpFragments() {
        supportFragmentManager.addAndHide(
            R.id.frameLayoutMain,
            HomeFragment.TAG,
            homeFragment,
            homeFragment
        )
    }

    private fun showFragment(fragment: Fragment) {
        val activeFragment = supportFragmentManager.findFragmentByTag(fragment.tag)
        supportFragmentManager.hideAndShow(activeFragment!!, fragment)
    }
}
