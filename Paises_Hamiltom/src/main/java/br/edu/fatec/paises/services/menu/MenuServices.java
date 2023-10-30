package br.edu.fatec.paises.services.menu;

import br.edu.fatec.paises.interfaces.adicionar_pais.AdicionarPais;
import br.edu.fatec.paises.interfaces.cadastrar_vizinho.CadastrarVizinho;
import br.edu.fatec.paises.interfaces.deletar_pais.DeletarPais;
import br.edu.fatec.paises.interfaces.editar_pais.EditarPais;
import br.edu.fatec.paises.enums.menu.MenuText;
import br.edu.fatec.paises.implementar.MontarTelas;
import br.edu.fatec.paises.interfaces.listar_paises.ListarPaises;
import br.edu.fatec.paises.interfaces.menu.Menu;

import javax.swing.*;

import static br.edu.fatec.paises.Main.paisDAO;

public class MenuServices {

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

    public boolean paisesVazio(JButton button) {
        if(paisDAO.getPaises().isEmpty() && !button.getText().equals(MenuText.BTN_NEW_PAIS.getString())) {
            String[] options = {MenuText.BTN_NEW_PAIS.getString(), MenuText.BTN_MENU.getString()};
            int choice = JOptionPane.showInternalOptionDialog(null,
                    MenuText.MSG_PAISES_EMPTY.getString() + "\n" + MenuText.MSG_NOVO_PAIS.getString(),
                    MenuText.LBL_TITLE.getString(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null,options, options[0]);
            if ((choice == 0)) telaApp(options[choice], initializeTelas(options[choice]).montarTela());
            else telaApp(options[choice], new Menu().montarTela());
            return true;
        }
        return false;
    }

    public void addListener(JButton button) {
        button.addActionListener(e -> {
            telaClose(button);
            if(paisesVazio(button)) return;
            telaApp(button.getText(), initializeTelas(button.getText()).montarTela() );
        });
    }

    public MontarTelas initializeTelas(String buttonText) {
        return switch (MenuText.getEnum(buttonText)) {
            case BTN_NEW_PAIS -> new AdicionarPais();
            case BTN_LIST_PAISES -> new ListarPaises();
            case BTN_NEW_VIZINHO -> new CadastrarVizinho();
            case BTN_EDIT_PAIS -> new EditarPais();
            case BTN_DELETE_PAIS -> new DeletarPais();
            default -> new Menu();
        };
    }
}