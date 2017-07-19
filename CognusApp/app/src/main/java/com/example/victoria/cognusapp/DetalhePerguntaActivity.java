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

    /*public void criarRespostas() {
        Usuario usuario = new Usuario("pudim","g@g", "dssdds");
        Resposta r11 = new Resposta("A raiz quadrada é aproximadamente 32,7", 2,0,usuario,1);
        Resposta r21 = new Resposta("Segundo psicólogos a cor mais bonita é rosa", 5,2,usuario,2);
        Resposta r22 = new Resposta("A cor mais bonita é azul", 2,1,usuario,2);
        respostas.add(r11);
        respostas.add(r21);
        respostas.add(r22);
    }*/

    public void obterRespostas() {
        Call<RespostaList> chamada = respostaService.listarRespostasPergunta(Integer.toString(pergSelecionada.getperg_id()));
        chamada.enqueue(new Callback<RespostaList>() {
            @Override
            public void onResponse(Call<RespostaList> call, Response<RespostaList> response) {
                RespostaList r = response.body();
                if (r != null) {
                    respostas = r.getListRespostas();
                }
                else {
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

        pergSelecionada = intent.getParcelableExtra("pergunta");
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
        lblNumResp.setText(respostas.size() + " resposta(s)");

        AdapterRespostas adapterRespostas = new AdapterRespostas((List<Resposta>) respostas, this);
        lstRespostas.setAdapter(adapterRespostas);
    }

    public void responder(View view) {
        Intent intent = new Intent(this, ResponderPerguntaActivity.class);
        intent.putExtra("pergunta", pergSelecionada);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }
}
