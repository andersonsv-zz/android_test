package br.com.andersonsv.test.feature.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.andersonsv.test.R
import br.com.andersonsv.test.adapter.HomeProductAdapter
import br.com.andersonsv.test.feature.main.ProductDetailActivity
import br.com.andersonsv.test.network.enjoei.EnjoeiAPI
import br.com.andersonsv.test.network.enjoei.ProductApi
import br.com.andersonsv.test.network.model.product.HomeProducts
import br.com.andersonsv.test.network.model.product.Product
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var apiClient: ProductApi
    lateinit var homeProductAdapter: HomeProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFirstPage()
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

        callHomeProductsApi().enqueue(object : Callback<HomeProducts> {
            override fun onResponse(call: Call<HomeProducts>, response: Response<HomeProducts>) {
                Log.d("TAG", response.body().toString())

                val products = response.body()?.products

                homeProductAdapter.results = products ?: mutableListOf()
                recyclerView.smoothScrollToPosition(products?.size ?: 0)
                homeProductAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<HomeProducts>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun productItemClicked(productItem : Product) {

        Toast.makeText(activity, "Clicked: ${productItem.title}", Toast.LENGTH_LONG).show()

        val showDetailActivityIntent = Intent(activity, ProductDetailActivity::class.java)
        showDetailActivityIntent.putExtra("product", productItem)
        startActivity(showDetailActivityIntent)
    }
}