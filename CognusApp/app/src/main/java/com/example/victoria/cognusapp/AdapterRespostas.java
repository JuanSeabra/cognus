package com.example.victoria.cognusapp;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import classes.Resposta;
import classes.TopicoService;
import classes.Usuario;
import classes.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by victoria on 21/06/17.
 */

public class AdapterRespostas extends BaseAdapter {
    private final List<Resposta> respostas;
    private UsuarioService usuarioService;
    TextView lblNomeUsuario;
    TextView lblResposta;
    /*
    private final String[] usuarios = new String[] {
            "João", "José", "Maria"
    };*/
    private final Activity act;

    public AdapterRespostas(List<Resposta> respostas, Activity act) {
        this.respostas = respostas;
        this.act = act;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(act.getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usuarioService = retrofit.create(UsuarioService.class);
    }

    @Override
    public int getCount() {
        return respostas.size();
    }

    @Override
    public Object getItem(int position) {
        return respostas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.activity_layout_resposta, parent, false);
        Resposta resp = respostas.get(position);

        lblNomeUsuario = (TextView) view.findViewById(R.id.nome_usuario_resp);
        lblResposta = (TextView) view.findViewById(R.id.textoPergunta);

        buscarUsuario(resp.getuser_id());
        lblResposta.setText(resp.gettexto_resp());
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
