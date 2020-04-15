package com.joeparker.gradewiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewGradeActivity : AppCompatActivity() {

    private lateinit var editGradeNoteView: EditText
    private lateinit var editGradeMarkView: EditText
    private lateinit var editGradeWeightingView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_grade)
        editGradeNoteView = findViewById(R.id.edit_grade_note)
        editGradeMarkView = findViewById(R.id.edit_grade_mark)
        editGradeWeightingView = findViewById(R.id.edit_grade_weighting)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editGradeMarkView.text) || TextUtils.isEmpty(editGradeWeightingView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
//                val note = editGradeNoteView.text.toString()
//                val mark = editGradeMarkView.text.toString()
//                val weighting = editGradeWeightingView.text.toString()
                val data = arrayListOf(
                    editGradeNoteView.text.toString(),
                    editGradeMarkView.text.toString(),
                    editGradeWeightingView.text.toString()
                )
                replyIntent.putStringArrayListExtra(EXTRA_REPLY, data)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}