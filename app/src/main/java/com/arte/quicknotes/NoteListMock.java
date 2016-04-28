package com.arte.quicknotes;

import com.arte.quicknotes.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodgar on 27/04/2016.
 */
public class NoteListMock {
    private static List<Note> noteList;

    public static List<Note> getList() {
        if (noteList == null) {
            createList();
        }
        return noteList;
    }

    public static void addNote(Note note) {
        noteList.add(note);
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
}
