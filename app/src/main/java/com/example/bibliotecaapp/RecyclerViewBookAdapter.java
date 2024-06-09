package com.example.bibliotecaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Book> mBookList;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewBookAdapter(Context context, List<Book> bookList) {
        this.mContext = context;
        this.mBookList = bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.mBookList = bookList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.adapter_list_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Book book = mBookList.get(position);
        ((BookViewHolder) holder).bind(book, position);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private TextView title;
        private TextView author;
        private TextView year;
        private TextView available;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.linearLayout);
            title = itemView.findViewById(R.id.textViewTitle);
            author = itemView.findViewById(R.id.textViewAuthor);
            year = itemView.findViewById(R.id.textViewYear);
            available = itemView.findViewById(R.id.textViewAvailable);
        }

        public void bind(Book book, int position) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            year.setText(book.getYear());
            available.setText(book.isAvailable()? "Disponible" : "Prestado");
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!= null) {
                        mOnItemClickListener.onItemClick(v, position, mBookList.get(position), true);
                    }
                }
            });

            if (book.isAvailable()) {
//                layout.setBackgroundColor(mContext.getResources().getColor(R.color.green));
                available.setTextColor(mContext.getResources().getColor(R.color.green));
            } else {
//                layout.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                available.setTextColor(mContext.getResources().getColor(R.color.red));
            }

        }
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }
}