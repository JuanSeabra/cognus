package com.example.victoria.cognusapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import classes.Resposta;

/**
 * Created by victoria on 21/06/17.
 */

public class AdapterRespostas extends BaseAdapter {
    private final List<Resposta> respostas;
    private final String[] usuarios = new String[] {
            "João", "José", "Maria"
    };
    private final Activity act;

    public AdapterRespostas(List<Resposta> respostas, Activity act) {
        this.respostas = respostas;
        this.act = act;
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

        TextView lblNomeUsuario = (TextView) view.findViewById(R.id.nome_usuario_resp);
        TextView lblResposta = (TextView) view.findViewById(R.id.textoPergunta);

        String tags = "Tag1 Tag2 Tag3";
        lblNomeUsuario.setText(usuarios[position]);
        lblResposta.setText(respostas.get(position).gettexto_resp());
        return view;
    }
}
