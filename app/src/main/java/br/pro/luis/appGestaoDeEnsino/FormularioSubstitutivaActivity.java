package br.pro.luis.appGestaoDeEnsino;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;

public class FormularioSubstitutivaActivity extends AppCompatActivity {
    private EditText etNomeAluno;
    private EditText etMatricula;
    private EditText etNota1;
    private EditText etNota2;
    private EditText etNotaFinal;
    private Button btnSalvar;
    private String tvStatus;
    private String acao;
    private GestaoDeEnsino gestaoDeEnsino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_substitutiva);
        etNomeAluno = findViewById( R.id.etNomeAluno);
        etMatricula = findViewById( R.id.etMatricula);
        etNota1 = findViewById( R.id.etNota1);
        etNota2 = findViewById( R.id.etNota2);

        btnSalvar = findViewById( R.id.btnSalvar );
        acao = getIntent().getStringExtra("acao");

        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario(){
        int idGestaoDeEnsino = getIntent().getIntExtra("idGestaoDeEnsino", 0);
        if( idGestaoDeEnsino != 0) {
            gestaoDeEnsino = GestaoDeEnsinoDAO.getPetById(this, idGestaoDeEnsino);
            etNomeAluno.setText( gestaoDeEnsino.nomeAluno);
            etMatricula.setText( gestaoDeEnsino.matricula);

            etNota1.setText("");
            etNota2.setText("");
//            etNota1.setText(   String.valueOf(gestaoDeEnsino.nota1));
//            etNota2.setText(   String.valueOf(gestaoDeEnsino.nota2));
        }
    }

    @SuppressLint("ResourceType")
    private void salvar(){
        if(  etNomeAluno.getText().toString().isEmpty()|| etMatricula.getText().toString().isEmpty() || etNota1.getText().toString().isEmpty() || etNota2.getText().toString().isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_input_delete);
            alerta.setTitle(R.string.txtAtencao);
            alerta.setMessage(R.string.txtPreeTdsCampos);
            alerta.setNeutralButton(R.string.txtEntendiClicAqui, null);
            alerta.show();
        }else{



            gestaoDeEnsino.nomeAluno = etNomeAluno.getText().toString();
            gestaoDeEnsino.matricula = etMatricula.getText().toString();
            gestaoDeEnsino.nota1 = Integer.valueOf(etNota1.getText().toString());
            gestaoDeEnsino.nota2 = Integer.valueOf(etNota2.getText().toString());

            double valor = (gestaoDeEnsino.nota1 * 0.4) + (gestaoDeEnsino.nota2 * 0.6);

            gestaoDeEnsino.notaFinal = Math.floor(valor);
            if(valor > 8){
                tvStatus = "Excelente";
            }
            if(valor >= 6 && valor <=8){
                tvStatus = "Aprovado";
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
            }
            if(valor < 6){
                tvStatus = "Reprovado";

            }
            gestaoDeEnsino.status = tvStatus;
            gestaoDeEnsino.substitutiva = "S";
//            if(acao.equals("editar") && tvStatus.equals("Reprovado")){
//                Intent intent = new Intent(FormularioActivity.this, FormularioSubstitutivaActivity.class);
//            }


            if( acao.equals("editar")){
                GestaoDeEnsinoDAO.editar(gestaoDeEnsino, this);
                finish();
            }else {
                GestaoDeEnsinoDAO.inserir(gestaoDeEnsino, this);
                etNomeAluno.setText("");
                etMatricula.setText("");
                etNota1.setText("");
                etNota2.setText("");
            }
        }
    }
}