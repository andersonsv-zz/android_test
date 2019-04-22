package br.com.andersonsv.test.feature.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingResource
import br.com.andersonsv.test.R
import br.com.andersonsv.test.adapter.HomeProductAdapter
import br.com.andersonsv.test.extension.NoConnectivityException
import br.com.andersonsv.test.extension.makeGone
import br.com.andersonsv.test.extension.makeVisible
import br.com.andersonsv.test.feature.connection.ConnectionErrorFragment
import br.com.andersonsv.test.feature.main.ProductDetailActivity
import br.com.andersonsv.test.network.enjoei.EnjoeiAPI
import br.com.andersonsv.test.network.model.product.HomeProducts
import br.com.andersonsv.test.network.model.product.Product
import br.com.andersonsv.test.util.Constants
import br.com.andersonsv.test.util.SimpleIdlingResource
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    lateinit var mHomeProductAdapter: HomeProductAdapter

    private var mIdlingResource: SimpleIdlingResource? = null

    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        if (mIdlingResource == null) {
            mIdlingResource = SimpleIdlingResource()
        }
        return mIdlingResource as SimpleIdlingResource
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getIdlingResource()

        actionsSetup()
        loadFirstPage()
    }

    private fun actionsSetup(){
        homeSwipeToRefresh.setOnRefreshListener {
            loadFirstPage(true)
        }
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private fun callHomeProductsApi(): Call<HomeProducts> {
        return EnjoeiAPI(context!!).productApi.getHomeProducts(1)
    }

    private fun loadFirstPage(isSwipe: Boolean = false) {
        try {
            mIdlingResource?.setIdleState(false)

            mHomeProductAdapter = HomeProductAdapter(mutableListOf()) { productItem : Product -> productItemClicked(productItem) }
            recyclerView.adapter = mHomeProductAdapter
            val mLayoutManager = GridLayoutManager(context, 2)
            recyclerView.layoutManager = mLayoutManager
            recyclerView.setHasFixedSize(true)

            progressBar.makeVisible()

            callHomeProductsApi().enqueue(object : Callback<HomeProducts> {
                override fun onResponse(call: Call<HomeProducts>, response: Response<HomeProducts>) {

                    val products = response.body()?.products

                    mHomeProductAdapter.results = products ?: mutableListOf()
                    recyclerView.smoothScrollToPosition(0)
                    mHomeProductAdapter.notifyDataSetChanged()

                    progressBar.makeGone()

                    if(isSwipe){
                        homeSwipeToRefresh.isRefreshing = false
                    }

                    mIdlingResource?.setIdleState(true)
                }

                override fun onFailure(call: Call<HomeProducts>, t: Throwable) {
                    t.printStackTrace()
                    progressBar.makeGone()

                    if(isSwipe){
                        homeSwipeToRefresh.isRefreshing = false
                    }

                    mIdlingResource?.setIdleState(true)
                }
            })
        } catch (e: NoConnectivityException) {
            disconnected()
        }
    }

    private fun productItemClicked(productItem : Product) {
        val showDetailActivityIntent = Intent(activity, ProductDetailActivity::class.java)
        showDetailActivityIntent.putExtra(Constants.INTENT_PRODUCT, productItem)
        startActivity(showDetailActivityIntent)
    }

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
        val connectionErrorFragment = ConnectionErrorFragment.newInstance()

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutMain, connectionErrorFragment, connectionErrorFragment.tag)
            .commit()
    }

    private fun connected() {
        loadFirstPage()
    }
}