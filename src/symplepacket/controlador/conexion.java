/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.controlador;

/**
 *
 * @author blackdavo
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import symplepacket.frames.Carrito;
import symplepacket.frames.clientes;


public class conexion {

    
    Connection con;
        public Connection conector() {
//        con=null;
         try{
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection ("jdbc:mysql://localhost/symplepacket","root", "");
           } catch (Exception e){
                 } 
         
         
        return con;
    }
        
   
        }

