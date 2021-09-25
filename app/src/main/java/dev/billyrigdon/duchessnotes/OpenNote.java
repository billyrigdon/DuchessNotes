package dev.billyrigdon.duchessnotes;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class OpenNote extends AppCompatActivity {

	Toolbar toolbar;
	TextView title;
	TextView contents;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_note);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//Receives intent/props from NotesAdapter
		Intent i = getIntent();
		long id = i.getLongExtra("ID", 0);

		//Get note from database
		NoteDatabase db = new NoteDatabase(this);
		Note note = db.getNote(id);


		//Grab fields and set text value
		title = findViewById(R.id.open_note_title);
		contents = findViewById(R.id.open_note_contents);
		title.setText(note.getTitle());
		contents.setText(note.getContents());


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}