package br.com.andersonsv.test.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.andersonsv.test.R
import br.com.andersonsv.test.feature.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mHomeFragment = HomeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayoutMain, mHomeFragment, mHomeFragment.tag)
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
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

}
