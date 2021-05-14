/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.controlador;

import symplepacket.Objetos.Obj_Clase_Producto;
import symplepacket.Objetos.Obj_Clientes;
import symplepacket.Objetos.Obj_Inventario;
import symplepacket.Objetos.Obj_Tipo_Comprobante;

    
public class Ctr_MetodosGeneral {
    Ctr_LlenarObjetos lt=new Ctr_LlenarObjetos();
     public static void main(String[] args) {
        
       
    }
    public Obj_Inventario[] buscar_producto(String prt, String txt){
         Obj_Inventario[] auxbus=null;             
         switch(prt){
            case "CODIGO":
                 prt= " pro_producto_id ";
                 break;
                 
            case "NOMBRE":
                 prt= " pro_nombre_producto ";
                 break;
                 
            case "MARCA":
                 prt= " pro_marca_producto ";
                 break;
                 
            case "CATEGORIA":
                 prt= " cpp.cp_nombre_clase ";
                 break;   
         }
         auxbus=lt.GetInventario(prt, txt);
         
         return auxbus;
    }
    
    public Obj_Clientes[] buscar_cliente(String prt, String txt){
         Obj_Clientes[] auxbus=null;             
         switch(prt)
         {
            case "DOCUMENTO":
                 prt= "cli_documento_cliente";
                 break;
                 
            case "NOMBRE":
                 prt= " cli_nombres_cliente ";
                 break;
                 
            case "APELLIDO":
                 prt= " cli_apellidos_cliente ";
                 break;
         }
         auxbus=lt.Getclientes(prt, txt);
         return auxbus;
    }
    
    public Obj_Tipo_Comprobante[] buscar_tipocomprobante(String campo,String valor){
         Obj_Tipo_Comprobante[] auxtc=null;             
         switch(campo)
         {
            case "NOMBRE":
                 campo = "tc_nombre_ti";
                 break;
         }
         auxtc=lt.GetTipoComp(campo, valor);
         return auxtc;
    }
    
    public Obj_Clase_Producto[] buscar_claseproducto(String campo,String valor){
    Obj_Clase_Producto[] auxcp=null;
         switch(campo)
         {
            case "NOMBRE":
                 campo = "tc_nombre_ti";
                 break;
         }
         auxcp=lt.GetClaseProducto(campo, valor);
         return auxcp;
    }
}
