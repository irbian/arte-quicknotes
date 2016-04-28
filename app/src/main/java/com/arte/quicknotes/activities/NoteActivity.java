package com.arte.quicknotes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.arte.quicknotes.NoteListMock;
import com.arte.quicknotes.R;
import com.arte.quicknotes.database.NotesStorage;
import com.arte.quicknotes.models.Note;

public class NoteActivity extends AppCompatActivity {

    public static final String PARAM_NOTE = "param-note";
    private EditText mTitle;
    private EditText mContent;
    private Note mNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setupActivity();
        loadNote();
    }

    private void loadNote() {
        Note note = (Note) getIntent().getSerializableExtra(PARAM_NOTE);
        if (note == null) {
            return;
        }
        mNote = note;
        mTitle.setText(note.getTitle());
        mContent.setText(note.getContent());
        Log.i(NoteActivity.class.getSimpleName(), note.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);

        if (mNote == null) {
            MenuItem delete = menu.findItem(R.id.action_delete);
            delete.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        Note note = new Note();
        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();
        if (mNote != null) {
            note.setTitle(title);
            note.setContent(content);
            NoteListMock.addNote(note);
        } else {
            mNote.setTitle(title);
            mNote.setContent(content);
            NoteListMock.updateNote(note);
        }
        finish();
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = (EditText) findViewById(R.id.et_title);
        mContent = (EditText) findViewById(R.id.et_content);
    }
}
