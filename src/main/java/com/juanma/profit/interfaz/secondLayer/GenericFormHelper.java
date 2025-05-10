/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanma.profit.interfaz.secondLayer;

import java.awt.Color;
import java.awt.Component;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;





/**
 * Clase utilitaria para simplificar operaciones comunes en formularios
 */
public class GenericFormHelper {

    // Método para configurar placeholders en JTextField
    public static void setupPlaceholder(JTextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    // Método para validar campos obligatorios
    public static boolean validateRequiredFields(JFrame parent, JTextField... fields) {
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty() || 
                field.getForeground().equals(Color.GRAY)) { // Es un placeholder
                showError(parent, "Todos los campos son obligatorios");
                field.requestFocus();
                return false;
            }
        }
        return true;
    }

    // Método para validar campos numéricos
    public static boolean validateNumericFields(JFrame parent, JTextField... fields) {
        for (JTextField field : fields) {
            try {
                Double.parseDouble(field.getText());
            } catch (NumberFormatException e) {
                showError(parent, "El campo " + field.getName() + " debe ser numérico");
                field.requestFocus();
                return false;
            }
        }
        return true;
    }

    // Método para mostrar mensajes de error
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }

    // Método para mostrar mensajes de éxito
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, 
            message, 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para confirmación de acción
    public static boolean confirmAction(Component parent, String message) {
        int response = JOptionPane.showConfirmDialog(
            parent,
            message,
            "Confirmar",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        return response == JOptionPane.YES_OPTION;
    }

    // Método genérico para manejar la acción de guardar
    public static <T> boolean handleSaveOperation(
            JFrame parent,
            String successMessage,
            Consumer<T> saveFunction,
            T entity) {
        try {
            saveFunction.accept(entity);
            showSuccess(parent, successMessage);
            parent.dispose();
            return true;
        } catch (Exception e) {
            showError(parent, "Error al guardar: " + e.getMessage());
            return false;
        }
    }
}
