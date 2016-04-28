package com.arte.quicknotes.database;

import com.arte.quicknotes.models.Note;

import java.util.List;

/**
 * Created by jrodgar on 28/04/2016.
 */
public interface NotesStorage {
    Note get(int id);
    List<Note> getAll();
    void add(Note note);
    void delete(Note note);
    Note update(Note note);

}
