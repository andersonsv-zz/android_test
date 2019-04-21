package br.com.andersonsv.test.network.enjoei

import br.com.andersonsv.test.network.model.product.HomeProducts
import br.com.andersonsv.test.network.model.product.Pagination
import br.com.andersonsv.test.network.model.product.Photo
import br.com.andersonsv.test.network.model.product.Product
import br.com.andersonsv.test.network.model.user.Avatar
import br.com.andersonsv.test.network.model.user.User
import br.com.andersonsv.test.util.TestUtil
import com.squareup.moshi.Moshi
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ProductApiTest {
    private val moshi = Moshi.Builder().build()
    private var results = String()


    @Before
    fun setUp() {
        results = TestUtil.loadTextFile("fixtures/product/list.json")
    }

    @Test
    fun `when load products check total and current pages`() {

        val paginationExpected = Pagination(currentPage = 1, totalPages = 2)
        val homeProductsExpected = HomeProducts(pagination = paginationExpected, products = null)

        val homeProducts = moshi.adapter(HomeProducts::class.java).fromJson(results)!!

        assertEquals(homeProducts.pagination.currentPage, homeProductsExpected.pagination.currentPage)
        assertEquals(homeProducts.pagination.totalPages, homeProductsExpected.pagination.totalPages)
    }

    @Test
    fun `when load products check product`() {

        val avatar = Avatar("", "", "")
        val user = User(1, "", avatar)
        val photos = mutableListOf<Photo>()

        val photo = Photo("", "", "")

        photos.add(0, photo)

        val productExpected = Product(
            id = 1,
            discountPorcentage = 0.0,
            title = "Tenis maneiro",
            price = 400.0,
            originalPrice = 400.0,
            size = "36",
            likesCount = 46,
            maximumInstallment = 10,
            publishedCommentsCount = 10,
            content = "",
            user = user,
            photos = photos)

        val products = mutableListOf<Product>()
        products.add(0, productExpected)

        val homeProducts = moshi.adapter(HomeProducts::class.java).fromJson(results)!!

        val productLoad = homeProducts.products!![0]

        assertEquals(productLoad.price, productExpected.price)
        assertEquals(productLoad.title, productExpected.title)
        assertEquals(productLoad.price, productExpected.price)
        assertEquals(productLoad.size, productExpected.size)
        assertEquals(productLoad.likesCount, productExpected.likesCount)
        assertEquals(productLoad.maximumInstallment, productExpected.maximumInstallment)
    }

    @Test
    fun `when load products check user`() {

        val avatar = Avatar("", "", "")
        val user = User(1, "Maria Fernanda Le Roux", avatar)
        val photos = mutableListOf<Photo>()
        val photo = Photo("", "", "")

        photos.add(0, photo)

        val productExpected = Product(
            id = 0,
            discountPorcentage = 0.0,
            title = "",
            price = 0.0,
            originalPrice = 0.0,
            size = "",
            likesCount = 0,
            maximumInstallment = 0,
            publishedCommentsCount = 0,
            content = "",
            user = user,
            photos = photos)

        val products = mutableListOf<Product>()
        products.add(0, productExpected)

        val homeProducts = moshi.adapter(HomeProducts::class.java).fromJson(results)!!
        val userLoad = homeProducts.products!![0].user

        assertEquals(userLoad.id, productExpected.user.id)
        assertEquals(userLoad.name, productExpected.user.name)
    }
}