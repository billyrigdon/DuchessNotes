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

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {
	LayoutInflater inflater;
	List<Note> lists;

	ListsAdapter(Context context, List<Note> list) {
		this.inflater = LayoutInflater.from(context);
		this.lists = list;
	}


	@NonNull
	@Override
	public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_card, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder holder, int position) {
		String title = lists.get(position).getTitle();
		String date = lists.get(position).getDate();
		String time = lists.get(position).getTime();
		long id = lists.get(position).getID();
		holder.listTitle.setText(title);
		holder.listDate.setText(date);
		holder.listTime.setText(time);
	}

	@Override
	public int getItemCount() {
		return lists.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView listTitle,listDate, listTime;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			listTitle = itemView.findViewById(R.id.note_card_title);
			listDate = itemView.findViewById(R.id.note_card_date);
			listTime = itemView.findViewById(R.id.note_card_time);


			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					//Pass lyric id as props for SQL query on next screen
					Intent i = new Intent(view.getContext(), OpenNote.class);
					i.putExtra("ID", lists.get(getBindingAdapterPosition()).getID());
					view.getContext().startActivity(i);
				}
			});
		}

	}
}
