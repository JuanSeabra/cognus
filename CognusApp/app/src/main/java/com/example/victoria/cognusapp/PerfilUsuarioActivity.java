package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class PerfilUsuarioActivity extends AppCompatActivity {
    Usuario userAtual;
    TopicoList topicos;
    AdapterTopicosUser adapterTopicos;
    ListView listTopicos;
    private TopicoService topicoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        topicoService = retrofit.create(TopicoService.class);
        Intent intent = getIntent();
        userAtual = intent.getParcelableExtra("usuario");

        TextView user_name = (TextView) findViewById(R.id.nome_usuario);
        TextView user_email = (TextView) findViewById(R.id.email);

        user_name.setText(userAtual.getUser_name());
        user_email.setText(userAtual.getUser_email());

        listTopicos = (ListView) findViewById(R.id.lista_topicos);
        Call<TopicoList> chamada = topicoService.listarTopicosUsuario(userAtual.getUser_id());
        chamada.enqueue(new Callback<TopicoList>() {
            @Override
            public void onResponse(Call<TopicoList> call, Response<TopicoList> response) {
                topicos = response.body();

                if (topicos != null) {
                    System.out.println("Teve topicos");
                    List<Topico> listaTopicos = topicos.getListaTopicos();
                    if (listaTopicos.size() == 2) {
                        Topico t1 = listaTopicos.get(0);
                        Topico t2 = listaTopicos.get(1);

                        if (t1.gettopico_id() == t2.gettopico_id()) {
                            listaTopicos.remove(t1);
                        }
                    }
                    topicos.setListaTopicos(listaTopicos);
                }
                else {
                    topicos = new TopicoList();
                    topicos.setListaTopicos(new ArrayList<Topico>());
                }
                adapterTopicos = new AdapterTopicosUser(topicos.getListaTopicos(), PerfilUsuarioActivity.this);
                listTopicos.setAdapter(adapterTopicos);

                listTopicos.setTextFilterEnabled(false);
            }

            @Override
            public void onFailure(Call<TopicoList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
        listTopicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Topico topicoSelecionado = topicos.getListaTopicos().get(position);
                Intent intent1 = new Intent(PerfilUsuarioActivity.this, FeedTopicosActivity.class);
                intent1.putExtra("topico",topicoSelecionado);
                startActivity(intent1);
            }
        });
    }
}
