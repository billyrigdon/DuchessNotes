package dev.billyrigdon.duchessnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	Toolbar toolbar;
	CardView notesCard;
	CardView listsCard;
	CardView lyricsCard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Set toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);



		//Create listeners for cards
		notesCard = (CardView) findViewById(R.id.notes_card);
		notesCard.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent noteIntent = new Intent(v.getContext(),NotesPage.class);
				startActivity(noteIntent);
			}
		});



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}


}