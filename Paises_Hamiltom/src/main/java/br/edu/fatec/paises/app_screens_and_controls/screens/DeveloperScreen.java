package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.DevelopersText;

import javax.swing.*;
import java.awt.*;

public class DeveloperScreen {

    protected final JLabel lblTitle = createLabel();

    public JLabel createLabel() {
        JLabel label = new JLabel(DevelopersText.LBL_TITLE.getString());
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setBounds(0, 10, 800, 50);
        return label;
    }
}