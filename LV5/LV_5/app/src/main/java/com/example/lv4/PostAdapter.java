package com.example.lv4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lv4.R;
import com.example.lv4.Student;

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
        holder.Image.setImageBitmap(ConvertImageToBitmap(postsList.get(position).getImage()));
    }
    @Override
    public int getItemCount() {
        return postsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Ime;
        TextView Prezime;
        TextView Predmet;
        ImageView Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Ime = itemView.findViewById(R.id.ime);
            Prezime = itemView.findViewById(R.id.prezime);
            Predmet = itemView.findViewById(R.id.predmet);
            Image = itemView.findViewById(R.id.img);
        }
    }
    public Bitmap ConvertImageToBitmap(String sImage)
    {
        if(sImage != null)
        {
            byte[] bytes = Base64.decode(sImage,Base64.DEFAULT);
            Bitmap image = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            return image;
        }
        return null;
    }
}