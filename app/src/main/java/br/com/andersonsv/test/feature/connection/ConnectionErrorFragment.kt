package br.com.andersonsv.test.feature.connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.andersonsv.test.R
import br.com.andersonsv.test.feature.home.HomeFragment
import kotlinx.android.synthetic.main.activity_connection_error.*


class ConnectionErrorFragment : Fragment() {
    companion object {
        fun newInstance(): ConnectionErrorFragment {
            return ConnectionErrorFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.activity_connection_error, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonErrorTryAgain.setOnClickListener {
            val homeFragment = HomeFragment.newInstance()

            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayoutMain, homeFragment, homeFragment.tag)
                .commit()
        }
    }
}