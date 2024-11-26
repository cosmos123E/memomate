package com.purity.memomate10.notemanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purity.memomate10.data.Noterepository
import com.purity.memomate10.data.note
import kotlinx.coroutines.launch

class noteviewmodel : ViewModel(){
    private val repository = Noterepository()

    private val _notes = MutableLiveData<List<note>>()
    val notes: LiveData<List<note>> = _notes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun fetchNotes() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val notes = repository.getNotes()
                _notes.value = notes
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }

    }
    fun addNote(note: note) {
        viewModelScope.launch {
            try {
                repository.addNote(note)
                fetchNotes()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    fun deleteNote(note: note) {
        viewModelScope.launch {
            try {
                repository.deleteNote(note)
                fetchNotes()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    fun editNote(note: note) {
        viewModelScope.launch {
            try {
                repository.editNote(note)
                fetchNotes()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

}
