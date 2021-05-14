/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import symplepacket.Objetos.Obj_Clientes;

/**
 *
 * @author blackdavo
 */
public class Ctr_CrearContenedor {
    
     conexion cx= new conexion();
    Connection cn= cx.conector();
   Ctr_Modelotabla mod = null;
       public Ctr_Modelotabla modeloCarrito (){
            mod = null;
            String[]columnas= new String[4];
            
            columnas[0]="CANTIDAD";
            columnas[1]="DESCRIPCION";
            columnas[2]="PRECIO/U";
            columnas[3]="IMPORTE";
            mod = new Ctr_Modelotabla(columnas);
            return mod;
        }
        public Ctr_Modelotabla modeloProducto (){
            mod = null;
            String[]columnas= new String[7];
            columnas[0]="ID";
            columnas[1]="CLASE";
            columnas[2]="MARCA";
            columnas[3]="NOMBRE";
            columnas[4]="MODELO";
            columnas[5]="CANTIDAD";
            columnas[6]="PRECIO";  
            mod = new Ctr_Modelotabla(columnas);
            return mod;
        }

        public Ctr_Modelotabla modelocliente (){
          mod = null;
          String[]columnas= new String[5];
          
          columnas[0]="NOMBRE";
          columnas[1]="DOCUMENTO";
          columnas[2]="CORREO";
          columnas[3]="TELEFONO";
          columnas[4]="DIRECCION";
          mod = new Ctr_Modelotabla(columnas);
            return mod;
       
       
}
              public Ctr_Modelotabla modeloTipoComp (){
          mod = null;
          String[]columnas= new String[1];

          columnas[0]="NOMBRE";
       
          mod = new Ctr_Modelotabla(columnas);
            return mod;
       
       
}

}
