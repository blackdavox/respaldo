/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import symplepacket.Objetos.Obj_Calculos;
import symplepacket.Objetos.Obj_Carrito;
import symplepacket.Objetos.Obj_Clase_Producto;
import symplepacket.Objetos.Obj_Clientes;
import symplepacket.Objetos.Obj_Inventario;
import symplepacket.Objetos.Obj_Tipo_Comprobante;
import symplepacket.frames.Carrito;
import symplepacket.frames.Inventario;

/**
 *
 * @author blackdavo
 */
public class Ctr_LlenarObjetos extends javax.swing.JFrame {
    
    conexion cx= new conexion();
    Connection cn= cx.conector();
    Ctr_CrearContenedor ct= new Ctr_CrearContenedor();
    Ctr_Modelotabla mod;
    
    
    int x;
    
  
    public Obj_Inventario[] GetInventario(String campo, String valor){
        Obj_Inventario[] iv=null;
        int tam,i=0;
       
        try {
            CallableStatement st = cn.prepareCall("call show_products (?,?)");  
              st.setString(1, campo);
              st.setString(2, valor);
              st.execute();
            ResultSet rs = st.executeQuery();
            rs.last();
            tam= rs.getRow();
            rs.beforeFirst();               
            iv = new Obj_Inventario[tam];
            while(rs.next()){
                iv[i]=new Obj_Inventario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));                         
                  i++;
            }
            st.close();
        } catch (SQLException ex) {          
        }      
        return iv;
    }   
    
    public Obj_Clientes[] Getclientes(String campo, String valor) {
         Obj_Clientes[] dc=null;
         int tam,i=0;
          try {
            CallableStatement st = cn.prepareCall("call show_clientes (?,?)");  
            st.setString(1, campo);
            st.setString(2, valor);
            st.execute();
            ResultSet rs = st.executeQuery();
            rs.last();
            tam= rs.getRow();
            rs.beforeFirst(); 
              dc = new Obj_Clientes [tam];
             while(rs.next()){
                 dc[i]=new Obj_Clientes( Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));            
                 i++;
             }
                st.close();
         } catch (SQLException ex) {
         }
             return dc;
    }
    
    public Obj_Tipo_Comprobante[] GetTipoComp(String campo, String valor){
            Obj_Tipo_Comprobante[] tc=null;
            int tam,i=0;
             try {
                CallableStatement st = cn.prepareCall("call show_tipocom (?,?)");  
                st.setString(1, campo);
                st.setString(2, valor);
                st.execute();
                ResultSet rs = st.executeQuery();
                System.out.print(rs.toString());
                rs.last();
                tam= rs.getRow() ;
                rs.beforeFirst();
                tc= new Obj_Tipo_Comprobante[tam];
                 while(rs.next()){ 
                   tc[i]=new Obj_Tipo_Comprobante(rs.getInt(1),rs.getString(2));
                   
                   i++;
                }
                 st.close();
                  }catch (SQLException ex) {
                  
                  }catch( NullPointerException ex){
                      System.out.print(ex.toString());
                  }
          
             
            return tc;
        }
    
    public Obj_Clase_Producto[] GetClaseProducto(String campo, String valor){
        Obj_Clase_Producto[]cp=null;
        int tam,i=0;
         try {
             CallableStatement st = cn.prepareCall("call show_claseprod (?,?)");  
             st.setString(1, campo);
             st.setString(2, valor);
             st.execute();
             ResultSet rs = st.executeQuery();
             
             rs.last();
             tam= rs.getRow();
             rs.beforeFirst();
             
             cp= new Obj_Clase_Producto[tam];
             while(rs.next()){ 
                cp[i]=new Obj_Clase_Producto(rs.getInt(1),rs.getString(2));
                i++;
            }
             st.close();
              }catch (SQLException ex) {}
                return cp;
        }
      
    public Obj_Carrito[] GetCarrito(){
        Obj_Carrito[] cr=null;
        int tam,i=0;
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery("call show_car");
                    rs.last();
                    tam= rs.getRow();
                    rs.beforeFirst();
                    cr = new Obj_Carrito[tam];
                        while(rs.next()){
                          cr[i]=new Obj_Carrito(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6)); 
                            i++;          
                        }  
                            st.close();
                    }catch (SQLException ex) {                           
                            }
        return cr;
    }
    
    public Obj_Calculos llenarObjCalculos(){
        Obj_Calculos objCal=null;
         try {
             
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("call calculos_carrito");
             while(rs.next()){
            objCal = new Obj_Calculos(rs.getDouble(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4));
            }
             st.close();
         } catch (SQLException ex) {
             Logger.getLogger(Ctr_LlenarObjetos.class.getName()).log(Level.SEVERE, null, ex);
         }
         return objCal;
    }
    
    public Obj_Tipo_Comprobante[] pruebatc(){
        
        Obj_Tipo_Comprobante[] pp=null;
        int tam,i=0;
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery("select * from tipo_comprobante;");
//                    st.close();
                    rs.last();
                    
                    tam= rs.getRow();
                    rs.beforeFirst();
                    pp = new Obj_Tipo_Comprobante[tam];
                        while(rs.next()){
                          pp[i]=new Obj_Tipo_Comprobante(rs.getInt(1),rs.getString(2)); 
                            i++;          
            }           
                    }catch (SQLException ex) {                           
                            }
        return pp;
    }
    
     public Obj_Clase_Producto[] pruebacp(){
        
        Obj_Clase_Producto[] pp=null;
        int tam,i=0;
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery("select * from claseproducto");
//                    st.close();
                    rs.last();
                    tam= rs.getRow();
                    rs.beforeFirst();
                    pp = new Obj_Clase_Producto[tam];
                        while(rs.next()){
                          pp[i]=new Obj_Clase_Producto(rs.getInt(1),rs.getString(2)); 
                            i++;          
            }           
                    }catch (SQLException ex) {                           
                            }
        return pp;
    }
    
  
    
    
    }

