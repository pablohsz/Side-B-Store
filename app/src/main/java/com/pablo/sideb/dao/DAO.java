package com.pablo.sideb.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pablo.sideb.model.Cliente;
import com.pablo.sideb.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "bd_bstore";

    //Tabela que armazena os dados do cliente
    private static final String tb_cliente = "custumer";
    private static final String c_cpf = "cpf";
    private static final String c_nome = "nome";
    private static final String c_email = "email";
    private static final String c_telefone = "telefone";
    //Tabela que armazena os dados do pedido
    private static final String tb_pedido = "orders";
    private static final String c_idPedido = "id";
    private static final String c_dtCompra = "dt_compra";
    private static final String c_item = "item";
    private static final String c_qtde = "qtde";
    private static final String c_endereco = "endereco";
    //Tabela que armazena os itens do carrinho
    private static final String tb_carrinho = "orders";
    private static final String c_idItem = "idItem";
    private static final String c_qtdeCarrinho = "qtdeItem";

    private String query = "";
    public static Context contexto;

    public DAO(@Nullable Context context) {
        super(context, name, null, version);
        contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE cliente (cpf TEXT PRIMARY KEY, ...)
        query = "CREATE TABLE " + tb_cliente + "(" +
                c_cpf + " TEXT PRIMARY KEY NOT NULL, " +
                c_nome + " TEXT NOT NULL, " +
                c_email + " TEXT NOT NULL, " +
                c_telefone + " TEXT NOT NULL)";
        db.execSQL(query);
        query = "";
        //CREATE TABLE orders (id INT, ...)
        query = "CREATE TABLE " + tb_pedido + "(" +
                c_idPedido + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                c_dtCompra + " TEXT NOT NULL, " +
                c_item + " TEXT NOT NULL, " +
                c_qtde + " INTEGER NOT NULL, " +
                c_endereco + " TEXT NOT NULL)";
        db.execSQL(query);
        query = "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCliente(@NonNull Cliente cli) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO  " + tb_cliente +
                 " (" + c_cpf + ", " + c_nome + ", " + c_email + ", " + c_telefone + ") VALUES ('" +
                cli.getCpf() + "', '" + cli.getNome() + "', '" + cli.getEmail() + "', '" + cli.getTelefone() + "')";
        db.execSQL(query);
        db.close();
    }

    public Cliente consultarCliente() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Pedido> pedidoLista = new ArrayList<>();

        String query = "SELECT * FROM " + tb_cliente;
        Cursor cursor = db.rawQuery(query, null);
        Cliente cli = new Cliente();
        cli.setCpf(cursor.getString(0));
        cli.setNome(cursor.getString(1));
        cli.setEmail(cursor.getString(2));
        cli.setTelefone(cursor.getString(3));

        return cli;
    }

    public void removePedido(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_pedido, c_idPedido + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void addPedido(Pedido ped) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(c_dtCompra, ped.getDtCompra());
        values.put(c_item, ped.getItem());
        values.put(c_qtde, ped.getQtde());
        values.put(c_endereco, ped.getEnderecoEntrega());
        db.insert(tb_pedido, null, values);
        db.close();
    }

    public List<Pedido> listarPedidos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Pedido> pedidoLista = new ArrayList<>();

        String query = "SELECT * FROM " + tb_pedido;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Pedido ped = new Pedido();
                ped.setIdPedido(Integer.parseInt(cursor.getString(0)));
                ped.setDtCompra(cursor.getString(1));
                ped.setItem(cursor.getString(2));
                ped.setQtde(Integer.parseInt(cursor.getString(3)));
                ped.setEnderecoEntrega(cursor.getString(4));
                pedidoLista.add(ped);
            } while (cursor.moveToNext());
        }
        return pedidoLista;
    }


}
