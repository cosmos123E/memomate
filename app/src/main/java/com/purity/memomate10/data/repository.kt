package com.purity.memomate10.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await



class Noterepository{
    private val db = FirebaseFirestore.getInstance()
    private val notesCollection = db.collection("notes")

    suspend fun getNotes(): List<note> {
        val snapshot = notesCollection
            .get()
            .await()
        val notes = mutableListOf<note>()

        for (document in snapshot.documents) {
            val note = document.toObject(note::class.java)
            if (note != null) {
                notes.add(note)
            }
        }
        return notes
    }

    // Add a new note to Firestore
    suspend fun addNote(note: note) {
        notesCollection
            .add(note)
            .await()
    }

    suspend fun deleteNote(note: note) {
        val querySnapshot = notesCollection
            .whereEqualTo("title", note.title)
            .whereEqualTo("content", note.content)
            .get()
            .await()
    }

    suspend fun editNote(note: note) {
        val querySnapshot = notesCollection
            .whereEqualTo("title", note.title)
            .whereEqualTo("content", note.content)
            .get()
            .await()
    }

}