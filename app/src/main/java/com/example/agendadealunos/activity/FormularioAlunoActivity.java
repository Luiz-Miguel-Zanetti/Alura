package com.example.agendadealunos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agendadealunos.R;
import com.example.agendadealunos.data.AlunoDAO;
import com.example.agendadealunos.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    String TITULO_APP_BAR = "Novo aluno";
    EditText campoNome, campoTelefone, campoEmail;
    Button buttonSalvar;
    final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        //Configura o nome da AppBar
        setTitle(TITULO_APP_BAR);

        //Métodos
        inicializaComponentes();
        configuraBotãoSalvar();


        Intent dados = getIntent();
        Aluno aluno = (Aluno) dados.getSerializableExtra("aluno");
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());





    }

    public void inicializaComponentes() {

        campoNome = findViewById(R.id.editTextNome);
        campoTelefone = findViewById(R.id.editTextTelefone);
        campoEmail = findViewById(R.id.editTextEmail);
        buttonSalvar = findViewById(R.id.buttonSalvar);


    }

    public void configuraBotãoSalvar() {

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Aluno aluno = criaAluno();
                salva(aluno);

            }
        });



    }



    private void salva(Aluno aluno) {

        alunoDAO.salvar(aluno);
        finish();

    }

    public Aluno criaAluno() {

        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);


    }


}