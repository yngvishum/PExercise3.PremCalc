package com.example.pexercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val selectedItem = spinnerAge.getItemAtPosition(position)
        Toast.makeText(this, "Selected Item =$selectedItem", Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

        /*val buttonThird: Button
        buttonThrid.setOnclickListener(this)
         */
    }

    private fun calculatePremium(){
        //Get the age group

        val age: Int = spinnerAge.selectedItemPosition

        var premium = when(age){
            0 -> 60
            1 -> 70
            2 -> 90
            3 -> 120
            4 -> 150
            else -> 150
        }

        //Get the gender selection
        val gender: Int = radioGroupGender.checkedRadioButtonId
        if(gender == R.id.radioButtonMale){
            premium += when(age){
                0 -> 0
                1 -> 50
                2 -> 100
                3 -> 150
                4 -> 200
                else -> 200
            }

        }

        //Determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked

        if(smoker){
            premium += when(age){
                0 -> 0
                1 -> 100
                2 -> 150
                3 -> 200
                4 -> 250
                else -> 300
            }
        }

        val symbol = Currency.getInstance(Locale.getDefault()).symbol
        textViewPremium.text = String.format("%s %d", symbol, premium)
    }

    fun reset(view:View){
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        textViewPremium.text = getString(R.string.insurance_premium)
        checkBoxSmoker.isChecked = false


    }
}
