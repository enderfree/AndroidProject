package team3.samuelandsebastian.androidproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import team3.samuelandsebastian.androidproject.R;
import team3.samuelandsebastian.androidproject.models.Word;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Word> words;

    public RecyclerAdapter(ArrayList<Word> words) {
        this.words = words;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word, date, nbOfResults;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.txtViewRecycleWord);
            date = itemView.findViewById(R.id.txtViewRecycleDate);
            nbOfResults = itemView.findViewById(R.id.txtViewRecycleResults);
        }
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cell, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = words.get(position);
        holder.word.setText(word.getWord());
        holder.nbOfResults.setText(word.getResults().size() + (word.getResults().size() == 1 ? " Result" : " Results"));
        holder.date.setText(word.getDate());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

}
