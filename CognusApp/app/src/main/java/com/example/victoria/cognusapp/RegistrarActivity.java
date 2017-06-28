package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import classes.Usuario;

public class RegistrarActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {
    ArrayList<String> topicos;
    Usuario usuarioAtual;
    AdapterTopicos adapterTopicos;
    android.widget.SearchView mSearchView;
    ListView listTopicos;

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

        listTopicos = (ListView) findViewById(R.id.listTopicos);
        criarTopicos();
        adapterTopicos = new AdapterTopicos(topicos, this);
        listTopicos.setAdapter(adapterTopicos);

        listTopicos.setTextFilterEnabled(false);

        mSearchView=(android.widget.SearchView) findViewById(R.id.buscar_topicos);

        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        //mSearchView.setSubmitButtonEnabled(true);
        //mSearchView.setQueryHint("Procure mais categorias");
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapterTopicos.getFilter().filter(newText);
        return true;
    }
}
