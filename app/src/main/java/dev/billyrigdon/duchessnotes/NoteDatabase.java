package dev.billyrigdon.duchessnotes;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "notedb";
	private static final String DATABASE_TABLE = "notes";

	//column names for database
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_CONTENT = "contents";
	private static final String KEY_DATE = "date";
	private static final String KEY_TIME = "time";

	NoteDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

		String query = "CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, contents TEXT, date TEXT, time TEXT) ";

		sqLiteDatabase.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

		if (i >= i1) {
			return;
		} else {
			sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(sqLiteDatabase);
		}

	}

	public long addNote(Note note) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_TITLE, note.getTitle());
		contentValues.put(KEY_CONTENT, note.getContents());
		contentValues.put(KEY_DATE, note.getDate());
		contentValues.put(KEY_TIME, note.getTime());

		long ID = db.insert(DATABASE_TABLE, null, contentValues);

		return ID;
	}

	public Note getNote(long id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor returnedNote = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME}, KEY_ID + "=?",
				new String[]{String.valueOf(id)}, null, null, null);

		if (returnedNote != null) {
			returnedNote.moveToFirst();
		}

		return new Note(returnedNote.getLong(0), returnedNote.getString(1), returnedNote.getString(2),
				returnedNote.getString(3), returnedNote.getString(4));

	}
	public List<Note> getNotes() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<Note> allNotes = new ArrayList<>();

		String query = "SELECT * FROM " + DATABASE_TABLE;
		Cursor returnedNote = db.rawQuery(query,null);
		if (returnedNote.moveToFirst()) {
			do{

				Note note = new Note();
				note.setID(returnedNote.getLong(0));
				note.setTitle(returnedNote.getString(1));
				note.setContents(returnedNote.getString(2));
				note.setDate(returnedNote.getString(3));
				note.setTime(returnedNote.getString(4));

				allNotes.add(note);

			} while (returnedNote.moveToNext());
		}

		return allNotes;
	}
}
