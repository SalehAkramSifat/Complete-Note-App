package com.sifat.noteapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sifat.noteapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    lateinit var db : NoteDatabaseHelper
    private var noteId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1){
            finish()
            return
        }
        val note = db.getNotebyId(noteId)
        binding.updatetitle.setText(note.title)
        binding.updatemainBox.setText(note.content)

        binding.updatedone.setOnClickListener{
            val newtitle = binding.updatetitle.text.toString()
            val newcontent = binding.updatemainBox.text.toString()

            val updateNote = Note(noteId, newtitle, newcontent)

            db.updateNote(updateNote)
            finish()

            Toast.makeText(this, "Changed", Toast.LENGTH_SHORT).show()
        }
    }
}