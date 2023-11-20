package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.DeveloperScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;
import br.edu.fatec.paises.models.Developer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.logger;

public class Developers extends DeveloperScreen implements PanelSettings {
    private static final int COMPONENTS_WIDTH = 250, COMPONENTS_HEIGHT = 275;
    private static final int[] COLUMN = {100, 385}, LINE = {145, 405};
    private final ImageIcon allanSantosImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/allan_santos.jpg")));
    private final ImageIcon victorMendezImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/victor_mendez.jpg")));
    private final ImageIcon nathaliaSoaresImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/nathalia_soares.jpg")));
    private final ImageIcon reinaldoNunesImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/reinaldo_nunes.jpg")));
    private final List<Developer> developerList = List.of(
            new Developer("Allan Santos", "https://github.com/AllanSantos-DV", allanSantosImage),
            new Developer("Victor Mendez", "https://github.com/Vict0rMendes", victorMendezImage),
            new Developer("Nathalia Soares", "https://github.com/Nathalia-Soares", nathaliaSoaresImage),
            new Developer("Reinaldo Nunes", "https://github.com/Re1naldoNunes", reinaldoNunesImage)
    );

    @ComponentMethod
    public List<JLabel> getListDevelopers() {
        return getDeveloperLabels();
    }

    public ImageIcon getIcon(ImageIcon icon, String name) {
        int fontSize = 15, width = icon.getIconWidth(), height = icon.getIconHeight() + fontSize * 2;
        int xName = (width - (name.length() * 7)) / 2, yName = width + fontSize;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(icon.getImage(), 0, 0, null);
        g.setFont(new Font("Arial", Font.BOLD, fontSize));
        g.setColor(Color.BLACK);
        g.drawString(name, xName, yName);
        g.dispose();
        return new ImageIcon(image);
    }

    public MouseAdapter mouseListenerGitHub(String link) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(URI.create(link));
                } catch (IOException e) {
                    logger.error(AppText.MSG_ERROR.getString(), e);
                }
            }
        };
    }

    public List<JLabel> getDeveloperLabels() {
        List<JLabel> labels = new ArrayList<>();
        labels.add(lblTitle);
        for (int i = 0; i < developerList.size(); i++) {
            int column = i < 2 ? 0 : 1;
            int line = column == 0 ? i : i - 2;
            Developer dev = developerList.get(i);
            JLabel label = new JLabel();
            ImageIcon icon = getIcon(dev.image(), dev.name());
            label.setIcon(icon);
            label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            label.setBounds(LINE[line], COLUMN[column], COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
            label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.addMouseListener(mouseListenerGitHub(dev.githubUrl()));
            labels.add(label);
        }
        return labels;
    }
}