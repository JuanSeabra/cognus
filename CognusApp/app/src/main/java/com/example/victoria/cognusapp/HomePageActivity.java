package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import classes.Pergunta;
import classes.Resposta;
import classes.Usuario;

public class HomePageActivity extends AppCompatActivity {
    Usuario usuarioAtual;
    private List<Usuario> usuarios;
    private List<Pergunta> perguntas = new ArrayList<>();
    private List<Resposta> respostas = new ArrayList<>();
    private Pergunta p1;
    private Pergunta p2;
    private Resposta r11;
    private Resposta r21;
    private Resposta r22;

    public void criarPerguntas() {
        p1 = new Pergunta("Qual a raiz de 1069?", "",1,3);
        p2 = new Pergunta("Qual a cor mais bonita do mundo?", "",2,2);
        perguntas.add(p1);
        perguntas.add(p2);
    }

    public void criarRespostas() {
        r11 = new Resposta("A raiz quadrada é aproximadamente 32,7", 2,0,1,1);
        r21 = new Resposta("Segundo psicólogos a cor mais bonita é rosa", 5,2,1,2);
        r22 = new Resposta("A cor mais bonita é azul", 2,1,3,2);
        respostas.add(r11);
        respostas.add(r21);
        respostas.add(r22);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        usuarios = new ArrayList<Usuario>();
        Usuario user = new Usuario("Administrador","admin@teste.com","admin",1);
        Usuario user2 = new Usuario("João","joao@teste.com","123",2);
        Usuario user3 = new Usuario("Maria","maria@teste.com","123",3);
        usuarios.add(user);
        usuarios.add(user2);
        usuarios.add(user3);

        //pegar usuario
        Intent intent = getIntent();
        usuarioAtual = (Usuario) intent.getSerializableExtra("usuario");

        criarPerguntas();
        criarRespostas();
        //fazer a parte do list view
        ListView lstPerguntas = (ListView) findViewById(R.id.lstPerguntasHome);
        AdapterPerguntas adapterPerguntas = new AdapterPerguntas(perguntas, respostas, usuarios, this);

        lstPerguntas.setAdapter(adapterPerguntas);
    }

    public void fazerPergunta(View view) {
        Intent intent = new Intent(this, FazerPerguntaActivity.class);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }
}
