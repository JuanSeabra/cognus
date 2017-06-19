package com.example.victoria.cognusapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import classes.Pergunta;

/**
 * Created by victoria on 19/06/17.
 */

public class AdapterPerguntas extends BaseAdapter {
    private final List<Pergunta> perguntas;
    private final Activity act;
    private final String[] responderam = new String[] {
            "João", "Maria", "José", "Mário"
    };
    private final String[] respostas = new String[] {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    };

    public AdapterPerguntas(List<Pergunta> perguntas, Activity act) {
        this.perguntas = perguntas;
        this.act = act;
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
        TextView lblPergunta = (TextView) view.findViewById(R.id.pergunta);
        TextView lblNomeUsuario = (TextView) view.findViewById(R.id.nome_usuario);
        TextView lblResposta = (TextView) view.findViewById(R.id.resposta);
        TextView lblNumResp = (TextView) view.findViewById(R.id.numero_respostas);

        String tags = "Tag1 Tag2 Tag3";
        lblTags.setText(tags);
        lblPergunta.setText(pergunta.getTexto());
        lblNomeUsuario.setText(responderam[position]);
        lblResposta.setText(respostas[position]);
        lblNumResp.setText("1 resposta");

        return view;
    }
}
