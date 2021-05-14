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


public class Obj_Inventario {
     private int id;
     private String clase;
     private String marca;
     private String nombre;
     private String modelo;
     private int cantidad;
     private String precio;
     
    public int GetId(){return id;} 
    public String GetCla(){return clase;}
    public String GetMar(){return marca;}
    public String GetNom(){return nombre;}
    public String GetMod(){return modelo;}
    public int GetCan(){ return cantidad;}
    public String GetPre(){return precio;}
    

    public Obj_Inventario (int Id,String cla,String mar,String nom,String mod,int can, String pre ) {
       id=Id;
       clase=cla;     
       marca=mar;
       nombre= nom;
       modelo= mod;
       cantidad= can;    
       precio=pre;
    }
     @Override
      public String toString()
    {
        return id+"*"+clase+"*"+marca+"*"+nombre+"*"+modelo+"*"+cantidad+"*"+precio;
    }    


}
