package com.joeparker.gradewiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
        val adapter = GradeListAdapter(this, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        gradeViewModel = ViewModelProvider(this).get(GradeViewModel::class.java)

        // Observe livedata and update on change
        gradeViewModel.allGrades.observe(this, Observer { grades ->
            grades?.let { adapter.setGrades(it) }
        })

        gradeViewModel.total.observe(this, Observer { total ->
            total?.let { findViewById<TextView>(R.id.validation).text = it.toString() }
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
            data?.extras?.getStringArrayList(NewGradeActivity.EXTRA_REPLY)?.let {
                val grade = Grade(note = it[0], mark = it[1].toFloat(), weighting = it[2].toFloat())
                gradeViewModel.addGrade(grade)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
