package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import classes.Usuario;

public class RegistrarActivity extends AppCompatActivity {
    ArrayList<String> topicos;
    Usuario usuarioAtual;

    protected void criarTopicos() {
        topicos = new ArrayList<String>();
        topicos.add("Matemática");
        topicos.add("Música");
        topicos.add("Ciência");
        topicos.add("Tecnologia");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Intent intent = getIntent();
        usuarioAtual = (Usuario) intent.getSerializableExtra("usuario");

        ListView listTopicos = (ListView) findViewById(R.id.listTopicos);
        criarTopicos();
        AdapterTopicos adapterTopicos = new AdapterTopicos(topicos, this);
        listTopicos.setAdapter(adapterTopicos);
    }

    public void finalizarRegistro(View view) {
        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        if (txtNome.getText().length() > 0) {
            //inserir o novo usuário no banco de dados
            //pegar os topicos escolhidos
            usuarioAtual.setNome(txtNome.getText().toString());

            //proxima pagina
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", usuarioAtual);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
