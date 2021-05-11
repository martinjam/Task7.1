package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task71.data.DatabaseHelper;
import com.example.task71.model.Note;

public class EditNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Button updateButton = findViewById(R.id.updateButton);
        EditText noteMultiLineEditText2 = findViewById(R.id.noteMultiLineEditText2);
        DatabaseHelper db = new DatabaseHelper(this);

        noteMultiLineEditText2.setText(getIntent().getStringExtra("note_id"));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newNote = noteMultiLineEditText2.getText().toString();

                int updateRow = db.updateNote(new Note(newNote));
                if (updateRow > 0) {
                    Toast.makeText(EditNote.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditNote.this, "No row found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}