package br.edu.fatec.paises.repository;

import br.edu.fatec.paises.models.Pais;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PaisDAO {
    private final List<Pais> paisListOrdered = new LinkedList<>();
    private final Map<String, Pais> paises = new LinkedHashMap<>();
    public PaisDAO(){
        addPais("Brasil", "Brasilia", 8516000);
        addPais("Argentina", "Buenos Aires", 2780000);
        addPais("Uruguai", "Montevidéu", 176000);
        addPais("Paraguai", "Assunção", 406752);
        addPais("Bolívia", "Sucre", 1098581);
        addPais("Peru", "Lima", 1285216);
        addPais("Colômbia", "Bogotá", 1141748);
        addPais("Venezuela", "Caracas", 916445);
        addPais("Guiana", "Georgetown", 214969);
        addPais("Suriname", "Paramaribo", 163820);
        addPais("Guiana Francesa", "Caiena", 83534);
        addPais("Equador", "Quito", 283561);
        addPais("Chile", "Santiago", 756102);
        addPais("Ilhas Malvinas", "Stanley", 12173);
    }

    private void addPais(String nome, String capital, double dimensao) {
        addPais(new Pais(nome, capital, dimensao));
    }

    public void addPais(Pais pais) {
        paises.put(pais.getNome(), pais);
        paisListOrdered.add(pais);
    }

    public void editPais(String pais, String nome, String capital, double dimensao) {
        Pais paisOld = paises.remove(pais);
        Pais paisNew = new Pais(nome, capital, dimensao);
        if (!paisOld.getFronteira().isEmpty()) {
            paisNew.setFronteira(paisOld.getFronteira());
            for(Pais vizinho : paisNew.getFronteira()) vizinho.getFronteira().set(vizinho.getFronteira().indexOf(paisOld), paisNew);
        }
        paises.put(nome, paisNew);
        paisListOrdered.set(paisListOrdered.indexOf(paisOld), paisNew);
    }

    public void removePais(String pais) {
        Pais paisOld = paises.remove(pais);
        paisListOrdered.remove(paisOld);
    }

    public Pais getPais(String pais) {
        return paises.get(pais);
    }

    public List<Pais> getPaises() {
        return paisListOrdered;
    }
}