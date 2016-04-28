package com.arte.quicknotes.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arte.quicknotes.R;
import com.arte.quicknotes.models.Note;

import java.util.List;

/**
 * Created by jrodgar on 27/04/2016.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    public List<Note> mNoteList;

    public NotesAdapter(List<Note> notes) {
        mNoteList = notes;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Note note = mNoteList.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteExcerpt.setText(note.getExcerpt());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTitle;
        public TextView noteExcerpt;

        public ViewHolder(View itemView) {
            super(itemView);

            noteTitle = (TextView) itemView.findViewById(R.id.note_title);
            noteExcerpt = (TextView) itemView.findViewById(R.id.note_excerpt);
        }
    }
}
