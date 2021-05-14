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
public class Obj_ComprobanteVenta {
    
    private int Id;
    private int TipoId;
    private int ClieId;
    
    public void SetId(int id){id=Id;}
    public void SetTipoId(int Id){TipoId=Id;}
    public void SetClieId(int Id){ClieId=Id;}
    
    public int GetId(){return Id;}
    public int GetTipoId(){return TipoId;}
    public int GetClieId(){return ClieId;}
   
    public Obj_ComprobanteVenta( int tipoid, int clieid){
        
        TipoId=tipoid;
        ClieId= clieid;
    }
}
