package dev.billyrigdon.duchessnotes;

public class Note {
	private long ID;
	private String title;
	private String contents;
	private String date;
	private String time;

	Note(){}

	Note(String title, String contents, String date, String time){
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.time = time;
	}

	Note(long ID, String title, String contents, String date, String time ) {
		this.ID = ID;
		this.title = title;
		this.contents = contents;
		this.date = date;
		this.time = time;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
