package com.juliemenge.pittsburghrecycling

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.jetbrains.anko.*
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.Chronology

class MainActivity : AppCompatActivity() {

    val neighborhoods = arrayOf("Central/Eastern", "Northern/Southern") //neighborhood choices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var selectedRecyclingDates = arrayOf<String>()
        //list of recycling dates
        val recyclingDatesCentralEastern = arrayOf("11-27-2017", "12-11-2017", "12-26-2017")
        val recyclingDatesNorthernSouthern = arrayOf("11-20-2017", "12-04-2017", "12-18-2017")

        //get today's date
        val current = LocalDateTime.now() //current date
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy") //how you want the date to display
        val formattedCurrentDate = current.format(formatter) //current date displayed the way you want it

        //spinner to store which neighborhoods
        val aa: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, neighborhoods)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        neighborhoodSpinner.adapter = aa

        //base list of recycling dates based on chosen neighborhood
        neighborhoodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem = neighborhoodSpinner.selectedItem.toString()
                if (selectedItem == "Central/Eastern") {
                    toast("You picked Central/Eastern!")
                    selectedRecyclingDates = recyclingDatesCentralEastern
                    //check if today is recycling day
                    if (selectedRecyclingDates.contains(formattedCurrentDate)) {
                        recyclingAnswerTextView.text = "YES"
                    } else recyclingAnswerTextView.text = "NO"

                } else {
                    toast("You picked Northern/Southern!")
                    selectedRecyclingDates = recyclingDatesNorthernSouthern
                    //check if today is recycling day
                    if (selectedRecyclingDates.contains(formattedCurrentDate)) recyclingAnswerTextView.text = "YES" else recyclingAnswerTextView.text = "NO"
                }
            }
        }


        //TODO: figure out how to set the next recycling date without hard coding it in - compare today's date
        //TODO: to each item in the array, if today's date is greater than array date, compare today's date to next date, if next date in array is
        //TODO: greater than today's date, set that to be next recycling date

        //set the next recycling date
        val nextRecyclingDate = selectedRecyclingDates.firstOrNull { it > formattedCurrentDate }
        println(nextRecyclingDate)
        toast(nextRecyclingDate.toString())

//        fun dateCompare (dateOne: String, dateTwo: String) : Boolean {
//            if (dateOne > dateTwo) {
//                return true
//            } else return false
//        }
//
//        val answer = dateCompare("12-10-17", "12-08-17")

        nextRecyclingDayTextView.text = nextRecyclingDate //set the next recycling day text

    }
}
