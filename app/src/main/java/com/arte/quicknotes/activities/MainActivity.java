package com.arte.quicknotes.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arte.quicknotes.NoteListMock;
import com.arte.quicknotes.R;
import com.arte.quicknotes.adapters.NotesAdapter;
import com.arte.quicknotes.db.NotesDataSource;
import com.arte.quicknotes.models.Note;

public class MainActivity extends AppCompatActivity implements NotesAdapter.Events {

    NotesAdapter mAdapter;
    private NotesDataSource notesDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivity();
    }

    @Override
    protected void onPause() {

        notesDataSource.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        notesDataSource.open();
        super.onResume();
        Toast.makeText(this, "Acaba de resumirse la actividad",  Toast.LENGTH_LONG).show();
    }



    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Context context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NoteActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        notesDataSource = new NotesDataSource(this);
        notesDataSource.open();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.notes_recylcer_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new NotesAdapter(notesDataSource, this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAdapter.mNotifyDataSetChanged();
    }

    @Override
    public void onNoteClicked(Note note) {
        Log.i(MainActivity.class.getSimpleName(), note.toString());
        Intent intent = new Intent(this, NoteActivity.class);
        Bundle arguments = new Bundle();
        arguments.putSerializable(NoteActivity.PARAM_NOTE, note);
        intent.putExtras(arguments);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onNoteLongClicked(final Note note) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirm");
        alert.setMessage("Are you sure to delete the record?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //NoteListMock.deleteNote(note);
                notesDataSource.deleteNote(note);
                mAdapter.mNotifyDataSetChanged();
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();


    }

}
