package com.joeparker.gradewiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewModuleActivity : AppCompatActivity() {

    private lateinit var editModuleCodeView: EditText
    private lateinit var editModuleNameView: EditText
    private lateinit var editModuleCreditsView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_module)
        editModuleCodeView = findViewById(R.id.edit_module_code)
        editModuleNameView = findViewById(R.id.edit_module_name)
        editModuleCreditsView = findViewById(R.id.edit_module_credits)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editModuleNameView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val data = arrayListOf(
                    editModuleCodeView.text.toString(),
                    editModuleNameView.text.toString(),
                    editModuleCreditsView.text.toString()
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