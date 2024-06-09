package com.example.bibliotecaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fragment1, fragment2, fragment3, fragment4;
    private TabLayout tabLayout;
    public static List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Agregar libros a la lista
        books.add(new Book("La maría", "Jorge Isaacs", "1867", "9780060114176", "Maria es una novela de Jorge Isaacs. Se publicó en el año de 1867 y se inscribe dentro del romanticismo. Por su tema y estructura conserva todas las características de la novela sentimental que en Francia había llegado a su apogeo con Atala de Chateaubriand y Pablo y Virginia de Saint Pierre.", false));
        books.add(new Book("Cien años de soledad", "Gabriel García Márquez", "1967", "9780307356535", "La obra cuenta la historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo, mostrando la estirpe familiar desde el patriarca José Arcadio Buendía hasta sus descendientes.", false));
        books.add(new Book("Don Quijote de la Mancha", "Miguel de Cervantes", "1605", "9788491050081", "La historia sigue las aventuras de un hidalgo que, tras leer novelas de caballería, decide convertirse en caballero andante para defender el bien y la justicia.", true));
        books.add(new Book("La Odisea", "Homero", "Siglo VIII a.C.", "9780140268867", "La Odisea sigue el viaje de regreso a casa de Odiseo, rey de Ítaca, tras la Guerra de Troya.", true));
        books.add(new Book("Hamlet", "William Shakespeare", "1601", "9788437613808", "La obra gira en torno a Hamlet, príncipe de Dinamarca, quien busca vengar la muerte de su padre a manos de su tío Claudio.", true));
        books.add(new Book("Orgullo y prejuicio", "Jane Austen", "1813", "9788497942230", "La historia sigue la vida de Elizabeth Bennet, una joven ingeniosa y de carácter fuerte, y su relación con el orgulloso y distante Sr. Darcy.", true));
        books.add(new Book("Matar un ruiseñor", "Harper Lee", "1960", "9788490325079", "La novela aborda temas como la injusticia racial y la pérdida de la inocencia en el sur de Estados Unidos durante la Gran Depresión.", true));
        books.add(new Book("1984", "George Orwell", "1949", "9788423345254", "La novela presenta una sociedad distópica gobernada por un régimen totalitario liderado por el Gran Hermano, que ejerce un control omnipresente sobre los ciudadanos.", true));
        books.add(new Book("Moby Dick", "Herman Melville", "1851", "9788420651226", "La historia sigue al marinero Ishmael en su viaje a bordo del ballenero Pequod, comandado por el obsesivo capitán Ahab, en busca de vengarse de Moby Dick, una gran ballena blanca que le arrancó una pierna.", true));
        books.add(new Book("Los miserables", "Victor Hugo", "1862", "9788491050876", "La novela sigue las vidas y las interacciones de varios personajes, particularmente la del exconvicto Jean Valjean, quien busca redención.", true));

        // Crear un grafo
        Graph<Book, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Agregar nodos (libros) al grafo
        for (Book book : books) {
            graph.addVertex(book);
        }

        // Agregar aristas (relaciones entre libros del mismo autor)
        for (int i = 0; i < books.size(); i++) {
            Book book1 = books.get(i);
            for (int j = i + 1; j < books.size(); j++) {
                Book book2 = books.get(j);
                if (book1.getAuthor().equals(book2.getAuthor())) {
                    graph.addEdge(book1, book2);
                }
            }
        }

        fragment1 = findViewById(R.id.fragment1);
        fragment2 = findViewById(R.id.fragment2);
        fragment3 = findViewById(R.id.fragment3);
        fragment4 = findViewById(R.id.fragment4);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        fragmentTransaction.replace(R.id.fragment1, dashBoardFragment);
        fragmentTransaction.commit();

        fragment1.setVisibility(View.VISIBLE);
        fragment2.setVisibility(View.GONE);
        fragment3.setVisibility(View.GONE);
        fragment4.setVisibility(View.GONE);

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        DashBoardFragment dashBoardFragment = new DashBoardFragment();
                        fragmentTransaction1.replace(R.id.fragment1, dashBoardFragment);
                        fragmentTransaction1.commit();
                        fragment1.setVisibility(View.VISIBLE);
                        fragment2.setVisibility(View.GONE);
                        fragment3.setVisibility(View.GONE);
                        fragment4.setVisibility(View.GONE);
                        break;
                    case 1:
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        SearchBookFragment searchBookFragment = new SearchBookFragment();
                        fragmentTransaction2.replace(R.id.fragment2, searchBookFragment);
                        fragmentTransaction2.commit();
                        fragment1.setVisibility(View.GONE);
                        fragment2.setVisibility(View.VISIBLE);
                        fragment3.setVisibility(View.GONE);
                        fragment4.setVisibility(View.GONE);
                        break;
                    case 2:
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        RegisterFragment registerFragment = new RegisterFragment();
                        fragmentTransaction3.replace(R.id.fragment3, registerFragment);
                        fragmentTransaction3.commit();
                        fragment1.setVisibility(View.GONE);
                        fragment2.setVisibility(View.GONE);
                        fragment3.setVisibility(View.VISIBLE);
                        fragment4.setVisibility(View.GONE);
                        break;
                    case 3:
                        FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        ManageUserFragment manageUserFragment = new ManageUserFragment();
                        fragmentTransaction4.replace(R.id.fragment4, manageUserFragment);
                        fragmentTransaction4.commit();
                        fragment1.setVisibility(View.GONE);
                        fragment2.setVisibility(View.GONE);
                        fragment3.setVisibility(View.GONE);
                        fragment4.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}