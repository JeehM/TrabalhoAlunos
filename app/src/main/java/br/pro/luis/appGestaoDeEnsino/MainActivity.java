package br.pro.luis.appGestaoDeEnsino;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAluno;
    //    private ArrayAdapter adapter;
    private AdapterGestaoDeEnsino adapter;
    private List<GestaoDeEnsino> listaGestaoDeEnsinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btVerificaRecuperacao = findViewById(R.id.btVerificaRecuperacao);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);
            }
        });

        btVerificaRecuperacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VerificaPorcentagemAlunosRecuperacaoActivity.class);
                startActivity(intent);
            }
        });

        lvAluno = findViewById(R.id.lvAluno);
        carregarAluno();
        configurarListView();
    }



    private void configurarListView() {

        lvAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GestaoDeEnsino gestaoDeEnsinoSelecionado = listaGestaoDeEnsinos.get(position);
                    Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                    intent.putExtra("acao", "editar");
                    intent.putExtra("idGestaoDeEnsino", gestaoDeEnsinoSelecionado.id);
                    startActivity(intent);
            }
        });

        lvAluno.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                GestaoDeEnsino gestaoDeEnsinoSelecionado = listaGestaoDeEnsinos.get(position);
                excluirAluno(gestaoDeEnsinoSelecionado);
                return true;
            }
        });

    }

    private void verificaSubstitutiva() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage(R.string.txtConfirmaRealizarSunstitutiva);
        alerta.setNeutralButton(R.string.txtCancela, null);
        alerta.setPositiveButton(R.string.txtSim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, FormularioSubstitutivaActivity.class);
                startActivity(intent);
            }
        });
        alerta.show();
    }

    private void excluirAluno(GestaoDeEnsino gestaoDeEnsino) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage(R.string.txtConfirmaExclusao);
        alerta.setNeutralButton(R.string.txtCancela, null);
        alerta.setPositiveButton(R.string.txtSim, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GestaoDeEnsinoDAO.excluir(gestaoDeEnsino.id, MainActivity.this);
                carregarAluno();
            }
        });
        alerta.show();
    }

    private void avaliarPorcentagemDeRecuperacao() {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarAluno();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void carregarAluno() {
        listaGestaoDeEnsinos = GestaoDeEnsinoDAO.getAluno(this);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaFilmes);
        adapter = new AdapterGestaoDeEnsino(this, listaGestaoDeEnsinos);
        lvAluno.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}