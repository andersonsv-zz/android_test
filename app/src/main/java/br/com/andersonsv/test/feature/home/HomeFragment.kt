package br.com.andersonsv.test.feature.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.andersonsv.test.adapter.HomeProductAdapter
import br.com.andersonsv.test.extension.isNetworkConnected
import br.com.andersonsv.test.extension.makeGone
import br.com.andersonsv.test.extension.makeVisible
import br.com.andersonsv.test.feature.main.ConnectionErrorActivity
import br.com.andersonsv.test.feature.main.ProductDetailActivity
import br.com.andersonsv.test.network.enjoei.EnjoeiAPI
import br.com.andersonsv.test.network.model.product.HomeProducts
import br.com.andersonsv.test.network.model.product.Product
import br.com.andersonsv.test.util.Constants
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var homeProductAdapter: HomeProductAdapter

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(
                ConnectivityManager
                    .EXTRA_NO_CONNECTIVITY, false)
            if (notConnected) {
                disconnected()
            } else {
                connected()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(broadcastReceiver)
    }

    private fun disconnected() {
        val showErrorConnectIntent = Intent(activity, ConnectionErrorActivity::class.java)
        startActivity(showErrorConnectIntent)
    }

    private fun connected() {
        loadFirstPage()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(br.com.andersonsv.test.R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(context!!.isNetworkConnected()){
            loadFirstPage()
        } else {
            disconnected()
        }
    }

    companion object {
        const val TAG = "home"
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private fun callHomeProductsApi(): Call<HomeProducts> {
        return EnjoeiAPI.productApi.getHomeProducts(1)
    }

    private fun loadFirstPage() {

        homeProductAdapter = HomeProductAdapter(mutableListOf(), { productItem : Product -> productItemClicked(productItem) })
        recyclerView.adapter = homeProductAdapter
        val mLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.setHasFixedSize(true)

        progressBar.makeVisible()

        callHomeProductsApi().enqueue(object : Callback<HomeProducts> {
            override fun onResponse(call: Call<HomeProducts>, response: Response<HomeProducts>) {

                val products = response.body()?.products

                homeProductAdapter.results = products ?: mutableListOf()
                recyclerView.smoothScrollToPosition(0)
                homeProductAdapter.notifyDataSetChanged()

                progressBar.makeGone()
            }

            override fun onFailure(call: Call<HomeProducts>, t: Throwable) {
                t.printStackTrace()
                progressBar.makeGone()
            }
        })
    }

    private fun productItemClicked(productItem : Product) {
        val showDetailActivityIntent = Intent(activity, ProductDetailActivity::class.java)
        showDetailActivityIntent.putExtra(Constants.INTENT_PRODUCT, productItem)
        startActivity(showDetailActivityIntent)
    }
}