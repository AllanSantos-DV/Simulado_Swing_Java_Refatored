package br.edu.fatec.paises.services.menu;

import javax.swing.*;

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
        button.getRootPane().getParent().setVisible(false);
    }
}
