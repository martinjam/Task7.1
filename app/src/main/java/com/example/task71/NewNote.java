package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task71.data.DatabaseHelper;
import com.example.task71.model.Note;

public class NewNote extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        EditText noteMultiLineEditText = findViewById(R.id.noteMultiLineEditText);
        Button saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = noteMultiLineEditText.getText().toString();

                if (note != null)
                {
                    long result = db.insertNote(new Note(note));
                    if (result > 0)
                    {
                        Toast.makeText(NewNote.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(NewNote.this, "Registration error!", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(NewNote.this, "Two passwords do not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}