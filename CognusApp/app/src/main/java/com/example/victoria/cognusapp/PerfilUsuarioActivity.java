package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PerfilUsuarioActivity extends AppCompatActivity {

    ArrayList<String> topicos;
    AdapterTopicos adapterTopicos;
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
        setContentView(R.layout.activity_perfil_usuario);

        /*

        criarTopicos();

        listTopicos = (ListView) findViewById(R.id.lista_topicos);
        adapterTopicos = new AdapterTopicos(topicos, this);
        listTopicos.setAdapter(adapterTopicos);

        listTopicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topicoSelecionado = topicos.get(position);
                Intent intent1 = new Intent(PerfilUsuarioActivity.this, FeedTopicosActivity.class);
                intent1.putExtra("topico",topicoSelecionado);
                startActivity(intent1);
            }
        });*/
    }
}
