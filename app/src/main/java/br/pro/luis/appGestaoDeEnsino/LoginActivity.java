package br.pro.luis.appGestaoDeEnsino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.pro.luis.appGestaoDeEnsino.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btLogin = (Button) findViewById(R.id.btLogin);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView usarname = findViewById(R.id.tLogin);
                TextView tSenha = findViewById(R.id.tSenha);
                String login = usarname.getText().toString();
                String senha = tSenha.getText().toString();
//                if (login.equals("luis") && senha.equals("123")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(LoginActivity.this, TenteNovamenteActivity.class);
//                    startActivity(intent);
//                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}