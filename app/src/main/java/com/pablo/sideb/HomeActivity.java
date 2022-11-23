package com.pablo.sideb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.pablo.sideb.model.Produto;
import com.pablo.sideb.service.ItemAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rcyViewItens;
    ItemAdapter adapter;
    ArrayList<Produto> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rcyViewItens = findViewById(R.id.rcyViewItens);
        itens = new ArrayList<Produto>();
        int idimg = R.drawable.caprisongs_albumcover,
        idimg1 = R.drawable.melodrama_albumcover,
        idimg2 = R.drawable.dawnfm_albumcover;
        itens.add(new Produto("FKA Twigs - Caprisongs", "Mixtape lançada em 2022 explora o mapa astral da diva.", "R$ 50,99", idimg));
        itens.add(new Produto("Lorde - Melodrama", "A maior obra já lançada por um ser humano explora o coração partido.", "R$ 99,99", idimg1));
        itens.add(new Produto("The Weeknd - Dawn FM", "Foda. Esse cara é foda.", "R$ 30,99", idimg2));
        adapter = new ItemAdapter(HomeActivity.this, itens);
        rcyViewItens.setLayoutManager(new LinearLayoutManager(HomeActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rcyViewItens.setAdapter(adapter);
        Toast.makeText(this, "toassssssssssssssst", Toast.LENGTH_SHORT).show();

    }
}