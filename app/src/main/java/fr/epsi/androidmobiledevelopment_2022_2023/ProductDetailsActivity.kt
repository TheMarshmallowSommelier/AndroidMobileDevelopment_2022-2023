package fr.epsi.androidmobiledevelopment_2022_2023

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class ProductDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        showBack()

        val productJson = intent.getStringExtra("product")
        val product = Gson().fromJson(productJson, Product::class.java)
        setHeaderTxt(product.name)

        val productImage = findViewById<ImageView>(R.id.product_image)
        val productDescription = findViewById<TextView>(R.id.product_description)

        Picasso.get().load(product.picture_url).into(productImage)
        productDescription.text = product.description

    }
}