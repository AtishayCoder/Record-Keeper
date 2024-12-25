package com.example.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {
    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPreferences.getString("5km record", "Not set")
        binding.textView10kmValue.text = runningPreferences.getString("10km record", "Not set")
        binding.textViewHalfMarathonValue.text = runningPreferences.getString("Half Marathon record", "Not set")
        binding.textViewMarathonValue.text = runningPreferences.getString("Marathon record", "Not set")
        binding.textView5kmDate.text = runningPreferences.getString("5km date", null)
        binding.textView10kmDate.text = runningPreferences.getString("10km date", null)
        binding.textViewHalfMarathonDate.text = runningPreferences.getString("Half Marathon date", null)
        binding.textViewMarathonDate.text = runningPreferences.getString("Marathon date", null)
    }

    private fun setupButtonClickListeners() {
        binding.container5km.setOnClickListener { launchRunningEditRecordScreen("5km") }
        binding.container10km.setOnClickListener { launchRunningEditRecordScreen("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchRunningEditRecordScreen("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRunningEditRecordScreen("Marathon") }
    }

    private fun launchRunningEditRecordScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance)
        startActivity(intent)
    }
}