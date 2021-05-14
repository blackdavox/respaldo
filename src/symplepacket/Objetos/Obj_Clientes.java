/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.Objetos;

import symplepacket.frames.Carrito;

/**
 *
 * @author blackdavo
 */
public class Obj_Clientes {
     private int id;
     private String nombres;
     private String apellidos;
     private String documento;
     private String correo;
     private String telefono;
     private String direccion;

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int GetId(){return id;} 
    public String GetNom(){return nombres;}
    public String GetApe(){return apellidos;}
    public String GetDoc(){return documento;}
    public String GetCor(){ return correo;}
    public String GetTel(){return telefono;}
    public String GetDir(){return direccion;}
    
    
    
     public Obj_Clientes (int Id,String nom,String doc,String cor,String tel, String dir ) {
       id=Id;
       nombres=nom;     
       documento=doc;
       correo= cor;
       telefono= tel;
       direccion= dir;    
    }
     @Override
      public String toString()
    {
        return nombres+"*"+documento+"*"+correo+"*"+telefono+"*"+direccion;
    }    
}
