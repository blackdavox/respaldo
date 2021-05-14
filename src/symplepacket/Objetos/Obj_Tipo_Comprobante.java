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
public class Obj_Tipo_Comprobante {
    private String nombre;
    private int id;

  
    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }
     public Obj_Tipo_Comprobante (int Id,String nom ) {
        nombre = nom;
        id = Id;
    } 
   
     @Override
      public String toString()
    {
     return nombre ;
    }
}
