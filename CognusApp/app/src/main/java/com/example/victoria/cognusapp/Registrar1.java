package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.Usuario;
import classes.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrar1 extends AppCompatActivity {
    // UI references.
    private EditText txtEmail;
    private EditText txtConfirmarEmail;
    private EditText txtSenha;
    private EditText txtConfirmarSenha;
    private UsuarioService usuarioService;
    private Usuario userAtual;
    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.ip_requisicao))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usuarioService = retrofit.create(UsuarioService.class);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 0;
    }


    public void proximoPasso(View view) {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtConfirmarEmail = (EditText) findViewById(R.id.txtConfirmarEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtConfirmarSenha = (EditText) findViewById(R.id.txtConfirmarSenha);

        txtEmail.setError(null);
        txtConfirmarEmail.setError(null);
        txtSenha.setError(null);
        txtConfirmarSenha.setError(null);

        email = txtEmail.getText().toString();
        String confEmail = txtConfirmarEmail.getText().toString();
        senha = txtSenha.getText().toString();
        String confSenha = txtConfirmarSenha.getText().toString();

        //verificar se nao tem nenhum campo em branco
        if (!isEmailValid(email)) {
            txtEmail.setError(getString(R.string.error_invalid_email));
            txtEmail.requestFocus();
        }
        if (!isPasswordValid(senha)) {
            txtSenha.setError(getString(R.string.error_invalid_password));
            txtSenha.requestFocus();
        }
        if (!email.equals(confEmail)) {
            txtEmail.setError("E-mails não compatíveis!");
            txtConfirmarEmail.setError("E-mails não compatíveis!");
            txtEmail.requestFocus();
        }
        else if (!senha.equals(confSenha)) {
            txtSenha.setError("Senhas não compatíveis!");
            txtConfirmarSenha.setError("Senhas não compatíveis!");
            txtSenha.requestFocus();
        }
        else {
            Call<Usuario> chamada1 = usuarioService.buscarUsuarioEmail(email);
            chamada1.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    userAtual = response.body();
                    if(userAtual != null){
                        Toast.makeText(getApplicationContext(), "Endereço de e-mail já existe.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        userAtual = new Usuario(email, senha);
                        //pode navegar para a proxima tela
                        Intent intent = new Intent(getBaseContext(), RegistrarActivity.class);
                        intent.putExtra("usuario", userAtual);
                        //intent.putExtra("senha", senha);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
