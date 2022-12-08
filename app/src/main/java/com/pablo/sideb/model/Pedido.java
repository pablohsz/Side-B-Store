package com.pablo.sideb.model;

public class Pedido {

    private int idPedido;
    private String dtCompra;
    private String item;
    private int qtde;
    private String enderecoEntrega;
    private String valorPedido;

    public Pedido() {
    }

    public Pedido(int idPedido, String dtCompra, String item, int qtde, String enderecoEntrega, String valorPedido) {
        this.idPedido = idPedido;
        this.dtCompra = dtCompra;
        this.item = item;
        this.qtde = qtde;
        this.enderecoEntrega = enderecoEntrega;
        this.valorPedido = valorPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(String dtCompra) {
        this.dtCompra = dtCompra;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(String valorPedido) {
        this.valorPedido = valorPedido;
    }

}
