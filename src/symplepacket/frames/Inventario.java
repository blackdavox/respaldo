/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.frames;

import symplepacket.controlador.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import symplepacket.controlador.Ctr_CrearContenedor;
import symplepacket.controlador.Ctr_LlenarObjetos;
import symplepacket.controlador.Ctr_Modelotabla;
import symplepacket.Objetos.Obj_Inventario;
import symplepacket.controlador.Ctr_MetodosGeneral;

/**
 *
 * @author blackdavo
 */
public final class Inventario extends javax.swing.JFrame {
       conexion cx= new conexion();
       Connection cn= cx.conector();
       
       Ctr_MetodosGeneral mg= new Ctr_MetodosGeneral();
        Ctr_LlenarObjetos lt= new Ctr_LlenarObjetos();
        Ctr_CrearContenedor cc=new Ctr_CrearContenedor();
        Ctr_Modelotabla inventario;
        Carrito cr= new Carrito();
        Obj_Inventario[] inv;
        
       
    public Inventario() {
        initComponents();
        iniciar("","");
        
    }

    void iniciar(String prt, String txt){
        inventario=cc.modeloProducto();
        inv=mg.buscar_producto(prt, txt);
             for(Obj_Inventario c :inv){
                 inventario.SetObjeto(c);
               }
             if(inv!=null){
              tablaproducto.setModel(inventario); 
             }
        
        tablaproducto.getTableHeader().setReorderingAllowed(false);
        tablaproducto.setRowHeight(25);
   
    }
   
  
    
     void BuscarDatos(){
         String bus,op;
         bus=txtbus.getText();
         op=cbreg.getSelectedItem().toString();
                 
            iniciar(op, bus);
         
         
     }
     void addCarrito(){ 
            Obj_Inventario auxInventario;
            auxInventario = (Obj_Inventario)inventario.GetObjeto(tablaproducto.getSelectedRow());
        
            if(auxInventario!=null){   
            if(auxInventario.GetCan()>=Integer.parseInt(txtcan.getText())){
             try {
             String rtn = "agg_carrito";
             int cant =Integer.parseInt(txtcan.getText().toString());
                CallableStatement statement = cn.prepareCall("{call "+rtn+"(?,?)}");
                statement.setInt(1, auxInventario.GetId());
                statement.setInt(2, cant);
                statement.execute();
                statement.close();
                System.out.println("arrecho!");

                    JOptionPane.showMessageDialog(null, "Datos Guardados");

                        }catch (SQLException ex) {
                            Logger.getLogger(IngresarProducto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        else{
                           JOptionPane.showMessageDialog(null,"Cantidad Insuficiente","Advertencia",JOptionPane.WARNING_MESSAGE);
                        }  
            }
        
     }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popderecho = new javax.swing.JPopupMenu();
        carrito = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproducto = new javax.swing.JTable();
        btnbus = new javax.swing.JButton();
        cbreg = new javax.swing.JComboBox<>();
        txtbus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcan = new javax.swing.JTextField();
        Agg = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        carrito.setText("ANADIR AL CARRITO");
        carrito.setInheritsPopupMenu(true);
        carrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carritoActionPerformed(evt);
            }
        });
        popderecho.add(carrito);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("I N V E N T A R I O");

        tablaproducto = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaproducto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablaproducto.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        tablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaproducto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaproducto.setComponentPopupMenu(popderecho);
        tablaproducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaproducto.setGridColor(new java.awt.Color(0, 102, 255));
        tablaproducto.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(tablaproducto);

        btnbus.setText("B U S C A R");
        btnbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusActionPerformed(evt);
            }
        });

        cbreg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "CODIGO", "MARCA", "CATEGORIA" }));

        jLabel2.setText("CANTIDAD");

        txtcan.setText("1");

        Agg.setText("AGREGAR ");
        Agg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AggActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(27, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(cbreg, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(txtbus)
                                .addGap(44, 44, 44)
                                .addComponent(btnbus)
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(356, 356, 356)))))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157)
                .addComponent(Agg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbus))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtcan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Agg, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusActionPerformed
        BuscarDatos();
    }//GEN-LAST:event_btnbusActionPerformed

    private void carritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carritoActionPerformed

        addCarrito();  
        iniciar(null,null);
    }//GEN-LAST:event_carritoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        cr.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void AggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AggActionPerformed
             addCarrito();
    }//GEN-LAST:event_AggActionPerformed

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agg;
    private javax.swing.JButton btnbus;
    private javax.swing.JMenuItem carrito;
    private javax.swing.JComboBox<String> cbreg;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu popderecho;
    private javax.swing.JTable tablaproducto;
    private javax.swing.JTextField txtbus;
    private javax.swing.JTextField txtcan;
    // End of variables declaration//GEN-END:variables
}
