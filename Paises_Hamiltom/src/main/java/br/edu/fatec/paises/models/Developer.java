package br.edu.fatec.paises.models;

import javax.swing.*;

public class Developer {
    private final String name;
    private final String githubUrl;
    private final ImageIcon image;

    public Developer(String name, String githubUrl, ImageIcon image) {
        this.name = name;
        this.githubUrl = githubUrl;
        this.image = image;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public ImageIcon getImage() {
        return image;
    }
}

