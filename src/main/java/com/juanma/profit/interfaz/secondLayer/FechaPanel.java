/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanma.profit.interfaz.secondLayer;

/**
 *
 * @author juanm
 */
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FechaPanel extends JPanel {
    private JSpinner dateSpinner;

    public FechaPanel() {
        setLayout(new FlowLayout());

        // Crear un JSpinner con un modelo de fecha
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);

        add(new JLabel("Seleccione la fecha:"));
        add(dateSpinner);
    }

    public Date getFecha() {
        return (Date) dateSpinner.getValue();
    }
}