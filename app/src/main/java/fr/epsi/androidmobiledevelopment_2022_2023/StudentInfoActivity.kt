package fr.epsi.androidmobiledevelopment_2022_2023

import android.os.Bundle

class StudentInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)
        showBack()
        setHeaderTxt("Joey FRIMIN")
    }
}