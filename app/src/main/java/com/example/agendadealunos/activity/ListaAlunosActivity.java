package com.example.agendadealunos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendadealunos.R;
import com.example.agendadealunos.data.AlunoDAO;
import com.example.agendadealunos.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.Normalizer;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APP_BAR = "Lista de Alunos";
    FloatingActionButton buttonAddAluno;
    final AlunoDAO alunoDAO = new AlunoDAO();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APP_BAR);

        //Metodos
        inicializarComponentes();
        irFormularioActivity();


    }

    public void inicializarComponentes(){

         buttonAddAluno = findViewById(R.id.floatAddAluno);
    }

    public void irFormularioActivity(){

        buttonAddAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                startActivity(intent);


            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();

    }

    public void configuraLista(){

        ListView listaAlunos = findViewById(R.id.listaDeAlunos);
       final List<Aluno> alunos = alunoDAO.todos();
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, alunoDAO.todos()));
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = alunos.get(position);
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
                intent.putExtra("aluno", alunoEscolhido );
                startActivity(intent);
            }
        });

    }


}
