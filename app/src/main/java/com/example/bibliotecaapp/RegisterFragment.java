package com.example.bibliotecaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText editTextTitle, editTextAuthor, editTextYear, editTextISBN, editTextDescription;
    private Button buttonRegistration;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        editTextTitle = (EditText) view.findViewById(R.id.editTextTitle);
        editTextAuthor = (EditText) view.findViewById(R.id.editTextAuthor);
        editTextYear = (EditText) view.findViewById(R.id.editTextYear);
        editTextISBN = (EditText) view.findViewById(R.id.editTextISBN);
        editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        buttonRegistration = (Button) view.findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(this);

        return view;
    }

    private void goToRegister() {

        String title = editTextTitle.getText().toString();
        String author = editTextAuthor.getText().toString();
        String year = editTextYear.getText().toString();
        String isbn = editTextISBN.getText().toString();
        String description = editTextDescription.getText().toString();
        if (title.isEmpty() || author.isEmpty() || year.isEmpty() || isbn.isEmpty() || description.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {

            List<Book> books = MainActivity.books;
            Book book = new Book(title, author, year, isbn, description,true);
            books.add(book);
            Toast.makeText(getContext(), "¡Libro agregado!", Toast.LENGTH_SHORT).show();
            editTextTitle.setText("");
            editTextAuthor.setText("");
            editTextYear.setText("");
            editTextISBN.setText("");
            editTextDescription.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getTag().equals("clickRegister")){
            goToRegister();
        }
    }
}