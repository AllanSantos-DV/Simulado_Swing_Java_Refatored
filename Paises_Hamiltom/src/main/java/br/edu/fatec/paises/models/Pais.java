package br.edu.fatec.paises.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pais {
    private final String nome;
    private final String capital;
    private final double dimensao;
    private List<Pais> fronteira;

    public Pais(String nome, String capital, double dimensao) {
        this.nome = nome;
        this.capital = capital;
        this.dimensao = dimensao;
    }

    public String toString() {
        StringBuilder nomePais = new StringBuilder("Vizinhos: ");
        if (fronteira == null || fronteira.isEmpty())
            return String.format("Nome: %s. Capital: %s. Dimensão: %,.2f", nome, capital, dimensao);
        for (Pais pais : fronteira) {
            nomePais.append(pais.getNome()).append(", ");
        }
        return String.format("Nome: %s. Capital: %s. Dimensão: %,.2f. %s", nome, capital, dimensao, nomePais);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(nome.toLowerCase(), pais.getNome().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, capital);
    }

    public String getNome() {
        return nome;
    }

    public String getCapital() {
        return capital;
    }

    public double getDimensao() {
        return dimensao;
    }

    public List<Pais> getFronteira() {
        List<Pais> novaFronteira = new ArrayList<>();
        return fronteira == null?  novaFronteira : this.fronteira;
    }

    public void setFronteira(List<Pais> fronteira) {
        if (this.fronteira == null) this.fronteira = new ArrayList<>(fronteira);
        else this.fronteira.addAll(fronteira);
    }
}
