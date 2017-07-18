package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import classes.Pergunta;
import classes.Resposta;
import classes.Usuario;

public class DetalhePerguntaActivity extends AppCompatActivity {
    private List<Resposta> respostas = new ArrayList<>();
    Pergunta pergSelecionada;
    Usuario usuarioAtual;

    public void criarRespostas() {
        Usuario usuario = new Usuario("pudim","g@g", "dssdds");
        Resposta r11 = new Resposta("A raiz quadrada é aproximadamente 32,7", 2,0,usuario,1);
        Resposta r21 = new Resposta("Segundo psicólogos a cor mais bonita é rosa", 5,2,usuario,2);
        Resposta r22 = new Resposta("A cor mais bonita é azul", 2,1,usuario,2);
        respostas.add(r11);
        respostas.add(r21);
        respostas.add(r22);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe__pergunta);

        Intent intent = getIntent();

        pergSelecionada = (Pergunta) intent.getParcelableExtra("pergunta");
        usuarioAtual = (Usuario) intent.getParcelableExtra("usuario");

        TextView txtPergunta = (TextView) findViewById(R.id.txtPerguntaDesc);
        txtPergunta.setText(pergSelecionada.gettexto_perg());

        ListView lstRespostas = (ListView) findViewById(R.id.lstRespostas);
        List<Resposta> r2 = new ArrayList<>();
        criarRespostas();
        for (Resposta r: respostas) {
            System.out.println(pergSelecionada.getperg_id());
            if (r.getPergunta() == pergSelecionada.getperg_id()) {
                r2.add(r);
            }
        }
        TextView lblNumResp = (TextView) findViewById(R.id.numero_respostas);
        lblNumResp.setText(r2.size() + " resposta(s)");

        AdapterRespostas adapterRespostas = new AdapterRespostas(r2, this);
        lstRespostas.setAdapter(adapterRespostas);
    }

    public void responder(View view) {
        Intent intent = new Intent(this, ResponderPerguntaActivity.class);
        intent.putExtra("pergunta", pergSelecionada);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }
}
