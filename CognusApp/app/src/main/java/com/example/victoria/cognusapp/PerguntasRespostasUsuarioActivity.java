package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import classes.Usuario;

public class PerguntasRespostasUsuarioActivity extends AppCompatActivity {
    Usuario userAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas_respostas_usuario);

        Intent intent = getIntent();
        userAtual = intent.getParcelableExtra("usuario");
    }
}
