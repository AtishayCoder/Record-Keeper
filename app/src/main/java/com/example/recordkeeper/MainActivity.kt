package com.example.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.recordkeeper.cycling.CyclingFragment
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.example.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_running -> {
            deleteSharedPreferences("running")
            Toast.makeText(this, "Cleared all running records!", Toast.LENGTH_LONG).show()
            recreate()
            true
        }
        R.id.reset_cycling -> {
            deleteSharedPreferences("cycling")
            Toast.makeText(this, "Cleared all cycling records!", Toast.LENGTH_LONG).show()
            recreate()
            true
        }
        R.id.reset_all -> {
            deleteSharedPreferences("running")
            deleteSharedPreferences("cycling")
            Toast.makeText(this, "Cleared all records!", Toast.LENGTH_LONG).show()
            recreate()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.nav_running -> onRunningClicked()
        R.id.nav_cycling -> onCyclingClicked()
        else -> false
    }
}