package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import classes.Pergunta;
import classes.Topico;
import classes.TopicoList;
import classes.TopicoService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaTopicosActivity extends AppCompatActivity {
    TopicoList topicos;
    AdapterTopicosUser adapterTopicos;
    ListView listTopicos;
    Usuario userAtual;
    TopicoService topicoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_topicos);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Intent intent = getIntent();
        userAtual = intent.getParcelableExtra("usuario");

        topicoService = retrofit.create(TopicoService.class);
        listTopicos = (ListView) findViewById(R.id.lista_topicos);

        Call<TopicoList> chamada = topicoService.listarTopicos();
        chamada.enqueue(new Callback<TopicoList>() {
            @Override
            public void onResponse(Call<TopicoList> call, Response<TopicoList> response) {
                topicos = response.body();
                adapterTopicos = new AdapterTopicosUser((List<Topico>) topicos.getListaTopicos(), ListaTopicosActivity.this);
                listTopicos.setAdapter(adapterTopicos);

                listTopicos.setTextFilterEnabled(false);
            }

            @Override
            public void onFailure(Call<TopicoList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        listTopicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topicoSelecionado = topicos.get(position);
                Intent intent1 = new Intent(ListaTopicosActivity.this, FeedTopicosActivity.class);
                intent1.putExtra("topico",topicoSelecionado);
                startActivity(intent1);
            }
        });*/
    }
}
