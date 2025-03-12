/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.juanma.profit.interfaz.secondLayer;

import com.juanma.profit.entidad.Producto;
import com.juanma.profit.persistencia.ProductoPersistencia;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
/**
 * Clase que representa la interfaz gráfica para agregar un nuevo producto.
 * Permite ingresar los detalles del producto, como nombre, código, proveedor, precios y categoría.
 * Los datos ingresados se validan antes de agregar el producto a la persistencia.
 */
public class FrmAgregarProducto extends javax.swing.JFrame {

   
    public FrmAgregarProducto() {
        initComponents();
         setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
 pack();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreDelProductoAgregar = new javax.swing.JTextField();
        txtProveedorDelProductoAgregar = new javax.swing.JTextField();
        txtPrecioCompraAgregar = new javax.swing.JTextField();
        txtPrecioVentaAgregar = new javax.swing.JTextField();
        txtCodigoDelProductoAgregar = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        btnCancelarProducto = new javax.swing.JButton();
        cbxCategoriaProductoAgregar = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Producto");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Codigo del producto");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nombre del producto");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Proveedor del producto");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Precio venta del producto");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Precio compra del producto");

        txtNombreDelProductoAgregar.setToolTipText("");
        txtNombreDelProductoAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombreDelProductoAgregar.setName(""); // NOI18N
        txtNombreDelProductoAgregar.setPreferredSize(new java.awt.Dimension(64, 24));
        txtNombreDelProductoAgregar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreDelProductoAgregarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreDelProductoAgregarFocusLost(evt);
            }
        });
        txtNombreDelProductoAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreDelProductoAgregarActionPerformed(evt);
            }
        });

        txtProveedorDelProductoAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtProveedorDelProductoAgregar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProveedorDelProductoAgregarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProveedorDelProductoAgregarFocusLost(evt);
            }
        });
        txtProveedorDelProductoAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorDelProductoAgregarActionPerformed(evt);
            }
        });

        txtPrecioCompraAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPrecioCompraAgregar.setPreferredSize(new java.awt.Dimension(64, 24));

        txtPrecioVentaAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPrecioVentaAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPrecioVentaAgregar.setPreferredSize(new java.awt.Dimension(64, 24));

        txtCodigoDelProductoAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCodigoDelProductoAgregar.setPreferredSize(new java.awt.Dimension(64, 24));
        txtCodigoDelProductoAgregar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoDelProductoAgregarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoDelProductoAgregarFocusLost(evt);
            }
        });
        txtCodigoDelProductoAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoDelProductoAgregarActionPerformed(evt);
            }
        });

        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnAgregarProducto.setText("Agregar");
        btnAgregarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarProductoMouseClicked(evt);
            }
        });
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnCancelarProducto.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnCancelarProducto.setText("Cancelar");
        btnCancelarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnCancelarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarProductoMouseClicked(evt);
            }
        });
        btnCancelarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProductoActionPerformed(evt);
            }
        });

        cbxCategoriaProductoAgregar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Cigarrillos", "Bebidas", "Otros" }));
        cbxCategoriaProductoAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cbxCategoriaProductoAgregar.setPreferredSize(new java.awt.Dimension(64, 24));
        cbxCategoriaProductoAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCategoriaProductoAgregarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Categoría del producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrecioVentaAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(txtProveedorDelProductoAgregar)
                            .addComponent(txtNombreDelProductoAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrecioCompraAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(txtCodigoDelProductoAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxCategoriaProductoAgregar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))))))
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreDelProductoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoDelProductoAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxCategoriaProductoAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(135, 135, 135))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProveedorDelProductoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioCompraAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecioVentaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))))
        );

        txtNombreDelProductoAgregar.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreDelProductoAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreDelProductoAgregarActionPerformed
  
    }//GEN-LAST:event_txtNombreDelProductoAgregarActionPerformed

    private void btnCancelarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarProductoMouseClicked

    }//GEN-LAST:event_btnCancelarProductoMouseClicked

    private void btnCancelarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProductoActionPerformed

    }//GEN-LAST:event_btnCancelarProductoActionPerformed

    private void cbxCategoriaProductoAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCategoriaProductoAgregarActionPerformed
       
    }//GEN-LAST:event_cbxCategoriaProductoAgregarActionPerformed

    private void txtNombreDelProductoAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreDelProductoAgregarFocusGained
  if(txtNombreDelProductoAgregar.getText().equals("Ingresa el nombre")){
        txtNombreDelProductoAgregar.setText("");
    }
    }//GEN-LAST:event_txtNombreDelProductoAgregarFocusGained

    private void txtNombreDelProductoAgregarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreDelProductoAgregarFocusLost
         if(txtNombreDelProductoAgregar.getText().trim().equals("")){
        txtNombreDelProductoAgregar.setText("Ingresa el nombre");
    }
    }//GEN-LAST:event_txtNombreDelProductoAgregarFocusLost

    private void txtCodigoDelProductoAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDelProductoAgregarFocusGained
        if(txtCodigoDelProductoAgregar.getText().equals("Ingresa el Codigo")){
        txtCodigoDelProductoAgregar.setText("");
    }
    }//GEN-LAST:event_txtCodigoDelProductoAgregarFocusGained

    private void txtCodigoDelProductoAgregarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoDelProductoAgregarFocusLost
        if(txtCodigoDelProductoAgregar.getText().trim().equals("")){
        txtCodigoDelProductoAgregar.setText("Ingresa el Codigo");
    }
    }//GEN-LAST:event_txtCodigoDelProductoAgregarFocusLost

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        String nombre = txtNombreDelProductoAgregar.getText();
        String codigo = txtCodigoDelProductoAgregar.getText();
        String proveedor = txtProveedorDelProductoAgregar.getText();
        String precioCompraStr = txtPrecioCompraAgregar.getText();
        String precioVentaStr = txtPrecioVentaAgregar.getText();
        String categoria = (String) cbxCategoriaProductoAgregar.getSelectedItem();
 
        try {
            double precioCompra = Double.parseDouble(precioCompraStr);
            double precioVenta = Double.parseDouble(precioVentaStr);
            Producto producto = new Producto(nombre, codigo, proveedor, precioCompra, precioVenta,categoria);
            ProductoPersistencia.agregarProducto(producto);
            this.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos para precios.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnAgregarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProductoMouseClicked

    }//GEN-LAST:event_btnAgregarProductoMouseClicked

    private void txtProveedorDelProductoAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorDelProductoAgregarActionPerformed

    }//GEN-LAST:event_txtProveedorDelProductoAgregarActionPerformed

    private void txtProveedorDelProductoAgregarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProveedorDelProductoAgregarFocusLost
        if(txtProveedorDelProductoAgregar.getText().trim().equals("")){
            txtProveedorDelProductoAgregar.setText("Ingresa el Proveedor");
        }
    }//GEN-LAST:event_txtProveedorDelProductoAgregarFocusLost

    private void txtProveedorDelProductoAgregarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProveedorDelProductoAgregarFocusGained
        if(txtProveedorDelProductoAgregar.getText().equals("Ingresa el Proveedor")){
            txtProveedorDelProductoAgregar.setText("");
        }
    }//GEN-LAST:event_txtProveedorDelProductoAgregarFocusGained

    private void txtCodigoDelProductoAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoDelProductoAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoDelProductoAgregarActionPerformed

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregarProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnCancelarProducto;
    private javax.swing.JComboBox<String> cbxCategoriaProductoAgregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCodigoDelProductoAgregar;
    private javax.swing.JTextField txtNombreDelProductoAgregar;
    private javax.swing.JTextField txtPrecioCompraAgregar;
    private javax.swing.JTextField txtPrecioVentaAgregar;
    private javax.swing.JTextField txtProveedorDelProductoAgregar;
    // End of variables declaration//GEN-END:variables

}
