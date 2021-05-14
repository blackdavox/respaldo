/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symplepacket.controlador;

import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author blackdavo
 */
public class Ctr_Modelotabla implements TableModel {
    private Object[]columnas;
    private LinkedList datos = new LinkedList();
    private LinkedList <Object[]> datosxcelda = new LinkedList<Object[]>();
    
  
    public Ctr_Modelotabla(String[] columnas){
        this.columnas=columnas;
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnas[i].toString();
    }

    @Override
    public Class<?> getColumnClass(int i) {
      return Object.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        return datosxcelda.get(rowIndex)[columnIndex].toString();
    }

    @Override
    public void setValueAt(Object dato, int fila, int columna) {
//        Obj_Clientes aux = (Obj_Clientes)datos.get (fila);       
//        switch (columna) { 
//           case 0:                                                     
//               aux.setId((String)dato);
//               break;
//          
//           case 1:                                                      
//               aux.setNombres((String)dato);
//               break;
//           // Nos pasan la edad.
//           case 2:                                                      
//               aux.setDocumento(((String)dato));
//           case 3:                                                      
//                aux.setCorreo(((String)dato));
//           case 4:                                                      
//                 aux.setTelefono(((String)dato));
//           case 5:                                                      
//               aux.setDireccion(((String)dato));
//        }
//        TableModelEvent evento = new TableModelEvent (this, fila, fila, columna);
//        int i;
//        for (i=0; i<suscriptores.size(); i++)
//            ((TableModelListener)suscriptores.get(i)).tableChanged(evento);
    }
    
    @Override
    public void addTableModelListener(TableModelListener tl) {
       
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
         
    }
    
    
    
     public Object GetObjeto(int rowIndex){
        return (datos.get(rowIndex));
    }
    public void SetObjeto(Object obj) {
    datos.add (obj);
    
    LinkedList aux= new LinkedList();
    String AllData=obj.toString();
    StringTokenizer tokens=new StringTokenizer(AllData,"*");
    while(tokens.hasMoreTokens()){
        aux.add(tokens.nextToken());
    }
    datosxcelda.add(aux.toArray());

    }
    
}
