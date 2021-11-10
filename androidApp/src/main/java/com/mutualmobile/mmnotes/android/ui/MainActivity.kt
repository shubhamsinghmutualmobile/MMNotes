package com.mutualmobile.mmnotes.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mutualmobile.mmnotes.Greeting
import com.mutualmobile.mmnotes.android.utils.showToast
import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.local.DatabaseDriverFactory
import com.mutualmobile.mmnotes.data.sources.local.repositories.NoteRepositoryImpl
import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import dev.icerock.moko.mvvm.livedata.asFlow

fun greet(): String {
    return Greeting().greeting()
}

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        val sqlDriver = DatabaseDriverFactory(context = application).createDriver()
        val database = MMNotesDatabase(driver = sqlDriver)
        val noteRepository: NoteRepository = NoteRepositoryImpl(database)
        val notesViewModel = NotesViewModel(
            deleteAllNotesUseCase = DeleteAllNotesUseCase(noteRepository = noteRepository),
            getAllNotesUseCase = GetAllNotesUseCase(noteRepository = noteRepository),
            searchNotesUseCase = SearchNotesUseCase(noteRepository = noteRepository),
            insertNoteUseCase = InsertNoteUseCase(noteRepository = noteRepository)
        )

        super.onCreate(savedInstanceState)

        setContent {
            TestComp(
                text = greet(),
                viewModel = notesViewModel
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TestComp(
    text: String,
    viewModel: NotesViewModel
) {
    val ctx = LocalContext.current
    val listOfNotes by viewModel.listOfNotesLiveData.asFlow().collectAsState(initial = listOf())
    val myNote by remember {
        mutableStateOf(
            Note(title = "TestNote1", body = "BodyOfNote1", dateCreated = 123456)
        )
    }
    val noteState by viewModel.singleNoteLiveData.asFlow().collectAsState(initial = listOf())
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = listOfNotes.toString(),
            modifier = Modifier.combinedClickable(
                onClick = {
                    viewModel.getAllNotes()
                    if (!listOfNotes.contains(myNote)) {
                        viewModel.insertNote(myNote)
                        ctx.showToast("Inserted!")
                    } else {
                        ctx.showToast("Already exists!")
                    }
                },
                onLongClick = {
                    ctx.showToast("Deleted!")
                    with(viewModel) {
                        deleteAllNotes()
                        getAllNotes()
                    }
                },
                onDoubleClick = {
                    viewModel.searchNotes("TestNote1")
                    if (noteState.isEmpty()) {
                        ctx.showToast("Notes found!")
                    } else {
                        ctx.showToast("Notes not found!")
                    }
                }
            )
        )
    }
}
