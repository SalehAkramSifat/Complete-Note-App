package com.sifat.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sifat.noteapp.databinding.ActivityAddnoteBinding

class AddnoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddnoteBinding
    private lateinit var db : NoteDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddnoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        binding.done.setOnClickListener {
            val title = binding.title.text.toString()
            val content = binding.mainBox.text.toString()

            val note = Note(0, title,  content)
            db.insertNote(note)
            startActivity(Intent(this, MainActivity::class.java))
            finish()

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }

    }
}