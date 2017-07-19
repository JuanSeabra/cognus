package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import classes.Topico;
import classes.TopicoList;
import classes.TopicoService;
import classes.UsuarioService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {
    TopicoList topicos;
    Usuario usuarioAtual;
    AdapterTopicos adapterTopicos;
    android.widget.SearchView mSearchView;
    ListView listTopicos;
    UsuarioService usuarioService;
    TopicoService topicoService;
    boolean ok = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Intent intent = getIntent();
        usuarioAtual = intent.getParcelableExtra("usuario");
        topicos = new TopicoList();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usuarioService = retrofit.create(UsuarioService.class);
        topicoService = retrofit.create(TopicoService.class);

        listTopicos = (ListView) findViewById(R.id.listTopicos);
        obterTopicos();

        mSearchView=(android.widget.SearchView) findViewById(R.id.buscar_topicos);

        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        //mSearchView.setSubmitButtonEnabled(true);
        //mSearchView.setQueryHint("Procure mais categorias");
    }

    public void finalizarRegistro(View view) {
        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        if (txtNome.getText().length() > 0) {
            //inserir o novo usuário no banco de dados
            //pegar os topicos escolhidos
            usuarioAtual.setUser_name(txtNome.getText().toString());

            //getTopicosdeInteresse.

            List<Topico> topicosSelecionados= adapterTopicos.getTopicosSelecionados();
            Toast.makeText(getApplicationContext(), topicosSelecionados.get(0).getdescricao_topico(),
                    Toast.LENGTH_SHORT).show();
            usuarioAtual.setListTopicos(topicosSelecionados);

            registrarUsuario(usuarioAtual);
        }
    }

    private void registrarUsuario(final Usuario usuarioAtual) {
        System.out.println("Cadastro Usuario");

        Call<Usuario> chamada1 = usuarioService.cadastrarUsuario(usuarioAtual);
        chamada1.enqueue(new Callback<Usuario>() {

            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
               // try {
                    Usuario user = response.body();
                    //proxima pagina
                    Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                    //tem que ser o user que o maroo manda
                    intent.putExtra("usuario", user);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                //}
                /*catch (Exception e) {
                    Log.i("Erro", e.getMessage());
                }*/

            }

            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("Erro", t.getMessage());
                Toast.makeText(getApplicationContext(), "Erro",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void obterTopicos() {
        System.out.println("Obtendo tópicos");
        Call<TopicoList> chamada = topicoService.listarTopicos();
        chamada.enqueue(new Callback<TopicoList>() {
            @Override
            public void onResponse(Call<TopicoList> call, Response<TopicoList> response) {
                topicos = response.body();
                List<Topico> topicos2 = topicos.getListaTopicos();

                if (topicos2.size() == 2) {
                    Topico t = topicos2.get(0);
                    Topico t2 = topicos2.get(1);
                    if (t.gettopico_id() == t2.gettopico_id()) {
                        topicos2.remove(t2);
                    }
                    topicos.setListaTopicos(topicos2);
                }

                adapterTopicos = new AdapterTopicos(topicos.getListaTopicos(), RegistrarActivity.this);
                listTopicos.setAdapter(adapterTopicos);

                listTopicos.setTextFilterEnabled(false);
            }

            @Override
            public void onFailure(Call<TopicoList> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapterTopicos.getFilter().filter(newText);
        return true;
    }
}
