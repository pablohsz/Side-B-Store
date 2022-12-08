package com.pablo.sideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.model.Produto;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView rcyViewItens;
    ItemAdapter adapter;
    ArrayList<Produto> itens;
    DAO bdProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        bdProdutos = new DAO(this);
        rcyViewItens = findViewById(R.id.rcyViewItens);
        itens = bdProdutos.listarProdutos();
        adapter = new ItemAdapter(ListActivity.this, itens);
        rcyViewItens.setLayoutManager(new LinearLayoutManager(ListActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rcyViewItens.setAdapter(adapter);

    }


}