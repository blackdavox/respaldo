/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.Objetos;

/**
 *
 * @author blackdavo
 */
public class Obj_Carrito {
    
    
     private int crId;
     private int crCantidad;
     private int crProductoId;
     private String crDescr;
     private String crPrecioU;
     private String crImporte;

    public int GetId(){return crId;} 
    public int GetCant(){return crCantidad;}
    public int GetPid(){return crProductoId;} 
    public String GetDescr(){return crDescr;}
    public String GetPreciou(){return crPrecioU;}
    public String GetImporte(){return crImporte;}
   

    public Obj_Carrito (int Id,int proid,int cant,String desc,String pu,String imp ) {
       crId=Id;
       crCantidad=cant;
       crProductoId= proid;
       crDescr=desc;
       crPrecioU=pu;
       crImporte= imp;
     
    }
     @Override
      public String toString()
    {
        return crCantidad+"*"+crDescr+"*"+crPrecioU+"*"+crImporte;
    }    
    
    
    
}
