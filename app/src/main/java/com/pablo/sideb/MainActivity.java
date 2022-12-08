package com.pablo.sideb;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.model.Cliente;
import com.pablo.sideb.model.Produto;

public class MainActivity extends AppCompatActivity {

    ImageView banner1, banner2, banner3, cardTodos;
    Button btnEndereco, btnLista;
    DAO database = new DAO(this);
    TextView meusPedidos;


    Cliente c = new Cliente("072976", "PABLO", "PABLO@GMAIL", "62994585537");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database.addCliente(c);


        banner1 = findViewById(R.id.banner1);
        banner2 = findViewById(R.id.banner2);
        banner3 = findViewById(R.id.banner3);
        cardTodos = findViewById(R.id.cardTodos);
        meusPedidos = findViewById(R.id.meusPedidos);

        cardTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });


        meusPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListOrdersActivity.class);
                startActivity(i);
            }
        });

        banner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProductActivity.class);
                Produto produto = database.consultarProduto(110);
                i.putExtra("cod", produto.getCod());
                i.putExtra("produto", produto.getItem());
                i.putExtra("valor", produto.getValor());
                i.putExtra("imagem", produto.getImg());
                i.putExtra("caracteristicas", produto.getCaracteristicas());
                i.putExtra("tracklist", produto.getTracklist());
                startActivity(i);
            }
        });

        banner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProductActivity.class);
                Produto produto = database.consultarProduto(108);
                i.putExtra("cod", produto.getCod());
                i.putExtra("produto", produto.getItem());
                i.putExtra("valor", produto.getValor());
                i.putExtra("imagem", produto.getImg());
                i.putExtra("caracteristicas", produto.getCaracteristicas());
                i.putExtra("tracklist", produto.getTracklist());
                startActivity(i);
            }
        });

        banner3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Em breve...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}