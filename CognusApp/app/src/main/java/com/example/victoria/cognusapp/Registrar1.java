package com.example.victoria.cognusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registrar1 extends AppCompatActivity {
    // UI references.
    private EditText txtEmail;
    private EditText txtConfirmarEmail;
    private EditText txtSenha;
    private EditText txtConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar1);

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

        String email = txtEmail.getText().toString();
        String confEmail = txtConfirmarEmail.getText().toString();
        String senha = txtSenha.getText().toString();
        String confSenha = txtConfirmarSenha.getText().toString();

        //verificar se nao tem nenhum campo em branco
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
            //pode navegar para a proxima tela
            Intent intent = new Intent(this, MainActivity.class);
            /*intent.putExtra("email", email);
            intent.putExtra("senha", senha);*/
            startActivity(intent);
        }
    }
}
