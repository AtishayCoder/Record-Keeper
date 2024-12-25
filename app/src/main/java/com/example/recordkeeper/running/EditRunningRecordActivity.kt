package com.example.recordkeeper.running

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditRunningRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRunningRecordBinding
    private var defaultDate = MaterialDatePicker.todayInUtcMilliseconds()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Edit ${intent.getStringExtra("Distance")} Record"

        displayRecord()

        binding.editTextDate.isFocusable = false
        binding.editTextDate.isClickable = true

        binding.textInputDate.setOnClickListener {
            showDatePicker()
        }

        binding.editTextDate.setOnClickListener {
            showDatePicker()
        }

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun showDatePicker() {
        println("Showing picker.")
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(defaultDate)
            .build()
        datePicker.show(supportFragmentManager, "DATE_PICKER")

        datePicker.addOnPositiveButtonClickListener { selection ->
            val date = Date(selection)
            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
            defaultDate = selection
            binding.editTextDate.setText(formattedDate)
        }
    }

    private fun clearRecord() {
        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)

        runningPreferences.edit {
            remove("${intent.getStringExtra("Distance")} record")
            remove("${intent.getStringExtra("Distance")} date")
        }
    }

    private fun displayRecord() {
        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)
        binding.editTextRecord.setText(
            runningPreferences.getString(
                "${intent.getStringExtra("Distance")} record",
                null
            )
        )
        binding.editTextDate.setText(
            runningPreferences.getString(
                "${intent.getStringExtra("Distance")} date",
                null
            )
        )
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        val distance = intent.getStringExtra("Distance")

        val preferences = getSharedPreferences("running", Context.MODE_PRIVATE)

        preferences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }

        finish()
    }
}