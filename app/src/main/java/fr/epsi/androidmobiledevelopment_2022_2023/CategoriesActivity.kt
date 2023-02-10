package fr.epsi.androidmobiledevelopment_2022_2023

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.epsi.androidmobiledevelopment_2022_2023.BaseActivity
import fr.epsi.androidmobiledevelopment_2022_2023.R
import okhttp3.*
import java.io.IOException

class CategoriesActivity : BaseActivity() {

    private lateinit var categoriesList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        showBack()
        setHeaderTxt("Rayons")

        categoriesList = findViewById(R.id.categories_list)
        loadCategories()
    }

    private fun loadCategories() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.ugarit.online/epsi/categories.json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val categoriesType = object : TypeToken<CategoriesModel>() {}.type
                val categoriesModel = Gson().fromJson<CategoriesModel>(responseBody, categoriesType)
                categoriesList.setOnItemClickListener { parent, view, position, id ->
                    val category = categoriesModel.items[position]
                    val intent = Intent(this@CategoriesActivity, ProductsActivity::class.java)
                    val categoryJson = Gson().toJson(category)
                    intent.putExtra("category", categoryJson)
                    startActivity(intent)

                }

                runOnUiThread {
                    val categories = categoriesModel.items.map { it.title }
                    val adapter = ArrayAdapter(
                        this@CategoriesActivity,
                        android.R.layout.simple_list_item_1,
                        categories
                    )
                    categoriesList.adapter = adapter
                }
            }
        })
    }
}

data class CategoriesModel(val items: List<Category>)
data class Category(val category_id: String, val title: String, val products_url: String)
