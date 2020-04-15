package com.joeparker.gradewiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewGradeActivity : AppCompatActivity() {

    private lateinit var editGradeView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_grade)
        editGradeView = findViewById(R.id.edit_grade)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editGradeView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val text = editGradeView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, text)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}