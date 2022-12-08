package com.pablo.sideb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.model.Cliente;

public class FirstActivity extends AppCompatActivity {

    TextInputEditText edSaudacaoNome,edSaudacaoCpf,edSaudacaoFone, edSaudacaoEmail;
    Button btnEntrarApp;
    DAO bd = new DAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        edSaudacaoNome = findViewById(R.id.edSaudacaoNome);
        edSaudacaoCpf = findViewById(R.id.edSaudacaoCpf);
        edSaudacaoFone = findViewById(R.id.edSaudacaoFone);
        edSaudacaoEmail = findViewById(R.id.edSaudacaoEmail);
        btnEntrarApp = findViewById(R.id.btnEntrarApp);

        btnEntrarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente cli = new Cliente();

                //TODO: Inserir validações
                cli.setNome(edSaudacaoNome.getText().toString().toLowerCase());
                cli.setCpf(edSaudacaoCpf.getText().toString());
                cli.setTelefone(edSaudacaoFone.getText().toString());
                cli.setEmail(edSaudacaoEmail.getText().toString());
                bd.addCliente(cli);
            }
        });
    }
}