package com.arte.quicknotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class NotesDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    public static abstract class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String TEXT_TYPE = " TEXT";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + NoteEntry.TABLE_NAME + " (" +
                        NoteEntry._ID + " INTEGER PRIMARY KEY," +
                        NoteEntry.COLUMN_NAME_CONTENT + TEXT_TYPE + "," +
                        NoteEntry.COLUMN_NAME_TITLE + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + NoteEntry.TABLE_NAME;
        public static final String[] ALL_COLUMNS = {
                _ID, COLUMN_NAME_TITLE, COLUMN_NAME_CONTENT
        };
    }

    public NotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NoteEntry.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On change, discard the data and start over
        db.execSQL(NoteEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void prepareDB(SQLiteDatabase db) {
        db.execSQL(NoteEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}