package com.mutualmobile.mmnotes.viewmodels

import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

private const val TAG = "NotesViewModel"

class NotesViewModel constructor(
    private val deleteAllNotesUseCase: DeleteAllNotesUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val searchNotesUseCase: SearchNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {

    private val _singleNoteLiveData: MutableLiveData<List<Note>> = MutableLiveData(listOf())
    val singleNoteLiveData: LiveData<List<Note>> = _singleNoteLiveData

    private val _listOfNotesLiveData: MutableLiveData<List<Note>> = MutableLiveData(listOf())
    val listOfNotesLiveData: LiveData<List<Note>> = _listOfNotesLiveData

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
            _singleNoteLiveData.postValue(searchNotesUseCase(title = title))
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch {
            insertNoteUseCase(note = note)
        }
    }
}
