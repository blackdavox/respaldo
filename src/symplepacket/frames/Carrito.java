/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.frames;

import java.sql.CallableStatement;
import symplepacket.controlador.conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultComboBoxModel;
import symplepacket.Objetos.Obj_Tipo_Comprobante;
import symplepacket.Objetos.Obj_Calculos;
import symplepacket.controlador.Ctr_CrearContenedor;
import symplepacket.controlador.Ctr_LlenarObjetos;
import symplepacket.controlador.Ctr_Modelotabla;
import symplepacket.Objetos.Obj_Carrito;
import symplepacket.Objetos.Obj_Clientes;
import symplepacket.Objetos.Obj_ComprobanteVenta;

/**
 *
 * @author blackdavo
 */
public final class Carrito extends javax.swing.JFrame {
        
        conexion cx= new conexion();
        Connection cn= cx.conector();
        
        DefaultComboBoxModel cbox = null;
        Ctr_Modelotabla carrito;
        Ctr_CrearContenedor cc= new Ctr_CrearContenedor();
        Ctr_LlenarObjetos lt= new Ctr_LlenarObjetos();
        parametrizar prt= new parametrizar();
        
        Obj_Carrito[] cr;
        Obj_Tipo_Comprobante[] tp;
            private Obj_Calculos auxcalculos;
            private Obj_Clientes auxclie=null;
            private Obj_ComprobanteVenta comprobante=null;
      
        public Carrito() {
        
        initComponents();
        iniciar();
        
    }
        
