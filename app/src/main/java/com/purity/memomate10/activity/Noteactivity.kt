package com.purity.memomate10.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.purity.memomate10.data.note
import com.purity.memomate10.databinding.ActivityMainBinding
import com.purity.memomate10.notemanager.noteviewmodel

class mainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: noteviewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NoteAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.notes.observe(this) { notes ->
            adapter.submitList(notes)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.fetchNotes()

        binding.addNoteButton.setOnClickListener {
            val newNote = note(
                id = 0,
                title = binding.titleEditText.text.toString(),
                content = binding.contentEditText.text.toString(),
                createdAt = System.currentTimeMillis().toString()
            )
            viewModel.addNote(newNote)

        }
    }





}
