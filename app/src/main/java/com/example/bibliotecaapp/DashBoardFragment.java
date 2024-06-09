package com.example.bibliotecaapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    private TextView textView;
    private RecyclerView recyclerView;
    RecyclerViewBookAdapter adapter;
    List<Book> books1;
    Book book;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        textView = view.findViewById(R.id.textView);
        recyclerView = view.findViewById(R.id.recyclerView);

        // Crear un grafo simple
        Graph<Book, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Agregar nodos (libros) al grafo
        for (Book book : MainActivity.books) {
            graph.addVertex(book);
        }

        // Agregar aristas (relaciones) entre libros del mismo autor
        for (Book book1 : MainActivity.books) {
            for (Book book2 : MainActivity.books) {
                if (!book1.equals(book2) && book1.getAuthor().equals(book2.getAuthor())) {
                    graph.addEdge(book1, book2);
                }
            }
        }

        // Obtener los libros prestados (vértices conectados en el grafo)
        books1 = new ArrayList<>();
        for (Book book : MainActivity.books) {
            if (!book.isAvailable()) {
                books1.add(book);
            }
        }

        adapter = new RecyclerViewBookAdapter(getContext(), books1);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index, Object item, boolean isChecked) {
                book = (Book) item;

                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("¿Devolvieron el libro: " + book.getTitle() + "?");
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            book.setAvailable(true);
                            books1.remove(book);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}