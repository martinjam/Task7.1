package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.task71.data.DatabaseHelper;
import com.example.task71.model.Note;

import java.util.ArrayList;
import java.util.List;

public class ShowNotes extends AppCompatActivity {
    ListView notesListView;
    ArrayList<String> notesArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        notesListView = findViewById(R.id.notesListView);
        notesArrayList = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(ShowNotes.this);

        List<Note> notesList = db.fetchAllNotes();

        for (Note note : notesList) {
            notesArrayList.add(note.getNote());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesArrayList);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = adapter.getItem(position);
                Note editNote = db.fetchNote(item);
                Intent intent = new Intent(ShowNotes.this, EditNote.class);
                intent.putExtra("note_id", editNote.getId());
                startActivity(intent);
            }
        });
    }
}