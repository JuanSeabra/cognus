package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import classes.Pergunta;
import classes.Usuario;

public class ResponderPerguntaActivity extends AppCompatActivity {
    Usuario userAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_pergunta);

        Intent intent = getIntent();
        Pergunta pergunta = intent.getParcelableExtra("pergunta");
        userAtual = intent.getParcelableExtra("usuario");

        TextView txtPerg = (TextView) findViewById(R.id.txtPerguntaDesc);
        txtPerg.setText(pergunta.gettexto_perg());
    }

    public void responder(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
