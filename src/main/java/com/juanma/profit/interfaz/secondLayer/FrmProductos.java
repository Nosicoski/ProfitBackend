/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.juanma.profit.interfaz.secondLayer;

import com.juanma.profit.entidad.Producto;
import com.juanma.profit.persistencia.ProductoPersistencia;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author juanm
 */
/**
 * Clase que representa la interfaz gráfica para gestionar productos. Permite
 * visualizar, agregar, editar y eliminar productos, así como filtrarlos por
 * nombre o categoría. Los productos se muestran en una tabla y se pueden
 * manipular mediante botones.
 */
public class FrmProductos extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter; // Filtrador de filas

    public FrmProductos() {
        /**
         * Constructor de la clase FrmProductos. Inicializa la interfaz gráfica,
         * carga los productos en la tabla y configura el filtrado.
         */
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cargarProductosEnTabla();
        configurarFiltrado();
        setLocationRelativeTo(null);
        pack();
    }

    /**
     * Configura el filtrado de la tabla para permitir la búsqueda de productos.
     */
    public void configurarFiltrado() {

        tableModel = (DefaultTableModel) jTable2.getModel();
        rowSorter = new TableRowSorter<>(tableModel);
        jTable2.setRowSorter(rowSorter);

    }

    /**
     * Filtra la tabla de productos según el texto de búsqueda ingresado.
     */
    private void filtrarTabla() {

        String textoBusqueda = txtBuscarProducto.getText().trim();

        if (textoBusqueda.length() == 0) {
            rowSorter.setRowFilter(null);
        } else {

            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    }
/**
     * Carga los productos desde la persistencia y los muestra en la tabla.
     */
    public void cargarProductosEnTabla() {

        List<Producto> productos = ProductoPersistencia.obtenerTodos();

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Código");
        tableModel.addColumn("Proveedor");
        tableModel.addColumn("Precio de Compra");
        tableModel.addColumn("Precio de Venta");
        tableModel.addColumn("Categoría");

        for (Producto producto : productos) {
            Object[] row = new Object[]{
                producto.getNombre(),
                producto.getCodigo(),
                producto.getProveedor(),
                "$ " + String.format("%.2f", producto.getPrecioCompra()),
                "$ " + String.format("%.2f", producto.getPrecioVenta()),
                producto.getCategoria()
            };
            tableModel.addRow(row);
        }

        jTable2.setModel(tableModel);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProductos().setVisible(true);
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCalendarComboBox1 = new de.wannawork.jcalendar.JCalendarComboBox();
        jCalendarPanel1 = new de.wannawork.jcalendar.JCalendarPanel();
        jMonthPanel1 = new de.wannawork.jcalendar.JMonthPanel();
        testFrame1 = new de.wannawork.jcalendar.TestFrame();
        BtnAgregarProducto = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtBuscarProducto = new javax.swing.JTextField();
        cmbxFiltrarProductos = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Productos");

        BtnAgregarProducto.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Agregar.png")); // NOI18N
        BtnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAgregarProductoMouseClicked(evt);
            }
        });
        BtnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarProductoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Editar2.png")); // NOI18N
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Delete.png")); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtBuscarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProductoActionPerformed(evt);
            }
        });
        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
        });

        cmbxFiltrarProductos.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        cmbxFiltrarProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtrar", "Cigarrillos", "Bebidas", "Otros" }));
        cmbxFiltrarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmbxFiltrarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbxFiltrarProductosActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Actualizar.png")); // NOI18N
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 3, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Buscar");
        jLabel3.setToolTipText("");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\juanm\\Documents\\NetBeansProjects\\Profit\\src\\main\\java\\com\\juanma\\profit\\src\\imagenes\\Excel.png")); // NOI18N

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAgregarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbxFiltrarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditar)
                            .addComponent(jButton3)
                            .addComponent(BtnAgregarProducto)
                            .addComponent(btnActualizar)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbxFiltrarProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 941, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarProductoActionPerformed

    }//GEN-LAST:event_BtnAgregarProductoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionadaModificar = jTable2.getSelectedRow();
        if (filaSeleccionadaModificar == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FrmEditarProducto editarProducto = new FrmEditarProducto();
        editarProducto.setVisible(true);

    }//GEN-LAST:event_btnEditarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int filaSeleccionada = jTable2.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = (String) jTable2.getValueAt(filaSeleccionada, 1);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar este producto?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            ProductoPersistencia.eliminarProducto(codigo);

            cargarProductosEnTabla();

            JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private void txtBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoActionPerformed

    }//GEN-LAST:event_txtBuscarProductoActionPerformed

    private void BtnAgregarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAgregarProductoMouseClicked
        FrmAgregarProducto agregarProducto = new FrmAgregarProducto();
        agregarProducto.setVisible(true);


    }//GEN-LAST:event_BtnAgregarProductoMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarProductosEnTabla();
        configurarFiltrado();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        filtrarTabla();
    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void cmbxFiltrarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbxFiltrarProductosActionPerformed
        String categoriaSeleccionada = (String) cmbxFiltrarProductos.getSelectedItem();
        if (categoriaSeleccionada.equals("Categorias")) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + categoriaSeleccionada, 5));
        }
    }//GEN-LAST:event_cmbxFiltrarProductosActionPerformed
    
     


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarProducto;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JComboBox<String> cmbxFiltrarProductos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private de.wannawork.jcalendar.JCalendarComboBox jCalendarComboBox1;
    private de.wannawork.jcalendar.JCalendarPanel jCalendarPanel1;
    private javax.swing.JLabel jLabel3;
    private de.wannawork.jcalendar.JMonthPanel jMonthPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private de.wannawork.jcalendar.TestFrame testFrame1;
    private javax.swing.JTextField txtBuscarProducto;
    // End of variables declaration//GEN-END:variables
}
