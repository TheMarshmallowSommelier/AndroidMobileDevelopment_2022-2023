package fr.epsi.androidmobiledevelopment_2022_2023

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.epsi.androidmobiledevelopment_2022_2023.BaseActivity
import fr.epsi.androidmobiledevelopment_2022_2023.R
import okhttp3.*
import java.io.IOException


class ProductsActivity : BaseActivity() {

    private lateinit var productsList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        showBack()
        setHeaderTxt("Produits")

        productsList = findViewById(R.id.products_list)

        val categoryJson = intent.getStringExtra("category")
        val category = Gson().fromJson<Category>(categoryJson, Category::class.java)
        loadProducts(category)
    }

    private fun loadProducts(category: Category) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(category.products_url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val productsType = object : TypeToken<ProductsModel>() {}.type
                val productsModel = Gson().fromJson<ProductsModel>(responseBody, productsType)

                runOnUiThread {
                    val products = productsModel.items.map { it.name }
                    val adapter = ArrayAdapter(
                        this@ProductsActivity,
                        android.R.layout.simple_list_item_1,
                        products
                    )
                    productsList.adapter = adapter
                }
            }
        })
    }
}

data class ProductsModel(val items: List<Product>)
data class Product(val name: String, val description: String, val picture_url: String)
