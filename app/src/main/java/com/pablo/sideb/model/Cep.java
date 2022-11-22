package com.pablo.sideb.model;

public class Cep {

    private String cep;
    private String longadouro;
    private String bairro;
    private String localidade;
    private String uf;

    public Cep() {
    }

    public Cep(String cep, String longadouro, String bairro, String localidade, String uf) {
        this.cep = cep;
        this.longadouro = longadouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLongadouro() {
        return longadouro;
    }

    public void setLongadouro(String longadouro) {
        this.longadouro = longadouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString(){
        return getLongadouro() +  ", " + getBairro() + " - " + getLocalidade() + " - " + getUf().toUpperCase() + " - " + getCep();
    }
}
