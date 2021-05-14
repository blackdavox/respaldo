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
public class Obj_Calculos {
    
    private double subtotal;
    private double ivapor;
    private double ivatotal;
    private double total;
    
    public double GetSubtotal(){ return subtotal;}
     public double GetIvaPor(){ return ivapor;}
      public double GetIvaTotal(){ return ivatotal;}
       public double GetTotal(){ return total;}
    
       public Obj_Calculos(double sub, double ivap, double ivatot,double tot){
           subtotal=sub;
           ivapor=ivap;
           ivatotal=ivatot;
           total=tot;
       }
}
