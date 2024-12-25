package com.example.recordkeeper.cycling

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCyclingRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Edit ${intent.getStringExtra("Record")} Record"

        displayRecord()

        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        val cyclingPreferences = getSharedPreferences("cycling", Context.MODE_PRIVATE)

        cyclingPreferences.edit {
            remove("${intent.getStringExtra("Record")} record")
            remove("${intent.getStringExtra("Record")} date")
        }
    }

    private fun displayRecord() {
        val cyclingPreferences = getSharedPreferences("cycling", Context.MODE_PRIVATE)
        binding.editTextRecord.setText(cyclingPreferences.getString("${intent.getStringExtra("Record")} record", null))
        binding.editTextDate.setText(cyclingPreferences.getString("${intent.getStringExtra("Record")} date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        val distance = intent.getStringExtra("Record")

        val preferences = getSharedPreferences("cycling", Context.MODE_PRIVATE)

        preferences.edit {
            putString("$distance record", record)
            putString("$distance date", date)
        }

        finish()
    }
}