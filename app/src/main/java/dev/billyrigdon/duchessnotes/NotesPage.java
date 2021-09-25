package dev.billyrigdon.duchessnotes;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesPage extends AppCompatActivity {

	Toolbar toolbar;
	RecyclerView recyclerView;
	FloatingActionButton addNoteButton;
	NotesAdapter adapter;
	List<Note> notes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes_page);

		//Configure toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//Create new database object and pull notes from database
		NoteDatabase db = new NoteDatabase(this);
		notes = db.getNotes();
		Log.d(TAG, "onCreate: " + notes.get(0).getID());


		//Grab recycler view and set the layout Manager
		recyclerView = findViewById(R.id.list_of_notes);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		//Create new adapter and set recycler view to use adapter
		adapter = new NotesAdapter(this,notes);
		recyclerView.setAdapter(adapter);

		//Listener for the add notes button
		addNoteButton = (FloatingActionButton) findViewById(R.id.add_note_button);
		addNoteButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent addNoteIntent = new Intent(v.getContext(), AddNote.class);
				startActivity(addNoteIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}