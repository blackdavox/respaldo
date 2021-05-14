/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.controlador;

import symplepacket.Objetos.Obj_Inventario;

    
public class Ctr_MetodosGeneral {
    Ctr_LlenarObjetos lt=new Ctr_LlenarObjetos();
    Obj_Inventario[] auxbu=null;
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
                 prt= "cpp.cp_nombre_clase";
                 break;   
                 
            case "":
                 prt= "";
                 break;
         }
         auxbus=lt.GetInventario(prt, txt);
         
         return auxbus;
    }
    
   
      public void dd(){
          
      }
}
