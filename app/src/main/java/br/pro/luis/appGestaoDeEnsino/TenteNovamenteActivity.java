package br.pro.luis.appGestaoDeEnsino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.pro.luis.appGestaoDeEnsino.R;

public class TenteNovamenteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erro_senha);

        Button btTenteNovamente = (Button)findViewById(R.id.btTenteNovamente);

        btTenteNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TenteNovamenteActivity.this, LoginActivity.class);
                startActivity( intent );
            }
        });
    }
}