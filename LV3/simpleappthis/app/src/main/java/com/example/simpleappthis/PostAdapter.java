package com.example.simpleappthis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleappthis.R;
import com.example.simpleappthis.Student;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Student> postsList;
    public PostAdapter(List<Student> postsList) {
        this.postsList = postsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.Ime.setText(postsList.get(position).getName());
        holder.Prezime.setText(postsList.get(position).getSurname());
        holder.Predmet.setText(postsList.get(position).getSubject());
    }
    @Override
    public int getItemCount() {
        return postsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Ime;
        TextView Prezime;
        TextView Predmet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Ime = itemView.findViewById(R.id.ime);
            Prezime = itemView.findViewById(R.id.prezime);
            Predmet = itemView.findViewById(R.id.predmet);
        }
    }
}