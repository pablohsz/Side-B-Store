package com.pablo.sideb.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablo.sideb.R;
import com.pablo.sideb.model.ItemViewHolder;
import com.pablo.sideb.model.Produto;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private Context context;
    private ArrayList<Produto> itens;

    public ItemAdapter(Context context, ArrayList<Produto> itens) {
        this.context = context;
        this.itens = itens;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate((R.layout.item_store), parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Produto produto = itens.get(position);
        holder.txProduto.setText(produto.getNome());
        holder.txDescricao.setText(produto.getDescricao());
        holder.txValor.setText(produto.getValor());
        holder.imgFotoProduto.setImageResource(produto.getFotoProduto());

        holder.imgFotoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicou no" + itens.get(position).getNome(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return itens.size();
    }
}
