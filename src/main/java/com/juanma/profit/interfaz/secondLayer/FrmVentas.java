/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.juanma.profit.interfaz.secondLayer;

import com.juanma.profit.entidad.Producto;
import com.juanma.profit.entidad.Venta;
import com.juanma.profit.persistencia.VentaPersistencia;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author juanm
 */
public class FrmVentas extends javax.swing.JFrame {

    private DefaultTableModel tableModelVentas;
    private DefaultTableModel tableModelCaja;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public FrmVentas() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarVentasEnTabla();

        pack();
        cargarCajaEnTabla();

    }

    private void cargarCajaEnTabla() {
        List<Venta> ventas = VentaPersistencia.obtenerTodas();
        tableModelCaja = new DefaultTableModel();

        tableModelCaja.addColumn("Precio Producto");
        tableModelCaja.addColumn("Nombre del Producto");
        tableModelCaja.addColumn("Cantidad Vendida");

        double totalVendido = 0;
        Map<String, Integer> cantidadPorProducto = new HashMap<>();
        Map<String, Double> precioPorProducto = new HashMap<>();

        for (Venta venta : ventas) {
            List<Producto> productosVenta = Optional.ofNullable(venta.getProductos()).orElse(Collections.emptyList());

            for (Producto producto : productosVenta) {
                String nombreProducto = producto.getNombre();
                double precioVenta = producto.getPrecioVenta();

                cantidadPorProducto.put(nombreProducto, cantidadPorProducto.getOrDefault(nombreProducto, 0) + 1);
                precioPorProducto.put(nombreProducto, precioVenta);

                totalVendido += precioVenta;
            }
        }

        for (Map.Entry<String, Integer> entry : cantidadPorProducto.entrySet()) {
            String nombreProducto = entry.getKey();
            double precio = precioPorProducto.get(nombreProducto);

            Object[] row = new Object[]{
                "$ " + String.format("%.2f", precio),
                nombreProducto,
                entry.getValue()
            };
            tableModelCaja.addRow(row);
        }

        tblMostrarCajaVentas.setModel(tableModelCaja);

        txtTotalVendidoMostrarCajaVentas.setText("$ " + String.format("%.2f", totalVendido));
    }

    public void cargarVentasEnTabla() {
        List<Venta> ventas = VentaPersistencia.obtenerTodas();
        tableModelVentas = new DefaultTableModel();

        tableModelVentas.addColumn("ID");
        tableModelVentas.addColumn("Producto");
        tableModelVentas.addColumn("Categoría");
        tableModelVentas.addColumn("Precio");
        tableModelVentas.addColumn("Fecha");

        for (Venta venta : ventas) {
            List<Producto> productosVenta = Optional.ofNullable(venta.getProductos()).orElse(Collections.emptyList());

            for (Producto producto : productosVenta) {

                Date fecha = venta.getFecha();
                String fechaStr = (fecha != null) ? new SimpleDateFormat("dd/MM/yyyy").format(fecha) : "Sin fecha";

                Object[] row = new Object[]{
                    venta.getId(),
                    producto.getNombre(),
                    producto.getCategoria(),
                    "$ " + String.format("%.2f", producto.getPrecioVenta()),
                    fechaStr
                };
                tableModelVentas.addRow(row);
            }
        }

        tblRegistroDeVentas.setModel(tableModelVentas);
    }

    

    private void filtrarTabla() {

        String textoBusqueda = txtBuscarVenta.getText().trim();

        if (textoBusqueda.length() == 0) {
            rowSorter.setRowFilter(null);
        } else {

            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localeStrings1 = new de.wannawork.jcalendar.LocaleStrings();
        btnActualizarVentas = new javax.swing.JButton();
        btnAgregarEgreso = new javax.swing.JButton();
        btnEditarVenta = new javax.swing.JButton();
        btnEliminarVenta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtBuscarVenta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        cmbBoxFiltrarProveedor = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistroDeVentas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMostrarTotalEgresos = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTotalVendidoMostrarCajaVentas = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMostrarCajaVentas = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnAgregarVenta1 = new javax.swing.JButton();
        JCalendarFilterFechasCmbx = new de.wannawork.jcalendar.JCalendarComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas");

        btnActualizarVentas.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Actualizar.png")); // NOI18N
        btnActualizarVentas.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar"));
        btnActualizarVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarVentasActionPerformed(evt);
            }
        });

        btnAgregarEgreso.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Agregar.png")); // NOI18N
        btnAgregarEgreso.setBorder(null);
        btnAgregarEgreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarEgreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarEgresoMouseClicked(evt);
            }
        });
        btnAgregarEgreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEgresoActionPerformed(evt);
            }
        });

        btnEditarVenta.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Editar2.png")); // NOI18N
        btnEditarVenta.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar"));
        btnEditarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarVentaActionPerformed(evt);
            }
        });

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Delete.png")); // NOI18N
        btnEliminarVenta.setBorder(javax.swing.BorderFactory.createTitledBorder("Eliminar"));
        btnEliminarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Búsqueda :");
        jLabel4.setToolTipText("");

        txtBuscarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarVentaActionPerformed(evt);
            }
        });
        txtBuscarVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarVentaKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Excel.png")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder("Exportar"));

        cmbBoxFiltrarProveedor.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cmbBoxFiltrarProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtrar", "Cigarrillos", "Bebidas", "Otros" }));
        cmbBoxFiltrarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbBoxFiltrarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxFiltrarProveedorActionPerformed(evt);
            }
        });

        tblRegistroDeVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblRegistroDeVentas);

        jLabel5.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(209, 79, 79));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("EGRESOS");
        jLabel5.setToolTipText("");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtMostrarTotalEgresos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(txtMostrarTotalEgresos);

        jLabel3.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(60, 193, 60));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INGRESOS");
        jLabel3.setToolTipText("");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTotalVendidoMostrarCajaVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane4.setViewportView(txtTotalVendidoMostrarCajaVentas);

        tblMostrarCajaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane6.setViewportView(tblMostrarCajaVentas);

        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAgregarVenta1.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Agregar.png")); // NOI18N
        btnAgregarVenta1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar"));
        btnAgregarVenta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarVenta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarVenta1MouseClicked(evt);
            }
        });
        btnAgregarVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVenta1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarEgreso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnActualizarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 869, Short.MAX_VALUE)
                        .addComponent(cmbBoxFiltrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JCalendarFilterFechasCmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtBuscarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbBoxFiltrarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JCalendarFilterFechasCmbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnActualizarVentas, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditarVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarVenta1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(btnAgregarEgreso))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarVentasActionPerformed
        cargarVentasEnTabla();
        cargarCajaEnTabla();
        JOptionPane.showMessageDialog(this, "Ventas actualizadas.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnActualizarVentasActionPerformed

    private void btnEditarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarVentaActionPerformed

    }//GEN-LAST:event_btnEditarVentaActionPerformed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed

        int filaSeleccionada = tblRegistroDeVentas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una venta para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idVenta = (int) tblRegistroDeVentas.getValueAt(filaSeleccionada, 0);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar esta venta?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            VentaPersistencia.eliminarVenta(idVenta);

            cargarVentasEnTabla();

            JOptionPane.showMessageDialog(this, "Venta eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void txtBuscarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarVentaActionPerformed

    }//GEN-LAST:event_txtBuscarVentaActionPerformed

    private void txtBuscarVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVentaKeyReleased
        filtrarTabla();
    }//GEN-LAST:event_txtBuscarVentaKeyReleased

    private void cmbBoxFiltrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxFiltrarProveedorActionPerformed

    }//GEN-LAST:event_cmbBoxFiltrarProveedorActionPerformed

    private void btnAgregarVenta1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarVenta1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarVenta1MouseClicked

    private void btnAgregarVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVenta1ActionPerformed
        FrmAgregarVenta ventaAgregar = new FrmAgregarVenta();
        ventaAgregar.setVisible(true);
    }//GEN-LAST:event_btnAgregarVenta1ActionPerformed

    private void btnAgregarEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEgresoActionPerformed

    }//GEN-LAST:event_btnAgregarEgresoActionPerformed

    private void btnAgregarEgresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarEgresoMouseClicked

    }//GEN-LAST:event_btnAgregarEgresoMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.wannawork.jcalendar.JCalendarComboBox JCalendarFilterFechasCmbx;
    private javax.swing.JButton btnActualizarVentas;
    private javax.swing.JButton btnAgregarEgreso;
    private javax.swing.JButton btnAgregarVenta1;
    private javax.swing.JButton btnEditarVenta;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JComboBox<String> cmbBoxFiltrarProveedor;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private de.wannawork.jcalendar.LocaleStrings localeStrings1;
    private javax.swing.JTable tblMostrarCajaVentas;
    private javax.swing.JTable tblRegistroDeVentas;
    private javax.swing.JTextField txtBuscarVenta;
    private javax.swing.JTextPane txtMostrarTotalEgresos;
    private javax.swing.JTextPane txtTotalVendidoMostrarCajaVentas;
    // End of variables declaration//GEN-END:variables
}
