package com.pablo.sideb.model;

public class Produto {

    private String nome;
    private String descricao;
    private String valor;
    private int fotoProduto;

    public Produto(String nome, String descricao, String valor, int fotoProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.fotoProduto = fotoProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(int fotoProduto) {
        this.fotoProduto = fotoProduto;
    }

}
