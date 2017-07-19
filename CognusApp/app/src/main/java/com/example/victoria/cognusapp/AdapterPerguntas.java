package com.example.victoria.cognusapp;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import classes.Pergunta;
import classes.Resposta;
import classes.Usuario;
import classes.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by victoria on 19/06/17.
 */

public class AdapterPerguntas extends BaseAdapter {
    private final List<Pergunta> perguntas;
    //private final List<Usuario> usuarios;
    private final Activity act;
    private UsuarioService usuarioService;
    TextView lblNomeUsuario;

    public AdapterPerguntas(List<Pergunta> perguntas, Activity act) {
        this.perguntas = perguntas;
        //this.usuarios = usuarios;
        this.act = act;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(act.getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usuarioService = retrofit.create(UsuarioService.class);
    }

    @Override
    public int getCount() {
        return perguntas.size();
    }

    @Override
    public Pergunta getItem(int position) {
        return perguntas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_layout_pergunta, parent, false);
        Pergunta pergunta = perguntas.get(position);

        TextView lblTags = (TextView) view.findViewById(R.id.tags);
        TextView lblPergunta = (TextView) view.findViewById(R.id.txtPerguntaDesc);
        lblNomeUsuario = (TextView) view.findViewById(R.id.nome_usuario_resp);
        TextView lblTextoPergunta = (TextView) view.findViewById(R.id.textoPergunta);

        String tags = "Tag1 Tag2 Tag3";
        lblTags.setText(tags);
        lblPergunta.setText(pergunta.getDescricao());
        lblTextoPergunta.setText(pergunta.gettexto_perg());


        buscarUsuario(pergunta.getuser_id().getUser_id());

        return view;
    }

    public void buscarUsuario(long id) {
        Call<Usuario> chamada = usuarioService.consultarUsuario(id);

        chamada.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario user1 = response.body();
                System.out.println(user1.getUser_name());
                lblNomeUsuario.setText(user1.getUser_name());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("Erro",t.getMessage());
            }
        });


    }
}
