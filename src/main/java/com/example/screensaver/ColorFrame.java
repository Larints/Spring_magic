package com.example.screensaver;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.swing.*;

import java.awt.*;
import java.util.Random;


public abstract class ColorFrame extends JFrame {


    public ColorFrame() {
        setSize(200, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    protected abstract Color getColor();

    public void showOnRandomPlace() {
        Random random = new Random();
        setLocation(random.nextInt(1200), random.nextInt(700));
        getContentPane().setBackground(getColor());
        repaint();
    }
}
