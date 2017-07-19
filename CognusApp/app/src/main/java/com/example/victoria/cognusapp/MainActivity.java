package com.example.victoria.cognusapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {Usuario usuarioAtual;
    private List<Usuario> usuarios;
    private List<Pergunta> perguntas = new ArrayList<>();
    private List<Resposta> respostas = new ArrayList<>();
    private Pergunta p1;
    private Pergunta p2;
    private Resposta r11;
    private Resposta r21;
    private Resposta r22;
    private PerguntaService perguntaService;
    private AdapterPerguntas adapterPerguntas;
    private ListView lstPerguntas;

    private CallbackManager mFacebookCallbackManager;

    public void criarPerguntas() {
        p1 = new Pergunta("Qual a raiz de 1069?", "",1,usuarioAtual);
        p2 = new Pergunta("Qual a cor mais bonita do mundo?", "",2,usuarioAtual);
        perguntas.add(p1);
        perguntas.add(p2);
    }

    public void criarRespostas() {
        r11 = new Resposta("A raiz quadrada é aproximadamente 32,7", 2,0,usuarioAtual,1);
        r21 = new Resposta("Segundo psicólogos a cor mais bonita é rosa", 5,2,usuarioAtual,2);
        r22 = new Resposta("A cor mais bonita é azul", 2,1,usuarioAtual,2);
        respostas.add(r11);
        respostas.add(r21);
        respostas.add(r22);
    }

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

        usuarios = new ArrayList<Usuario>();
        Usuario user = new Usuario("Administrador","admin@teste.com","admin",1);
        Usuario user2 = new Usuario("João","joao@teste.com","123",2);
        Usuario user3 = new Usuario("Maria","maria@teste.com","123",3);
        usuarios.add(user);
        usuarios.add(user2);
        usuarios.add(user3);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        perguntaService = retrofit.create(PerguntaService.class);

        //pegar usuario
        Intent intent = getIntent();
        usuarioAtual = intent.getParcelableExtra("usuario");
        System.out.println(usuarioAtual.toString());

        criarPerguntas();
        criarRespostas();
        //fazer a parte do list view
        lstPerguntas = (ListView) findViewById(R.id.lstPerguntasHome);
        //pegar aqui as perguntas
        obterPerguntas();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            startActivity(intent);
        } else if (id == R.id.nav_feed) {

        } else if (id == R.id.nav_topicos) {
            Intent intent = new Intent(this, ListaTopicosActivity.class);
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
                System.out.println(Profile.getCurrentProfile().getName() );
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

    public void obterPerguntas() {
        System.out.println("Obtendo perguntas");
        Call<PerguntaList> chamada = perguntaService.listarPerguntas();
        chamada.enqueue(new Callback<PerguntaList>() {
            @Override
            public void onResponse(Call<PerguntaList> call, Response<PerguntaList> response) {
                PerguntaList perguntaList = response.body();
                adapterPerguntas = new AdapterPerguntas(perguntaList.getListaPerguntas(), respostas, usuarios, MainActivity.this);

                lstPerguntas.setAdapter(adapterPerguntas);

                lstPerguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Pergunta pergSelecionada = perguntas.get(position);
                        Intent intent1 = new Intent(MainActivity.this, DetalhePerguntaActivity.class);
                        intent1.putExtra("usuario", usuarioAtual);
                        intent1.putExtra("pergunta",pergSelecionada);
                        startActivity(intent1);
                    }
                });

            }

            @Override
            public void onFailure(Call<PerguntaList> call, Throwable t) {

            }
        });
    }
}
