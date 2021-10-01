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

public class LyricsAdapter extends RecyclerView.Adapter<LyricsAdapter.ViewHolder> {
	LayoutInflater inflater;
	List<Note> lyrics;

	LyricsAdapter(Context context, List<Note> lyrics) {
		this.inflater = LayoutInflater.from(context);
		this.lyrics = lyrics;
	}


	@NonNull
	@Override
	public LyricsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_card, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull LyricsAdapter.ViewHolder holder, int position) {
		String title = lyrics.get(position).getTitle();
		String date = lyrics.get(position).getDate();
		String time = lyrics.get(position).getTime();
		long id = lyrics.get(position).getID();
		holder.lyricTitle.setText(title);
		holder.lyricDate.setText(date);
		holder.lyricTime.setText(time);
	}

	@Override
	public int getItemCount() {
		return lyrics.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView lyricTitle,lyricDate, lyricTime;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			lyricTitle = itemView.findViewById(R.id.note_card_title);
			lyricDate = itemView.findViewById(R.id.note_card_date);
			lyricTime = itemView.findViewById(R.id.note_card_time);


			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					//Pass lyric id as props for SQL query on next screen
					Intent i = new Intent(view.getContext(), OpenNote.class);
					i.putExtra("ID", lyrics.get(getBindingAdapterPosition()).getID());
					view.getContext().startActivity(i);
				}
			});
		}

	}
}
