package com.mutualmobile.mmnotes.viewmodels

import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetNoteByIdUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.UpdateNoteUseCase
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

private const val TAG = "NotesViewModel"

class NotesViewModel constructor(
    private val deleteAllNotesUseCase: DeleteAllNotesUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val searchNotesUseCase: SearchNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    private val _listOfNotesLiveData: MutableLiveData<List<Note>> = MutableLiveData(listOf())
    val listOfNotesLiveData: LiveData<List<Note>> = _listOfNotesLiveData

    private val _searchNotesLiveData: MutableLiveData<List<Note>> = MutableLiveData(listOf())
    val searchNotesLiveData: LiveData<List<Note>> = _searchNotesLiveData

    init {
        _listOfNotesLiveData.postValue(getAllNotesUseCase())
    }

    fun getAllNotes() {
        viewModelScope.launch {
            _listOfNotesLiveData.postValue(getAllNotesUseCase())
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            deleteAllNotesUseCase()
        }
    }

    fun searchNotes(title: String) {
        viewModelScope.launch {
            _searchNotesLiveData.postValue(searchNotesUseCase(title = title))
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            insertNoteUseCase(note = note)
        }
    }

    fun getNoteById(id: Int) = getNoteByIdUseCase(noteId = id)

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note = note)
        }
    }
}
