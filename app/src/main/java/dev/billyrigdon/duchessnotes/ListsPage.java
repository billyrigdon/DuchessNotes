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

public class ListsPage extends AppCompatActivity {

	Toolbar toolbar;
	RecyclerView recyclerView;
	FloatingActionButton addListButton;
	ListsAdapter adapter;
	List<Note> lists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lists_page);

		//Configure toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		//Create new database object and pull lyrics from database
		NoteDatabase db = new NoteDatabase(this);

		lists = db.getNotes("list");

		//Grab recycler view and set the layout Manager
		recyclerView = findViewById(R.id.list_of_lists);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		//Create new adapter and set recycler view to use adapter
		adapter = new ListsAdapter(this,lists);
		recyclerView.setAdapter(adapter);

		//Listener for the add lyrics button
		addListButton = (FloatingActionButton) findViewById(R.id.add_list_button);
		addListButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent addListIntent = new Intent(v.getContext(), AddList.class);
				startActivity(addListIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}