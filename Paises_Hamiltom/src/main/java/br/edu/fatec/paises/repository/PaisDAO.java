package br.edu.fatec.paises.repository;

import br.edu.fatec.paises.models.Pais;

import java.util.LinkedList;
import java.util.List;

public class PaisDAO {
    private final List<Pais> paises = new LinkedList<>();
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

    public void addPais(String nome, String capital, int tamanho) {
        Pais pais = new Pais(nome, capital, tamanho);
        paises.add(pais);
    }

    public List<Pais> getPaises() {
        return paises;
    }
}
