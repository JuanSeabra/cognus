package com.example.victoria.cognusapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import classes.Pergunta;
import classes.Resposta;
import classes.Usuario;

/**
 * Created by victoria on 19/06/17.
 */

public class AdapterPerguntas extends BaseAdapter {
    private final List<Pergunta> perguntas;
    private final List<Resposta> respostas;
    private final List<Usuario> usuarios;
    private final Activity act;

    public AdapterPerguntas(List<Pergunta> perguntas, List<Resposta> respostas, List<Usuario> usuarios, Activity act) {
        this.perguntas = perguntas;
        this.respostas = respostas;
        this.usuarios = usuarios;
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
        Pergunta pergunta = perguntas.get(respostas.get(position).getPergunta()-1);

        TextView lblTags = (TextView) view.findViewById(R.id.tags);
        TextView lblPergunta = (TextView) view.findViewById(R.id.txtPerguntaDesc);
        TextView lblNomeUsuario = (TextView) view.findViewById(R.id.nome_usuario_resp);
        TextView lblResposta = (TextView) view.findViewById(R.id.resposta);
        TextView lblNumResp = (TextView) view.findViewById(R.id.numero_respostas);

        String tags = "Tag1 Tag2 Tag3";
        lblTags.setText(tags);
        lblPergunta.setText(pergunta.gettexto_perg());
        //System.out.println(usuarios.get(respostas.get(position).getuser_id()-1).getUser_name());
        lblNomeUsuario.setText("testando");
        lblResposta.setText(respostas.get(position).gettexto_resp());

        Integer cont = new Integer(0);
        for (int i = 0; i < respostas.size(); i++) {
            Resposta r = respostas.get(i);
            if (r.getPergunta() == pergunta.getperg_id())
                cont++;
        }
        lblNumResp.setText(cont.toString() + " resposta(s)");

        return view;
    }
}
