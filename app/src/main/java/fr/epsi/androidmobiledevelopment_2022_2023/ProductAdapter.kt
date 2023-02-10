package fr.epsi.androidmobiledevelopment_2022_2023

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductAdapter(context: Context, private val products: List<Product>)
    : ArrayAdapter<Product>(context, R.layout.product_item, products) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.product_item, parent, false)
        val product = products[position]

        val productImage = view.findViewById<ImageView>(R.id.product_image)
        Picasso.get().load(product.picture_url).into(productImage)

        val productName = view.findViewById<TextView>(R.id.product_name)
        productName.text = product.name

        val productDescription = view.findViewById<TextView>(R.id.product_description)
        productDescription.text = product.description

        return view
    }
}
