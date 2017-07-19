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
    RespostaService respostaService;

    public void obterRespostas() {
        Call<RespostaList> chamada = respostaService.listarRespostasPergunta(pergSelecionada.getperg_id());
        chamada.enqueue(new Callback<RespostaList>() {
            @Override
            public void onResponse(Call<RespostaList> call, Response<RespostaList> response) {
                RespostaList r = response.body();
                if (r != null) {
                    System.out.println("Teve respostas");
                    respostas = r.getListRespostas();
                }
                else {
                    System.out.println("Não teve respostas");
                    respostas = new ArrayList<Resposta>();
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

        TextView txtPergunta = (TextView) findViewById(R.id.txtPerguntaDesc);
        txtPergunta.setText(pergSelecionada.gettexto_perg());

        ListView lstRespostas = (ListView) findViewById(R.id.lstRespostas);

        //criarRespostas();
        obterRespostas();
        TextView lblNumResp = (TextView) findViewById(R.id.numero_respostas);
        if (respostas != null)
            lblNumResp.setText(respostas.size() + " resposta(s)");
        else {
            respostas = new ArrayList<>();
            lblNumResp.setText("0 respostas");
        }

        AdapterRespostas adapterRespostas = new AdapterRespostas(respostas, this);
        lstRespostas.setAdapter(adapterRespostas);
    }

    public void responder(View view) {
        Intent intent = new Intent(this, ResponderPerguntaActivity.class);
        intent.putExtra("pergunta", pergSelecionada);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }
}
