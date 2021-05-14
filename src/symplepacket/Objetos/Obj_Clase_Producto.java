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
public class Obj_Clase_Producto {
    
    private int Id;
    private String Nombre;
    
    
    public Obj_Clase_Producto(int id, String nom){
        Id= id;
        Nombre=nom;
    }
    
    @Override
      public String toString()
    {
     return Nombre ;
    }
}
