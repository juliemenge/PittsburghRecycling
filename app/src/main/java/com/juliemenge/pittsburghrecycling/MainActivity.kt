package com.juliemenge.pittsburghrecycling

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    val neighborhoods = arrayOf("Central/Eastern", "Northern/Southern") //neighborhood choices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //list of recycling dates
        val recyclingDatesCentralEastern = arrayOf("11-27-2017", "12-11-2017", "12-26-2017")
        val recyclingDatesNorthernSouthern = arrayOf("11-20-2017", "12-04-2017", "12-18-2017")
        //array to store dates based on chosen neighborhood
        var selectedRecyclingDates = arrayOf<String>()

        //get today's date
        val current = LocalDateTime.now() //current date
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy") //how you want the date to display
        val formattedCurrentDate = current.format(formatter) //current date displayed the way you want it

        //spinner to select neighborhood
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

                    selectedRecyclingDates = recyclingDatesCentralEastern //set correct list of recycling dates

                    //find and set next recycling date
                    //TODO: if there is no next date - aka, it is null, display "see ya next year" or something
                    val nextRecyclingDate = selectedRecyclingDates.firstOrNull { it > formattedCurrentDate }

                    if (nextRecyclingDate == null) {
                        nextRecyclingDayTextView.visibility = View.INVISIBLE
                        nextRecyclingDayLabel.visibility = View.INVISIBLE
                    } else nextRecyclingDayTextView.text = nextRecyclingDate //set the next recycling day text

                    //check if today is recycling day
                    if (selectedRecyclingDates.contains(formattedCurrentDate)) {
                        recyclingAnswerTextView.text = "YES"
                    } else recyclingAnswerTextView.text = "NO"

                } else {

                    selectedRecyclingDates = recyclingDatesNorthernSouthern //set correct list of recycling dates

                    //find and set next recycling date
                    val nextRecyclingDate = selectedRecyclingDates.firstOrNull { it > formattedCurrentDate }

                    if (nextRecyclingDate == null) {
                        nextRecyclingDayTextView.visibility = View.INVISIBLE
                        nextRecyclingDayLabel.visibility = View.INVISIBLE
                    } else nextRecyclingDayTextView.text = nextRecyclingDate //set the next recycling day text

                    //check if today is recycling day
                    if (selectedRecyclingDates.contains(formattedCurrentDate)) recyclingAnswerTextView.text = "YES" else recyclingAnswerTextView.text = "NO"
                }
            }
        }

    }
}
