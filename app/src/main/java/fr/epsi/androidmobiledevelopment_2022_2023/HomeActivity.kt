package fr.epsi.androidmobiledevelopment_2022_2023

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_home`)
        val buttonInfo = findViewById<Button>(R.id.buttonInfo)
        val buttonProduits = findViewById<Button>(R.id.buttonProduits)
        setHeaderTxt("Accueil")



        buttonInfo.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, StudentInfoActivity::class.java)
            startActivity(intent)
        })

        buttonProduits.setOnClickListener(View.OnClickListener {
            val intent = Intent(application, CategoriesActivity::class.java)
            startActivity(intent)
        })
    }
}