package br.pro.luis.appGestaoDeEnsino;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class VerificaPorcentagemAlunosRecuperacaoActivity extends AppCompatActivity {

    private ListView lvAlunoRecuperacao;
    private AdapterGestaoDeEnsino adapter;
    private Button btnQtdRecuperacao, btnQtdReprovado;
    private List<GestaoDeEnsino> listaGestaoDeEnsinos;
    private  Boolean verificaSeTem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifica_porcentagem_alunos_recuperacao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        lvAlunoRecuperacao = findViewById(R.id.lvAlunoRecuperacao);
        btnQtdRecuperacao = findViewById(R.id.btnQtdRecuperacao);
        btnQtdReprovado = findViewById(R.id.btnQtdReprovado);

        if(verificaSeTemAlgo()){
            setContentView(R.layout.activity_vazio);

        }else{
            carregarAluno();
            configurarListView();
        }
        btnQtdRecuperacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestaoDeEnsino gestaoDeEnsino = new GestaoDeEnsino();
                System.out.println("Clicou no recuperacao"+gestaoDeEnsino.getNomeAluno());
            }
        });

        btnQtdReprovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicou no reprovados");
            }
        });
    }

    private void carregaData(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        TextView txtView = new TextView(this);
        txtView.setText("Current Date and Time : "+formattedDate);
        txtView.setGravity(Gravity.CENTER);
        txtView.setTextSize(20);
        setContentView(txtView);
    }

    private void configurarListView() {

        lvAlunoRecuperacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GestaoDeEnsino gestaoDeEnsinoSelecionado = listaGestaoDeEnsinos.get(position);
                    Intent intent = new Intent(VerificaPorcentagemAlunosRecuperacaoActivity.this, FormularioSubstitutivaActivity.class);
                    intent.putExtra("acao", "editar");
                    intent.putExtra("idGestaoDeEnsino", gestaoDeEnsinoSelecionado.id);
                    startActivity(intent);

            }
        });

        lvAlunoRecuperacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                GestaoDeEnsino gestaoDeEnsinoSelecionado = listaGestaoDeEnsinos.get(position);
                excluirAluno(gestaoDeEnsinoSelecionado);
                return true;
            }
        });

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
                GestaoDeEnsinoDAO.excluir(gestaoDeEnsino.id, VerificaPorcentagemAlunosRecuperacaoActivity.this);
                carregarAluno();
            }
        });
        alerta.show();
    }

    private void avaliarPorcentagemDeRecuperacao() {

        Intent intent = new Intent(VerificaPorcentagemAlunosRecuperacaoActivity.this, LoginActivity.class);
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
        listaGestaoDeEnsinos = GestaoDeEnsinoDAO.getRecuperacao(this);
        adapter = new AdapterGestaoDeEnsino(this, listaGestaoDeEnsinos);
        lvAlunoRecuperacao.setAdapter(adapter);
    }

    private Boolean verificaSeTemAlgo() {
        listaGestaoDeEnsinos = GestaoDeEnsinoDAO.getRecuperacao(this);
        return verificaSeTem = listaGestaoDeEnsinos.isEmpty();
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