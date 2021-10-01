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

public class LyricsPage extends AppCompatActivity {

	Toolbar toolbar;
	RecyclerView recyclerView;
	FloatingActionButton addLyricButton;
	LyricsAdapter adapter;
	List<Note> lyrics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lyrics_page);

		//Configure toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//Create new database object and pull lyrics from database
		NoteDatabase db = new NoteDatabase(this);

		lyrics = db.getNotes("lyric");

		//Grab recycler view and set the layout Manager
		recyclerView = findViewById(R.id.list_of_lyrics);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		//Create new adapter and set recycler view to use adapter
		adapter = new LyricsAdapter(this,lyrics);
		recyclerView.setAdapter(adapter);

		//Listener for the add lyrics button
		addLyricButton = (FloatingActionButton) findViewById(R.id.add_lyric_button);
		addLyricButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent addLyricIntent = new Intent(v.getContext(), AddLyric.class);
				startActivity(addLyricIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}