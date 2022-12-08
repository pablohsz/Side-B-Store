package com.pablo.sideb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.pablo.sideb.dao.DAO;
import com.pablo.sideb.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ListOrdersActivity extends AppCompatActivity {


    TextInputEditText edIdPedido, edDataCompra, edItemPed, edQtdePed, edEnderecoCompra, edValorCompra;
    Button btnBuscarId, btnDeletar, btnAtualizar;
    ListView listaPedidos;
    List<String> pedidos;
    List<Pedido> orders;
    ArrayAdapter adapter;
    DAO database = new DAO(this);
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);
        edIdPedido = findViewById(R.id.edIdPedido);
        edDataCompra = findViewById(R.id.edDataCompra);
        edItemPed = findViewById(R.id.edItemPed);
        edQtdePed = findViewById(R.id.edQtdePed);
        edEnderecoCompra = findViewById(R.id.edEnderecoCompra);
        edValorCompra = findViewById(R.id.edValorCompra);
        btnBuscarId = findViewById(R.id.btnBuscarId);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        listaPedidos = findViewById(R.id.listaPedidos);
        pedidos = new ArrayList<String>();
        adapter = new ArrayAdapter(ListOrdersActivity.this, android.R.layout.simple_list_item_1, pedidos);

        carregaPedidos();
        adapter.notifyDataSetChanged();
        listaPedidos.setAdapter(adapter);

        btnBuscarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edIdPedido.getText().toString().equals("")) {
                    Toast.makeText(ListOrdersActivity.this, "Por favor, digite o ID de um pedido para realizar a busca.", Toast.LENGTH_SHORT).show();
                } else {
                    id = Integer.parseInt(edIdPedido.getText().toString());
                    Pedido p = new Pedido();
                    try {
                        p = database.consultarPedido(id);
                        edDataCompra.setText(p.getDtCompra());
                        edItemPed.setText(p.getItem());
                        edQtdePed.setText(String.valueOf(p.getQtde()));
                        edEnderecoCompra.setText(p.getEnderecoEntrega());
                        edValorCompra.setText(p.getValorPedido());
                        edEnderecoCompra.setEnabled(true);
                    } catch (CursorIndexOutOfBoundsException e) {
                        Toast.makeText(ListOrdersActivity.this, "Ocorreu um erro!\nPedido não encontrado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edItemPed.getText().toString().equals("")) {
                    try {
                        database.removePedido(id);
                        orders.clear();
                        carregaPedidos();
                        adapter.notifyDataSetChanged();
                        listaPedidos.setAdapter(adapter);
                        limparCampos();
                    } catch (Exception e) {
                        Toast.makeText(ListOrdersActivity.this, "Ocorreu um erro!\nNão foi possível deletar.", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(ListOrdersActivity.this, "Insira um ID e toque no botão BUSCAR.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edItemPed.getText().toString().equals("")) {
                    try {
                        Pedido p = new Pedido();
                        p.setEnderecoEntrega(edEnderecoCompra.getText().toString());
                        database.atualizarPedido(id, p.getEnderecoEntrega());
                        orders.clear();
                        carregaPedidos();
                        adapter.notifyDataSetChanged();
                        listaPedidos.setAdapter(adapter);
                        limparCampos();
                    } catch (Exception e) {
                        Toast.makeText(ListOrdersActivity.this, "Ocorreu um erro!\nNão foi possível atualizar.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ListOrdersActivity.this, "Insira um ID e toque no botão BUSCAR.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void carregaPedidos() {
        orders = database.listarPedidos();
        if (orders.size() != 0 && orders.size() != -1) {
            for (Pedido p : orders
            ) {
                String texto = "ID: " + p.getIdPedido() + " | " + p.getDtCompra() + "\n" +
                        "ITEM: " + p.getQtde() + "x " + p.getItem() + "\n" +
                        "ENDEREÇO DE ENTREGA: " + p.getEnderecoEntrega();
                pedidos.add(texto);
            }
        } else {
            pedidos.add("VOCÊ AINDA NÃO REALIZOU SEU PRIMEIRO PEDIDO!");
        }
    }

    public void limparCampos() {
        edIdPedido.setText("");
        edDataCompra.setText("");
        edItemPed.setText("");
        edQtdePed.setText("");
        edEnderecoCompra.setText("");
        edValorCompra.setText("");
    }
}