package com.joeparker.gradewiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.joeparker.gradewiz.database.entity.Grade

class MainActivity : AppCompatActivity() {

    private lateinit var gradeViewModel: GradeViewModel
    private val newGradeActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = GradeListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        gradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)

        // Observe livedata and update on change
        gradeViewModel.allGrades.observe(this, Observer { grades ->
            grades?.let { adapter.setGrades(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewGradeActivity::class.java)
            startActivityForResult(intent, newGradeActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newGradeActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewGradeActivity.EXTRA_REPLY)?.let {
                val grade = Grade(name = it, mark = 70.0f, weighting = 50.0f)
                gradeViewModel.insert(grade)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
