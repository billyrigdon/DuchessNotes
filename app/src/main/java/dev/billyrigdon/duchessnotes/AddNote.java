package dev.billyrigdon.duchessnotes;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddNote extends AppCompatActivity {

	Toolbar toolbar;
	EditText noteTitle, noteContents;
	Button submitButton;

	String title;
	Calendar calendar;
	String todayDate;
	String currentTime;
	String contents;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Set xml file and action bar
		setContentView(R.layout.activity_add_note);
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		noteTitle = findViewById(R.id.note_title);
		noteContents = findViewById(R.id.note_contents);
		NoteDatabase db = new NoteDatabase(this);


		noteTitle.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if (charSequence.length() != 0) {
					title = charSequence.toString();


				}
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});

		noteContents.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				if (charSequence.length() != 0) {
					contents = charSequence.toString();


				}
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});

		submitButton = findViewById(R.id.submit_button);

		submitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Save note
				Note note = new Note(noteTitle.getText().toString(), noteContents.getText().toString(), todayDate, currentTime, "note");
				db.addNote(note);
				goToNotes();
			}
		});


		//get current time and date
		calendar = Calendar.getInstance();

		todayDate = (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/"
				+ calendar.get(Calendar.YEAR);

		currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));


	}

	private void goToNotes() {
		Intent i = new Intent(this,NotesPage.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	private String pad(int time) {
		if (time < 10) {
			return "0" + time;
		} else {
			return String.valueOf(time);
		}
	}
}
