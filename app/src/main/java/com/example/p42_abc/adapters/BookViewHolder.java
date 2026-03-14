package com.example.p42_abc.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p42_abc.models.Book;

public class BookViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView descriptionTextView;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(Book book){
        titleTextView.setText(book.getTitle());
        descriptionTextView.setText(book.getDescription());
    }
}
