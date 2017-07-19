package com.example.victoria.cognusapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;

import classes.Pergunta;
import classes.PerguntaList;
import classes.PerguntaService;
import classes.Resposta;
import classes.Topico;
import classes.UserList;
import classes.Usuario;
import classes.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Usuario usuarioAtual;
    private PerguntaService perguntaService;
    private UsuarioService usuarioService;
    private AdapterPerguntas adapterPerguntas;
    private ListView lstPerguntas;
    UserList userList;
    PerguntaList perguntaList;

    private CallbackManager mFacebookCallbackManager;

    public void fazerPergunta(View view) {
        Intent intent = new Intent(this, FazerPerguntaActivity.class);
        intent.putExtra("usuario", usuarioAtual);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        perguntaService = retrofit.create(PerguntaService.class);
        usuarioService = retrofit.create(UsuarioService.class);

        //obter usuarios
        //obterUsuarios();

        //pegar usuario
        Intent intent = getIntent();
        usuarioAtual = intent.getParcelableExtra("usuario");
        System.out.println(usuarioAtual.toString());

        //fazer a parte do list view
        lstPerguntas = (ListView) findViewById(R.id.lstPerguntasHome);
        //pegar aqui as perguntas
        perguntaList = new PerguntaList();
        perguntaList.setListaPerguntas(new ArrayList<Pergunta>());
        adapterPerguntas = new AdapterPerguntas(perguntaList.getListaPerguntas(), MainActivity.this);

        lstPerguntas.setAdapter(adapterPerguntas);

        lstPerguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pergunta pergSelecionada = (perguntaList.getListaPerguntas()).get(position);
                Intent intent1 = new Intent(MainActivity.this, DetalhePerguntaActivity.class);
                intent1.putExtra("usuario", usuarioAtual);
                System.out.println(usuarioAtual);
                intent1.putExtra("pergunta",pergSelecionada);
                startActivity(intent1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        obterPerguntas();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            // Handle the camera action
            Intent intent = new Intent(this, PerfilUsuarioActivity.class);
            intent.putExtra("usuario", usuarioAtual);
            startActivity(intent);
        } else if (id == R.id.nav_feed) {
            //intent.putExtra("usuario", usuarioAtual);
        } else if (id == R.id.nav_topicos) {
            Intent intent = new Intent(this, ListaTopicosActivity.class);
            intent.putExtra("usuario", usuarioAtual);
            startActivity(intent);
        } else if (id == R.id.logout) {
            //implementar o logout

            //FACEBOOK
           /* if (AccessToken.getCurrentAccessToken() != null) {

                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }*/
            if (Profile.getCurrentProfile() != null)
                System.out.println(Profile.getCurrentProfile().getName() + "Email: " );
            if (LoginManager.getInstance() != null)
                LoginManager.getInstance().logOut();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean obterPerguntas() {
        System.out.println("Obtendo perguntas");
        Call<PerguntaList> chamada = perguntaService.listarPerguntas();
        chamada.enqueue(new Callback<PerguntaList>() {
            @Override
            public void onResponse(Call<PerguntaList> call, Response<PerguntaList> response) {
                System.out.println("ON RESPONSE");
                perguntaList = response.body();
                if (perguntaList != null) {
                    List<Pergunta> perguntas = perguntaList.getListaPerguntas();

                    if (perguntas.size() == 2) {
                        Pergunta p = perguntas.get(0);
                        Pergunta p2 = perguntas.get(1);

                        if (p.getperg_id() == p2.getperg_id()) {
                            perguntas.remove(p2);
                        }
                    }
                }
                else {
                    perguntaList = new PerguntaList();
                    perguntaList.setListaPerguntas(new ArrayList<Pergunta>());
                }


                perguntaList.setListaPerguntas(perguntaList.getListaPerguntas());
                adapterPerguntas = new AdapterPerguntas(perguntaList.getListaPerguntas(), MainActivity.this);

                lstPerguntas.setAdapter(adapterPerguntas);
            }

            @Override
            public void onFailure(Call<PerguntaList> call, Throwable t) {
                Log.i("Erro", t.getMessage());
            }
        });
        return true;
    }
}