        private void iniciar(){
            carrito=        cc.modeloCarrito();
            tp=             lt.GetTipoComp("0","0");
            auxcalculos=    lt.llenarObjCalculos();
            cr=             lt.GetCarrito();
                       
            cbox = new DefaultComboBoxModel(tp);
            tipo_cpb.setModel(cbox);
            
            for(Obj_Carrito c: cr){
                carrito.SetObjeto(c);
            }
            txtsub.setText(String.valueOf(auxcalculos.GetSubtotal()));
            txtivap.setText(String.valueOf(auxcalculos.GetIvaPor()));
            txtiva.setText(String.valueOf(auxcalculos.GetIvaTotal()));
            txttot.setText(String.valueOf(auxcalculos.GetTotal()));
            tablaventa.setModel(carrito);
            modelartabla();
        }
        private void modelartabla(){
            tablaventa.setRowHeight(25);
            
            TableColumnModel columnModel = tablaventa.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);
            columnModel.getColumn(1).setPreferredWidth(450);
            columnModel.getColumn(2).setPreferredWidth(50);
            columnModel.getColumn(3).setPreferredWidth(81);
           
        }
        void btnGnrVenta(){
         Obj_Tipo_Comprobante auxtp= GetTp();
         
         if(auxtp!= null&& auxclie != null){
         comprobante= new Obj_ComprobanteVenta(auxtp.getId(),auxclie.GetId());   
           System.out.print(comprobante.GetTipoId()+""+ comprobante.GetClieId());
             
               String rtn = "create_cv"; 
             try (
                 CallableStatement statement = cn.prepareCall("{call "+rtn+"(?,?,?)}")) {
                 statement.setInt(1, comprobante.GetTipoId());
                 statement.setInt(2, comprobante.GetClieId());
                 statement.setDouble(3, prt.GetIva());
                 statement.execute();
                 JOptionPane.showMessageDialog(null, "Datos Guardados");
             }
                    catch (SQLException ex) {
                        Logger.getLogger(IngresarProducto.class.getName()).log(Level.SEVERE, null, ex);
                    } 
            }
         else 
             JOptionPane.showMessageDialog(null, "FALTAN DATOS POR AGREGAR");
        }   
        void btnvaciarItem(){
         Obj_Carrito auxcarrito=null;
         auxcarrito = (Obj_Carrito)carrito.GetObjeto(tablaventa.getSelectedRow());
       
        if(auxcarrito!=null){  
            String rtn = "return_inventario"; 
            try {   
            CallableStatement statement = cn.prepareCall("{call "+rtn+"(?,?)}");
            statement.setInt(1, auxcarrito.GetPid());
            statement.setInt(2, auxcarrito.GetCant());
            statement.execute();
            statement.close();
                
            
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                    }catch (SQLException ex) {
                        Logger.getLogger(IngresarProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }                
        } 
                iniciar();
        }
        void btnvaciarTodo(){
            Obj_Carrito auxcarrito=null;
            auxcarrito = (Obj_Carrito)carrito.GetObjeto(tablaventa.getSelectedRow());
            int filas = tablaventa.getRowCount();  
            for(int i=0; i<filas;i++){
                if(i>=0){   
                      try {       
                    String rtn = "return_inventario";      
                    CallableStatement statement = cn.prepareCall("{call "+rtn+"(?,?)}");
                    statement.setInt(1, auxcarrito.GetPid());
                    statement.setInt(2, auxcarrito.GetCant());
                    statement.execute();
                    statement.close();
                            }catch (SQLException ex) {
                                Logger.getLogger(IngresarProducto.class.getName()).log(Level.SEVERE, null, ex);
                            }   
                } 
                }
                JOptionPane.showMessageDialog(null, "Datos Guardados");
                iniciar();
                }
        void btncliente(){  
                   clientes cl= new clientes();
                   cl.setVisible(true);
                }           
        void actioncliente(Obj_Clientes objcc){
           auxclie=objcc;
           txtnom.setText(objcc.GetNom());
           txtced.setText(objcc.GetDoc());
           txtcor.setText(objcc.GetCor());
           txttel.setText(objcc.GetTel());
           txtdir.setText(objcc.GetDir());
        }   
        public Obj_Tipo_Comprobante GetTp(){
            Obj_Tipo_Comprobante  aux = (Obj_Tipo_Comprobante)tipo_cpb.getSelectedItem();
            return aux;
        }
        

        
         
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop = new javax.swing.JPopupMenu();
        VaciarItem = new javax.swing.JMenuItem();
        btnGenerarVenta = new javax.swing.JButton();
        txttot = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtnom = new javax.swing.JLabel();
        txttel = new javax.swing.JLabel();
        txtced = new javax.swing.JLabel();
        txtdir = new javax.swing.JLabel();
        txtcor = new javax.swing.JLabel();
        tipo_cpb = new javax.swing.JComboBox<>();
        txtnumcpb = new javax.swing.JTextField();
        txtivap = new javax.swing.JLabel();
        txtiva = new javax.swing.JLabel();
        txtsub = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        btnvaciar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        VaciarItem.setText("Eliminar Item");
        VaciarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VaciarItemActionPerformed(evt);
            }
        });
        pop.add(VaciarItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGenerarVenta.setText("GENERAR VENTA");
        btnGenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, -1, 32));

        txttot.setBackground(new java.awt.Color(153, 255, 255));
        txttot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txttot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txttot.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL"));
        txttot.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(txttot, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 380, 100, 50));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(51, 153, 255));

        txtnom.setBorder(javax.swing.BorderFactory.createTitledBorder("NOMBRES"));

        txttel.setBorder(javax.swing.BorderFactory.createTitledBorder("TELEFONO"));

        txtced.setBorder(javax.swing.BorderFactory.createTitledBorder("CEDULA/RUC"));

        txtdir.setBorder(javax.swing.BorderFactory.createTitledBorder("DIRECCION"));

        txtcor.setBorder(javax.swing.BorderFactory.createTitledBorder("CORREO"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipo_cpb, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnumcpb, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcor, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtced, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tipo_cpb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnumcpb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtced, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 700, 200));

        txtivap.setBackground(new java.awt.Color(153, 255, 255));
        txtivap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtivap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtivap.setText("0");
        txtivap.setBorder(javax.swing.BorderFactory.createTitledBorder("IVA%"));
        txtivap.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(txtivap, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 100, 50));

        txtiva.setBackground(new java.awt.Color(153, 255, 255));
        txtiva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtiva.setBorder(javax.swing.BorderFactory.createTitledBorder("IVA"));
        txtiva.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(txtiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 320, 100, 50));

        txtsub.setBackground(new java.awt.Color(153, 255, 255));
        txtsub.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtsub.setBorder(javax.swing.BorderFactory.createTitledBorder("SUBTOTAL"));
        txtsub.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(txtsub, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 220, 100, 50));

        tablaventa = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaventa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaventa.setComponentPopupMenu(pop);
        jScrollPane2.setViewportView(tablaventa);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 720, 330));

        btnvaciar.setText("VACIAR");
        btnvaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvaciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnvaciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 120, 30));

        jButton2.setText("CLIENTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 115, 32));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VaciarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VaciarItemActionPerformed
    btnvaciarItem();
    }//GEN-LAST:event_VaciarItemActionPerformed

    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarVentaActionPerformed
   
     btnGnrVenta();
    }//GEN-LAST:event_btnGenerarVentaActionPerformed

    private void btnvaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvaciarActionPerformed
    btnvaciarTodo();
     
    }//GEN-LAST:event_btnvaciarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        btncliente();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carrito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem VaciarItem;
    private javax.swing.JButton btnGenerarVenta;
    private javax.swing.JButton btnvaciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu pop;
    private javax.swing.JTable tablaventa;
    private javax.swing.JComboBox<String> tipo_cpb;
    private javax.swing.JLabel txtced;
    private javax.swing.JLabel txtcor;
    private javax.swing.JLabel txtdir;
    private javax.swing.JLabel txtiva;
    private javax.swing.JLabel txtivap;
    private javax.swing.JLabel txtnom;
    private javax.swing.JTextField txtnumcpb;
    private javax.swing.JLabel txtsub;
    private javax.swing.JLabel txttel;
    private javax.swing.JLabel txttot;
    // End of variables declaration//GEN-END:variables
}
