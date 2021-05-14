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
public class GenerarVenta {
     
    private int TipoId, ClienteId;
   /* public void setName(String Nombre)
    {
        nombre = Nombre; 
    }
        public void setId(int Id)
    {
        id = Id;
    }*/
    public int getTipoId()
    {
        return TipoId;
    }
     public int getClienteId()
    {
        return ClienteId;
    }
     public GenerarVenta (int Tid,int Cid ) {
        TipoId = Tid;
        ClienteId= Cid;
    } 
   
}
