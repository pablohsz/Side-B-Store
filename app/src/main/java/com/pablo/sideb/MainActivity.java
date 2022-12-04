package com.pablo.sideb;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.dao.DAOProduto;
import com.pablo.sideb.model.Cliente;

public class MainActivity extends AppCompatActivity {


    Button btnEndereco, btnLista;
    DAO database = new DAO(this);
    DAOProduto db = new DAOProduto(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Cliente cli = new Cliente("072976756", "PABLO HENRIQUE", "pablohsouza20@gmail.com", "62994585537");
        database.addCliente(cli);


        btnEndereco = findViewById(R.id.btnEndereco);
        btnLista = findViewById(R.id.btnProdutos);

        btnEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
    }
}