package com.example.recordkeeper.running

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding

class EditRunningRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRunningRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Edit ${intent.getStringExtra("Distance")} Record"

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
        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)

        runningPreferences.edit {
            remove("${intent.getStringExtra("Distance")} record")
            remove("${intent.getStringExtra("Distance")} date")
        }
    }

    private fun displayRecord() {
        val runningPreferences = getSharedPreferences("running", Context.MODE_PRIVATE)
        binding.editTextRecord.setText(runningPreferences.getString("${intent.getStringExtra("Distance")} record", null))
        binding.editTextDate.setText(runningPreferences.getString("${intent.getStringExtra("Distance")} date", null))
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