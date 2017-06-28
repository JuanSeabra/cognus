package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import classes.Pergunta;

public class ResponderPerguntaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_pergunta);

        Intent intent = getIntent();
        Pergunta pergunta = (Pergunta) intent.getSerializableExtra("pergunta");

        TextView txtPerg = (TextView) findViewById(R.id.txtPerguntaDesc);
        txtPerg.setText(pergunta.getTexto());
    }

    public void responder(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
