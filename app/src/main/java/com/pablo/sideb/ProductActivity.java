package com.pablo.sideb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ProductActivity extends AppCompatActivity {

    TextView txTituloProduto, txCodProduto, txPrecoProduto, txTracklist, txCaracteristicas;
    ImageView imgProduto;
    Button btnComprar;
    Intent reiceveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        txTituloProduto = findViewById(R.id.txTituloProduto);
        txCodProduto = findViewById(R.id.txCodProduto);
        txPrecoProduto = findViewById(R.id.txPrecoProduto);
        txTracklist = findViewById(R.id.txTracklist);
        txCaracteristicas = findViewById(R.id.txCaracteristicas);
        imgProduto = findViewById(R.id.imgProduto);
        btnComprar = findViewById(R.id.btnComprar);

        reiceveData = getIntent();
        int cod = reiceveData.getIntExtra("cod", 0),
                img = reiceveData.getIntExtra("imagem", 0);
        String produto = reiceveData.getStringExtra("produto"),
                valor = reiceveData.getStringExtra("valor"),
                caracteristicas = reiceveData.getStringExtra("caracteristicas"),
                tracklist = reiceveData.getStringExtra("tracklist");


        txTituloProduto.setText(produto);
        txPrecoProduto.setText(valor);
        txCaracteristicas.setText(caracteristicas);
        txTracklist.setText(tracklist);
        txCodProduto.setText("COD: " + cod);
        imgProduto.setImageResource(img);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductActivity.this, OrderActivity.class);
                i.putExtra("produto", produto);
                i.putExtra("valor", valor);
                startActivity(i);
                finish();
            }
        });


    }
}