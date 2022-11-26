package com.pablo.sideb.model;

public class Produto {

    private int cod;
    private String item;
    private String descricao;
    private String valor;
    private int img;
    private String caracteristicas;
    private String tracklist;

    public Produto() {
    }

    public Produto(int cod, String nome, String descricao, String valor, int img, String caracteristicas, String tracklist) {
        this.cod = cod;
        this.item = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.img = img;
        this.caracteristicas = caracteristicas;
        this.tracklist = tracklist;
    }



    public Produto(String nome, String descricao, String valor, int img) {
        this.item = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.img = img;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

}
