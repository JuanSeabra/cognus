package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import classes.Pergunta;
import classes.Resposta;
import classes.RespostaList;
import classes.RespostaService;
import classes.Topico;
import classes.TopicoList;
import classes.TopicoService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalhePerguntaActivity extends AppCompatActivity {
    private List<Resposta> respostas;
    Pergunta pergSelecionada;
    Usuario usuarioAtual;
    private TopicoService topicoService;
    RespostaService respostaService;
    ListView lstRespostas;
    TextView lblNumResp;
    TextView lblTextoPergunta;
    TextView lblTags;
    TextView txtPergunta;

    public void obterRespostas() {
        Call<RespostaList> chamada = respostaService.listarRespostasPergunta(pergSelecionada.getperg_id());
        chamada.enqueue(new Callback<RespostaList>() {
            @Override
            public void onResponse(Call<RespostaList> call, Response<RespostaList> response) {
                RespostaList r = response.body();
                if (r != null) {
                    System.out.println("Teve respostas");
                    List<Resposta> respostas1 = r.getListRespostas();
                    if (respostas1.size() == 2) {
                        Resposta r1 = respostas1.get(0);
                        Resposta r2 = respostas1.get(1);
                        if (r1.getresp_id() == r2.getresp_id()) {
                            respostas1.remove(r1);
                        }
                    }
                    respostas = respostas1;
                }
                else {
                    System.out.println("Não teve respostas");
                    respostas = new ArrayList<Resposta>();
                }
                AdapterRespostas adapterRespostas = new AdapterRespostas(respostas, DetalhePerguntaActivity.this);
                lstRespostas.setAdapter(adapterRespostas);

                if (respostas != null)
                    lblNumResp.setText(respostas.size() + " resposta(s)");
                else {
                    respostas = new ArrayList<>();
                    lblNumResp.setText("0 respostas");
                }
            }

            @Override
            public void onFailure(Call<RespostaList> call, Throwable t) {
                Log.i("Erro", t.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe__pergunta);

        Intent intent = getIntent();
        usuarioAtual = intent.getParcelableExtra("usuario");

        pergSelecionada = intent.getParcelableExtra("pergunta");
        System.out.println(pergSelecionada.getperg_id());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        respostaService = retrofit.create(RespostaService.class);
        topicoService = retrofit.create(TopicoService.class);

        txtPergunta = (TextView) findViewById(R.id.txtPerguntaDesc);
        txtPergunta.setText(pergSelecionada.gettexto_perg());

        lblTextoPergunta = (TextView) findViewById(R.id.txtTituloPergunta);
        lblTextoPergunta.setText(pergSelecionada.getDescricao());

        lblTags = (TextView) findViewById(R.id.tags);
        buscarTopicosPergunta(pergSelecionada.getperg_id());


        lstRespostas = (ListView) findViewById(R.id.lstRespostas);

        //criarRespostas();

        lblNumResp = (TextView) findViewById(R.id.numero_respostas);
        if (respostas != null)
            lblNumResp.setText(respostas.size() + " resposta(s)");
        else {
            respostas = new ArrayList<>();
            lblNumResp.setText("0 respostas");
        }

        AdapterRespostas adapterRespostas = new AdapterRespostas(respostas, this);
        lstRespostas.setAdapter(adapterRespostas);

        obterRespostas();

    }

    public void responder(View view) {
        Intent intent = new Intent(this, ResponderPerguntaActivity.class);
        intent.putExtra("pergunta", pergSelecionada);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        usuarioAtual = intent.getParcelableExtra("usuario");

        pergSelecionada = intent.getParcelableExtra("pergunta");
        obterRespostas();
    }

    public void buscarTopicosPergunta(long id) {
        Call<TopicoList> chamada = topicoService.listarTopicoPergunta(id);
        chamada.enqueue(new Callback<TopicoList>() {
            @Override
            public void onResponse(Call<TopicoList> call, Response<TopicoList> response) {
                TopicoList topicoList = response.body();
                if (topicoList != null) {
                    List<Topico> topicos = topicoList.getListaTopicos();
                    if (topicos.size() == 2) {
                        Topico t = topicos.get(0);
                        Topico t2 = topicos.get(1);
                        if (t.gettopico_id() == t2.gettopico_id()) {
                            topicos.remove(t2);
                        }
                        topicoList.setListaTopicos(topicos);
                    }

                    String txtTopicos = "";
                    for (Topico t: topicoList.getListaTopicos() ) {
                        txtTopicos += (t.getdescricao_topico() + " ");
                    }

                    lblTags.setText(txtTopicos);
                }
                else {
                    lblTags.setText("Sem tags");
                }

            }

            @Override
            public void onFailure(Call<TopicoList> call, Throwable t) {

            }
        });
    }
}
