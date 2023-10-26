package br.edu.fatec.paises.services.menu;

import br.edu.fatec.paises.interfaces.adicionar_pais.AdicionarPais;
import br.edu.fatec.paises.interfaces.cadastrar_vizinho.CadastrarVizinho;
import br.edu.fatec.paises.interfaces.deletar_pais.DeletarPais;
import br.edu.fatec.paises.interfaces.editar_pais.EditarPais;
import br.edu.fatec.paises.interfaces.enums.menu.MenuText;
import br.edu.fatec.paises.interfaces.listar_paises.ListarPaises;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuServices {

    private static final Map<String, JPanel> telas = new LinkedHashMap<>();

    public MenuServices() {
        telas.put(MenuText.BTN_NEW_PAIS.getString(), new AdicionarPais().montarTela());
        telas.put(MenuText.BTN_NEW_VIZINHO.getString(), new CadastrarVizinho().montarTela());
        telas.put(MenuText.BTN_EDIT_PAIS.getString(), new EditarPais().montarTela());
        telas.put(MenuText.BTN_LIST_PAISES.getString(), new ListarPaises().montarTela());
        telas.put(MenuText.BTN_DELETE_PAIS.getString(), new DeletarPais().montarTela());
    }

    public void telaApp(String name, JPanel tela) {
        JFrame frame = new JFrame(name);
        frame.setContentPane(tela);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(tela.getSize());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void telaClose(JButton button) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(button);
        frame.dispose();
    }

    public void addListener(JButton button) {
        button.addActionListener(e -> {
            telaClose(button);
            telaApp(button.getName(), telas.get(button.getName()));
        });
    }
}
