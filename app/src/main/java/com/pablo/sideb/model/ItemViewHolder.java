package com.pablo.sideb.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pablo.sideb.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView txProduto, txDescricao, txValor;
    public ImageView imgFotoProduto;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        txProduto = itemView.findViewById(R.id.txProduto);
        txDescricao = itemView.findViewById(R.id.txDescricao);
        txValor = itemView.findViewById(R.id.txValor);
        imgFotoProduto = itemView.findViewById(R.id.imgFotoProduto);
    }
}
