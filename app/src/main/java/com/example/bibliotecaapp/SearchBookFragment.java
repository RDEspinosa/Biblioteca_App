package com.example.bibliotecaapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchBookFragment extends Fragment {

    // Declaración de variables
    private TreeNode root; // Raíz del árbol
    private RecyclerView recyclerView;
    private RecyclerViewBookAdapter adapter;
    private List<Book> filteredBooks;
    private TextInputLayout textInputLayout;
    private EditText editTextSearch;

    public SearchBookFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_book, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        textInputLayout = view.findViewById(R.id.textInputLayout);
        editTextSearch = view.findViewById(R.id.editTextSearch);

        // Inicialización de variables
        filteredBooks = new ArrayList<>();
        adapter = new RecyclerViewBookAdapter(getContext(), filteredBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Agregar libros al árbol
        addBooksToTree(MainActivity.books);

        // Configurar el botón del TextInputLayout
        textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAndUpdateAdapter(editTextSearch.getText().toString());
            }
        });

        // Realizar la búsqueda de libros y mostrar los resultados
        searchAndUpdateAdapter(""); // Búsqueda inicial sin filtro

        return view;
    }

    // Método para agregar libros al árbol
    private void addBooksToTree(List<Book> books) {
        for (Book book : books) {
            root = insert(root, book);
        }
    }

    // Método para insertar un libro en el árbol
    private TreeNode insert(TreeNode node, Book book) {
        if (node == null) {
            return new TreeNode(book);
        }

        if (book.getTitle().compareTo(node.book.getTitle()) < 0) {
            node.left = insert(node.left, book);
        } else if (book.getTitle().compareTo(node.book.getTitle()) > 0) {
            node.right = insert(node.right, book);
        }

        return node;
    }

    // Método para realizar la búsqueda de libros y actualizar el adaptador
    private void searchAndUpdateAdapter(String query) {
        filteredBooks.clear();
        search(root, query);
        adapter.notifyDataSetChanged();
    }

    // Método para realizar la búsqueda de libros de manera recursiva
    private void search(TreeNode node, String query) {
        if (node == null) {
            return;
        }

        // Búsqueda en el subárbol izquierdo
        search(node.left, query);

        // Agregar el libro al resultado si coincide con la consulta
        if (node.book.getTitle().toLowerCase().contains(query.toLowerCase())) {
            filteredBooks.add(node.book);
        }

        // Búsqueda en el subárbol derecho
        search(node.right, query);
    }

    // Clase para representar un nodo del árbol
    private static class TreeNode {
        private Book book;
        private TreeNode left, right;

        public TreeNode(Book book) {
            this.book = book;
            left = right = null;
        }
    }
}