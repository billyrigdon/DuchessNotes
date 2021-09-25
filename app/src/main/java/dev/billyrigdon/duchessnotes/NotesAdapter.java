package dev.billyrigdon.duchessnotes;


import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
	LayoutInflater inflater;
	List<Note> notes;

	NotesAdapter(Context context, List<Note> notes) {
		this.inflater = LayoutInflater.from(context);
		this.notes = notes;
	}


	@NonNull
	@Override
	public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_card, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
		String title = notes.get(position).getTitle();
		String date = notes.get(position).getDate();
		String time = notes.get(position).getTime();
		long id = notes.get(position).getID();
		holder.noteTitle.setText(title);
		holder.noteDate.setText(date);
		holder.noteTime.setText(time);
	}

	@Override
	public int getItemCount() {
		return notes.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView noteTitle,noteDate, noteTime;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			noteTitle = itemView.findViewById(R.id.note_card_title);
			noteDate = itemView.findViewById(R.id.note_card_date);
			noteTime = itemView.findViewById(R.id.note_card_time);


			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					//Pass note id as props for SQL query on next screen
					Intent i = new Intent(view.getContext(), OpenNote.class);
					i.putExtra("ID", notes.get(getBindingAdapterPosition()).getID());
					view.getContext().startActivity(i);
				}
			});
		}

	}
}
