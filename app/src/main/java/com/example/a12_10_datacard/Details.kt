package com.example.a12_10_datacard

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period

class Details : BaseActivity() {

    private lateinit var photo: ImageView
    private lateinit var firstName: EditText
    private lateinit var secondName: EditText
    private lateinit var date: EditText
    private lateinit var dateInfo: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupWindowInsets(R.id.details)
        setupToolbar(R.id.toolbar)

        // Настройка Action Bar с кнопкой "Назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firstName = findViewById(R.id.firstName)
        secondName = findViewById(R.id.secondName)
        date = findViewById(R.id.date)
        dateInfo = findViewById(R.id.dateInfo)
        photo = findViewById(R.id.photoIV)

        val person = intent.getSerializableExtra("person") as? Person
        if (person != null) {
            firstName.setText(person.firstName)
            secondName.setText(person.secondName)
            val (monthCount, dayCount) = bDayCount(person.date)
            dateInfo.setText("День рождения через $monthCount мес. $dayCount дн.")
            photo.setImageURI(Uri.parse(person.image))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bDayCount(bDay: LocalDate): Pair<String, String> {
        val period = Period.between(bDay, LocalDate.now())
        date.setText(period.years.toString())
        var nextBDay =
            LocalDate.of(LocalDate.now().year, bDay.month, bDay.dayOfMonth)
        if (period.years + bDay.year == LocalDate.now().year) nextBDay =
            LocalDate.of(LocalDate.now().year + 1, bDay.month, bDay.dayOfMonth)
        val periodBday = Period.between(LocalDate.now(), nextBDay)
        val monthCount = periodBday.months.toString()
        val dayCount = periodBday.days.toString()
        return Pair(monthCount, dayCount)
    }
}
