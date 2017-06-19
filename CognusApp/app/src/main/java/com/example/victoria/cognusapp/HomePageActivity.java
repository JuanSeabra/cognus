package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import classes.Pergunta;
import classes.Usuario;

public class HomePageActivity extends AppCompatActivity {
    Usuario usuarioAtual;
    private List<Usuario> usuarios;
    private List<Pergunta> perguntas = new ArrayList<>();

    public void criarPerguntas() {
        Pergunta p1 = new Pergunta("Qual a raiz de 1069?", "");
        Pergunta p2 = new Pergunta("Qual a cor mais bonita do mundo?", "");
        perguntas.add(p1);
        perguntas.add(p2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        usuarios = new ArrayList<Usuario>();
        Usuario user = new Usuario("Administrador","admin@teste.com","admin");
        Usuario user2 = new Usuario("João","joao@teste.com","123");
        Usuario user3 = new Usuario("Maria","maria@teste.com","123");
        usuarios.add(user);
        usuarios.add(user2);
        usuarios.add(user3);

        //pegar usuario
        Intent intent = getIntent();
        usuarioAtual = (Usuario) intent.getSerializableExtra("usuario");

        criarPerguntas();
        //fazer a parte do list view
        ListView lstPerguntas = (ListView) findViewById(R.id.lstPerguntasHome);
        AdapterPerguntas adapterPerguntas = new AdapterPerguntas(perguntas, this);

        lstPerguntas.setAdapter(adapterPerguntas);
    }

    public void fazerPergunta(View view) {
        Intent intent = new Intent(this, FazerPerguntaActivity.class);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }
}
