package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import classes.Pergunta;
import classes.Resposta;
import classes.RespostaService;
import classes.Usuario;
import classes.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResponderPerguntaActivity extends AppCompatActivity {
    Usuario userAtual;
    Pergunta pergunta;
    UsuarioService usuarioService;
    RespostaService respostaService;
    TextView txtPerg;
    TextView txtResposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_pergunta);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        respostaService = retrofit.create(RespostaService.class);

        Intent intent = getIntent();
        pergunta = intent.getParcelableExtra("pergunta");
        userAtual = intent.getParcelableExtra("usuario");

        txtPerg = (TextView) findViewById(R.id.txtPerguntaDesc);
        txtPerg.setText(pergunta.getDescricao());

        txtResposta = (TextView) findViewById(R.id.textoPergunta);
    }

    public void responder(View view) {

        String texto = txtResposta.getText().toString();
        if (texto != "") {
            System.out.println(userAtual.toString());
            System.out.println(pergunta.getperg_id());
            Resposta resposta = new Resposta(texto, 0, 0, userAtual.getUser_id(), pergunta.getperg_id());

            System.out.println("RESPOSTA");

            System.out.println(resposta.getuser_id());

            Call<Resposta> chamada = respostaService.cadastrarResposta(resposta);
            chamada.enqueue(new Callback<Resposta>() {
                @Override
                public void onResponse(Call<Resposta> call, Response<Resposta> response) {
                    Resposta retorno = response.body();
                    if (retorno != null) {
                        Intent intent = new Intent(ResponderPerguntaActivity.this, DetalhePerguntaActivity.class);
                        intent.putExtra("pergunta", pergunta);
                        intent.putExtra("usuario", userAtual);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Resposta> call, Throwable t) {
                    Log.i("Erro",t.getMessage());
                }
            });


        }
        else {
            //colocar um Toast aqui
        }

    }
}
