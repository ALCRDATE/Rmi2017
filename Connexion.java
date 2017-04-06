    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrmirobot;

import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alcrdate
 */
public class Connexion {
    private String url,user,pswd;
    private static Connexion cne = null;
    private Connection cn = null;
    private Connexion (String a, String b, String c){
        try {
            cn = DriverManager.getConnection(a,b,c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Connexion getInstance(String a, String b, String c){
        if(cne == null){
            cne = new Connexion(a,b,c);
            return cne;
        }
       // JOptionPane.showMessageDialog(null, "Objet deja cree");
        return cne;
    }
    
    
    public boolean connecter(){
        try{
            cn = DriverManager.getConnection(url,user,pswd);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    public int update(String query){
        int r;
    try {
    Statement st = cn.createStatement();
     r = st.executeUpdate(query);
     return r;
    }catch(SQLException e){
    
    }
    return -1;
    }
    
    
    public ResultSet lire(String query){
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public DefaultTableModel getTable(String query, String[] champs){
        DefaultTableModel m = new DefaultTableModel();
        m.setColumnIdentifiers(champs);
        try{
            ResultSet rs = lire(query);
            if (rs == null) return m;
            while (rs.next()){
                Vector t = new Vector();
                for (int i = 1; i <= champs.length ; i++) t.add(rs.getObject(i));
                    m.addRow(t);
            }
        }catch(Exception ex){
                return m;
        }
        return m;
    }
    
    public DefaultTableModel getTable(String query){
        DefaultTableModel m = new DefaultTableModel();
        try{ 
          Statement state1 = cn.createStatement();
          ResultSet rs4 = state1.executeQuery(query);
          ResultSetMetaData rsMeta = rs4.getMetaData();
          String []k = new String[rsMeta.getColumnCount()];
          for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
              k[i-1] = rsMeta.getColumnName(i);
          }
            m.setColumnIdentifiers(k);
            
            if (rs4 == null) return m;
            while (rs4.next()){
                Vector t = new Vector();
                for (int i = 1; i <= rsMeta.getColumnCount() ; i++) t.add(rs4.getObject(i));
                    m.addRow(t);
            }
        }catch(Exception ex){
                return m;
        }
        return m;
//        DefaultTableModel m = new DefaultTableModel();
//        try{
//        Statement state1 = cn.createStatement();
//        ResultSet rs4 = state1.executeQuery(query);
//        ResultSetMetaData rsMeta = rs4.getMetaData();
//            System.out.println(rsMeta.getColumnCount());
//            for (int i = 1; i <= rsMeta.getColumnCount(); i++) {
//                System.out.println("column " + i + " : "
//                + rsMeta.getColumnName(i) + " - "
//                + rsMeta.getColumnType(i) + " - "
//                + rsMeta.getColumnDisplaySize(i));
//            }
//        }catch(SQLException ex){
//            ex.printStackTrace();
//        }
//        return m;
    }
}
