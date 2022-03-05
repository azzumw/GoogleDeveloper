package com.example.watermeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.watermeproject.adapter.PlantAdapter
import com.example.watermeproject.adapter.PlantListener
import com.example.watermeproject.ui.ReminderDialogFragment
import com.example.watermeproject.viewmodel.PlantViewModel
import com.example.watermeproject.viewmodel.PlantViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: PlantViewModel by viewModels {
        PlantViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PlantAdapter(PlantListener { plant ->
            val dialog = ReminderDialogFragment(plant.name)
            dialog.show(supportFragmentManager,"WaterReminderDialogFragment")
            true
        })
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        val data = viewModel.plants
        adapter.submitList(data)
    }
}