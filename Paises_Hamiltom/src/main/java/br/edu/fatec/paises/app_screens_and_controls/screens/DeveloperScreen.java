package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.controller.Developers;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.DevelopersText;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeveloperScreen extends Developers implements PanelSettings {
    private final JLabel lblTitle = new JLabel();

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(DevelopersText.LBL_TITLE.getString());
        lblTitle.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setBounds(0, 10, 800, 50);
        return lblTitle;
    }

    @ComponentMethod
    public List<JLabel> getListDevelopers(){
        return getDeveloperLabels();
    }
}