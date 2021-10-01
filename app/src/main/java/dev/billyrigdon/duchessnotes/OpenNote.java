package dev.billyrigdon.duchessnotes;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OpenNote extends AppCompatActivity {

	Toolbar toolbar;
	TextView title;
	TextView contents;
	FloatingActionButton noteDeleteButton;
	Button noteUpdateButton;


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
		String category = note.getCategory();
		Log.d(TAG, "category: " + category);


		//Grab fields and set text value
		title = findViewById(R.id.open_note_title);
		contents = findViewById(R.id.open_note_contents);
		title.setText(note.getTitle());
		contents.setText(note.getContents());

		//Delete button listener
		noteDeleteButton = findViewById(R.id.open_note_delete);
		noteDeleteButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				db.deleteNote(id);

				if (category.equals("note")) {
					Intent noteIntent = new Intent(v.getContext(), NotesPage.class);
					startActivity(noteIntent);
				} else if (category.equals("lyric")) {
					Intent lyricIntent = new Intent(v.getContext(), LyricsPage.class);
					startActivity(lyricIntent);
				} else if (category.equals("list")) {
					Intent listIntent = new Intent(v.getContext(), ListsPage.class);
					startActivity(listIntent);
				}
			}
		});

		//Update button Listener
		noteUpdateButton = findViewById(R.id.open_note_save_button);
		noteUpdateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				note.setTitle(title.getText().toString());
				note.setContents(contents.getText().toString());
				db.editNote(note);
				Log.d(TAG, "onClick: " + category);
				if (category.equals("note")) {
					Intent noteIntent = new Intent(view.getContext(), NotesPage.class);
					startActivity(noteIntent);
				} else if (category.equals("lyric")) {
					Intent lyricIntent = new Intent(view.getContext(), LyricsPage.class);
					startActivity(lyricIntent);
				} else if (category.equals("list")) {
					Intent listIntent = new Intent(view.getContext(), ListsPage.class);
					startActivity(listIntent);
				}

			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}