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

import classes.UsuarioService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {
    ArrayList<String> topicos;
    Usuario usuarioAtual;
    AdapterTopicos adapterTopicos;
    android.widget.SearchView mSearchView;
    ListView listTopicos;
    UsuarioService usuarioService;

    protected void criarTopicos() {
        topicos = new ArrayList<String>();
        topicos.add("Matemática");
        topicos.add("Música");
        topicos.add("Ciência");
        topicos.add("Tecnologia");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Intent intent = getIntent();
        usuarioAtual = (Usuario) intent.getSerializableExtra("usuario");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usuarioService = retrofit.create(UsuarioService.class);

        listTopicos = (ListView) findViewById(R.id.listTopicos);
        criarTopicos();
        adapterTopicos = new AdapterTopicos(topicos, this);
        listTopicos.setAdapter(adapterTopicos);

        listTopicos.setTextFilterEnabled(false);

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

            registrarUsuario(usuarioAtual);

            //proxima pagina
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", usuarioAtual);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void registrarUsuario(Usuario usuarioAtual) {
        System.out.println("Requisicao");
        //System.out.println(usuarioAtual.getUser_name() + " " + usuarioAtual.getUser_email());
        Call<Usuario> chamada1 = usuarioService.cadastrarUsuario(usuarioAtual);
        chamada1.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario user = response.body();
                Toast.makeText(getApplicationContext(), user.getUser_name() + " " + user.getUser_id(),
                        Toast.LENGTH_SHORT).show();
                System.out.println("Resposta: " + user.getUser_email());
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.i("Erro", t.getMessage());
                Toast.makeText(getApplicationContext(), "Falha na conexão",
                        Toast.LENGTH_SHORT).show();
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
