package com.example.victoria.cognusapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    boolean face;
    Profile perfil;
    EditText txtNome;
    ImageView imgFoto;

    /*protected void criarTopicos() {
        topicos = new ArrayList<String>();
        topicos.add("Matemática");
        topicos.add("Música");
        topicos.add("Ciência");
        topicos.add("Tecnologia");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Intent intent = getIntent();
        usuarioAtual = intent.getParcelableExtra("usuario");
        face = intent.getBooleanExtra("face", false);

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
        txtNome = (EditText) findViewById(R.id.txtNome);
        imgFoto = (ImageView) findViewById(R.id.foto);
        if (face) {
            perfil = Profile.getCurrentProfile();
            imgFoto.setImageURI(perfil.getProfilePictureUri(110,110));
            txtNome.setText(perfil.getName());
        }
    }

    public void finalizarRegistro(View view) {

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
               try {
                    Usuario user = response.body();
                    //proxima pagina
                    Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                    //tem que ser o user que o maroo manda
                    intent.putExtra("usuario", user);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Log.i("Erro", e.getMessage());
                }
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
                try {
                    topicos = response.body();
                }
                catch(Exception e) {
                    topicos = new TopicoList();
                }
                finally {
                    adapterTopicos = new AdapterTopicos(topicos.getListaTopicos(), RegistrarActivity.this);
                    listTopicos.setAdapter(adapterTopicos);

                    listTopicos.setTextFilterEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<TopicoList> call, Throwable t) {
                Log.i("Erro", t.getMessage());
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
