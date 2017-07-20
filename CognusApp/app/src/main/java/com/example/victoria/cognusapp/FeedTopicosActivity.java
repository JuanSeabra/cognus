package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import classes.Pergunta;
import classes.PerguntaList;
import classes.PerguntaService;
import classes.Resposta;
import classes.Topico;
import classes.Usuario;
import classes.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedTopicosActivity extends AppCompatActivity {
    Usuario userAtual;
    Topico topico;

    private List<Pergunta> perguntas = new ArrayList<>();
    PerguntaList perguntaList;

    private PerguntaService perguntaService;
    private UsuarioService usuarioService;

    TextView lblTopico;
    ListView lstPerguntas;
    AdapterPerguntas adapterPerguntas;

    private Pergunta p1;
    private Pergunta p2;
    private Resposta r11;
    private Resposta r21;
    private Resposta r22;

    public void criarPerguntas() {
        Usuario usuario = new Usuario("pudim","g@g", "dssdds");
        p1 = new Pergunta("Qual a raiz de 1069?", "",1,usuario);
        p2 = new Pergunta("Qual a cor mais bonita do mundo?", "",2,usuario);
        perguntas.add(p1);
        perguntas.add(p2);
    }

    public void obterPerguntas(long id) {
        System.out.println("Obtendo perguntas");
        Call<PerguntaList> chamada = perguntaService.listarPerguntasTopico(id);
        chamada.enqueue(new Callback<PerguntaList>() {
            @Override
            public void onResponse(Call<PerguntaList> call, Response<PerguntaList> response) {
                perguntaList = response.body();
                if (perguntaList != null) {
                    System.out.println("Tem perguntas");
                    List<Pergunta> perguntas = perguntaList.getListaPerguntas();

                    if (perguntas.size() == 2) {
                        Pergunta p = perguntas.get(0);
                        Pergunta p2 = perguntas.get(1);

                        if (p.getperg_id() == p2.getperg_id()) {
                            perguntas.remove(p2);
                        }
                    }
                    perguntaList.setListaPerguntas(perguntas);
                }
                else {
                    perguntaList = new PerguntaList();
                    perguntaList.setListaPerguntas(new ArrayList<Pergunta>());
                }


                perguntaList.setListaPerguntas(perguntaList.getListaPerguntas());
                adapterPerguntas = new AdapterPerguntas(perguntaList.getListaPerguntas(), FeedTopicosActivity.this);

                lstPerguntas.setAdapter(adapterPerguntas);
            }

            @Override
            public void onFailure(Call<PerguntaList> call, Throwable t) {
                Log.i("Erro", t.getMessage());
            }
        });
    }

   /* public void criarRespostas() {
        Usuario usuario = new Usuario("pudim","g@g", "dssdds");
        r11 = new Resposta("A raiz quadrada é aproximadamente 32,7", 2,0,usuario,1);
        r21 = new Resposta("Segundo psicólogos a cor mais bonita é rosa", 5,2,usuario,2);
        r22 = new Resposta("A cor mais bonita é azul", 2,1,usuario,2);
        respostas.add(r11);
        respostas.add(r21);
        respostas.add(r22);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_topicos);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        perguntaService = retrofit.create(PerguntaService.class);
        usuarioService = retrofit.create(UsuarioService.class);

        //pegar usuario
        Intent intent = getIntent();
        topico = intent.getParcelableExtra("topico");
        userAtual = intent.getParcelableExtra("usuario");

        lblTopico = (TextView) findViewById(R.id.topico);
        lblTopico.setText(topico.getdescricao_topico());

        criarPerguntas();
        //criarRespostas();
        //fazer a parte do list view
        lstPerguntas = (ListView) findViewById(R.id.perguntou);
        perguntaList = new PerguntaList();
        perguntaList.setListaPerguntas(new ArrayList<Pergunta>());
        adapterPerguntas = new AdapterPerguntas(perguntaList.getListaPerguntas(), FeedTopicosActivity.this);

        lstPerguntas.setAdapter(adapterPerguntas);

        lstPerguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pergunta pergSelecionada = (perguntaList.getListaPerguntas()).get(position);
                Intent intent1 = new Intent(FeedTopicosActivity.this, DetalhePerguntaActivity.class);
                intent1.putExtra("usuario", userAtual);
                System.out.println(userAtual);
                intent1.putExtra("pergunta",pergSelecionada);
                System.out.println("Navegando: " + pergSelecionada.getperg_id());
                startActivity(intent1);
            }
        });

        obterPerguntas(topico.gettopico_id());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (topico != null)
            obterPerguntas(topico.gettopico_id());
    }
}
