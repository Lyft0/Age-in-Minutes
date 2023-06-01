package com.example.ageminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var liveMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.buttonDate)
        selectedDate = findViewById(R.id.selectedDate)
        liveMinutes = findViewById(R.id.ageMinute)

        selectedDate?.text = "-"
        liveMinutes?.text = "-"

        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }
    }

    fun clickDatePicker(){

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{ view, selectYear, selectMonth, selectDayOfMonth ->
                //Toast.makeText(this,"Year $selectYear",Toast.LENGTH_LONG).show()

                val selectDate = "$selectDayOfMonth/${selectMonth+1}/$selectYear"
                selectedDate?.text = selectDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectDate)
                val dateInMinutes = theDate.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateMinutes = currentDate.time / 60000

                val diffMinutes = currentDateMinutes - dateInMinutes

                liveMinutes?.text = diffMinutes.toString()
            },
            year,
            month,
            day
        ).show()
    }


}