package com.pablo.sideb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Produto produto = itens.get(position);
        holder.txProduto.setText(produto.getItem());
        holder.txDescricao.setText(produto.getDescricao());
        holder.txValor.setText(produto.getValor());
        holder.imgFotoProduto.setImageResource(produto.getImg());

        holder.imgFotoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ProductActivity.class);
                i.putExtra("cod", produto.getCod());
                i.putExtra("produto", produto.getItem());
                i.putExtra("valor", produto.getValor());
                i.putExtra("imagem", produto.getImg());
                i.putExtra("caracteristicas", produto.getCaracteristicas());
                i.putExtra("tracklist", produto.getTracklist());
                v.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return itens.size();
    }
}
