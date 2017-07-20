package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.Pergunta;
import classes.PerguntaService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FazerPerguntaActivity extends AppCompatActivity {
    Usuario usuarioAtual;
    private PerguntaService perguntaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pergunta);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        perguntaService = retrofit.create(PerguntaService.class);

        Intent intent = getIntent();
        usuarioAtual = intent.getParcelableExtra("usuario");
    }

    public void fazerPergunta(View view) {
        EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        EditText txtPerg = (EditText) findViewById(R.id.txtPergunta);

        Pergunta pergunta = new Pergunta(txtPerg.getText().toString(), txtTitulo.getText().toString(), usuarioAtual);

        Call<Pergunta> chamada1 = perguntaService.cadastrarPergunta(pergunta);
        chamada1.enqueue(new Callback<Pergunta>() {
            @Override
            public void onResponse(Call<Pergunta> call, Response<Pergunta> response) {
                Pergunta pergunta_servidor = response.body();
                /*Toast.makeText(getApplicationContext(), pergunta_servidor.gettexto_perg() + " " + pergunta_servidor.getDescricao(),
                        Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(FazerPerguntaActivity.this, MainActivity.class);
                intent.putExtra("usuario", usuarioAtual);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Pergunta> call, Throwable t) {
                Log.i("Erro", t.getMessage());
                Toast.makeText(getApplicationContext(), "Falha",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //adicionar a nova pergunta no banco
        //processar a extração de topicos


    }
}
