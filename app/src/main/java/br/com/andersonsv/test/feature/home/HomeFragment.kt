package br.com.andersonsv.test.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.andersonsv.test.R
import br.com.andersonsv.test.network.enjoei.EnjoeiAPI
import br.com.andersonsv.test.network.enjoei.ProductApi
import br.com.andersonsv.test.network.model.product.HomeProducts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var apiClient: ProductApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        loadFirstPage()
    }

    companion object {
        const val TAG = "home"
        fun newInstance(): HomeFragment {
            val homeFragment = HomeFragment()
            return homeFragment
        }
    }

    fun initialize() {
        apiClient = EnjoeiAPI.client.create(ProductApi::class.java)

    }

    private fun callHomeProductsApi(): Call<HomeProducts> {
        return apiClient.getHomeProducts(1)
    }

    private fun loadFirstPage() {

        //hideErrorView()

        callHomeProductsApi().enqueue(object : Callback<HomeProducts> {
            override fun onResponse(call: Call<HomeProducts>, response: Response<HomeProducts>) {
                // Got data. Send it to adapter

                Log.d("TAG", response.body().toString())
                //hideErrorView()

               /* val results = fetchResults(response)
                var totalpages = fetchTotalPages(response)
                TOTAL_PAGES = totalpages
                Log.d(TAG, "results: $results")
                Log.d(TAG, "totalpages: $totalpages")
                main_progress.setVisibility(View.GONE)
                moviesadapter.addAll(results)

                if (currentPage <= TOTAL_PAGES)
                    moviesadapter.addLoadingFooter()
                else
                    isLastPage = true*/
            }

            override fun onFailure(call: Call<HomeProducts>, t: Throwable) {
                t.printStackTrace()
               // showErrorView(t)
            }
        })
    }


}