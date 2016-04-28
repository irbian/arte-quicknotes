package com.arte.quicknotes;

import com.arte.quicknotes.database.NotesStorage;
import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodgar on 27/04/2016.
 */
public class NoteListMock implements NotesStorage {
    private static List<Note> noteList;
    private static int nextId = 0;

    public static List<Note> getList() {
        if (noteList == null) {
            createList();
        }
        return noteList;
    }

    public static void addNote(Note note) {
        note.setId(nextId);
        noteList.add(note);
        nextId++;
    }

    private static void createList() {
        noteList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Note note = new Note();
            note.setTitle("Note #" + (i + 1));
            note.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed nulla nibh. In sed lacinia neque, sit amet finibus mauris. Vestibulum venenatis, urna quis placerat vestibulum, est ipsum vestibulum mi, quis aliquet lorem tortor at orci. Quisque mollis vel nisl id tincidunt. Nam eu tortor scelerisque, tristique sapien non, ultrices libero. Nam placerat, sapien sed lobortis mollis, velit lorem aliquet velit, tristique aliquam eros arcu ut nibh. Aliquam a enim gravida, dapibus dui in, pellentesque dolor.");
            noteList.add(note);
        }
    }

    @Override
    public Note get(int id) {
        return noteList.get(id);
    }

    @Override
    public List<Note> getAll() {
        return getList();
    }

    @Override
    public void add(Note note) {
        addNote(note);
    }

    @Override
    public void delete(Note note) {
        noteList.remove(note.getId());
    }

    @Override
    public Note update(Note note) {
        noteList.get(note.getId()).setContent(note.getContent());
        noteList.get(note.getId()).setTitle(note.getTitle());
        return noteList.get(note.getId());
    }

    public static void updateNote(Note updateNote) {
        for(int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note.getId() == updateNote.getId()) {
                note.setTitle(updateNote.getTitle());
                note.setContent(updateNote.getContent());
                return;
            }
        }
    }

    public static void deleteNote(Note noteToDelete) {
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (note.getId() == noteToDelete.getId()) {
                noteList.remove(i);
                return;
            }
        }
    }
}
