package com.example.a12_10_datacard

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.time.LocalDate

class MainActivity : BaseActivity() {

    private val galleryRequest = 1
    private var photoUri: Uri? = null
    private lateinit var photo: ImageView
    private lateinit var firstName: EditText
    private lateinit var secondName: EditText
    private lateinit var dateET: EditText
    private lateinit var buttonSave: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWindowInsets(R.id.details)
        setupToolbar(R.id.toolbar)

        firstName = findViewById(R.id.firstName)
        secondName = findViewById(R.id.secondName)
        dateET = findViewById(R.id.date)
        photo = findViewById(R.id.photoIV)

        photo.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, galleryRequest)
        }

        buttonSave = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val dateList = dateET.text.split(".")
            val date = LocalDate.of(dateList[2].trim().toInt(), dateList[1].trim().toInt(), dateList[0].trim().toInt())
            val person = Person(
                firstName.text.toString(),
                secondName.text.toString(),
                date,
                photoUri.toString()
            )
            val intent = Intent(this, Details::class.java)
            intent.putExtra("person", person)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == galleryRequest) {
            photoUri = data?.data
            photo.setImageURI(photoUri)
        }
    }
}
