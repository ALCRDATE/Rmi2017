/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrmirobot;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author alcrdate
 */
public class objTache extends UnicastRemoteObject implements interfaceTache, Serializable {
    private static Connexion c = null;
    public objTache() throws RemoteException{
        c = Connexion.getInstance("jdbc:mysql://localhost/projet","root","");
    }
    
    
    
    @Override
    public int addTache(String user, String local) throws RemoteException {
        try{
            c.connecter();
        c.update("INSERT INTO `tache_a_faire` (`id`, `user`, `robot`, `local`, `status`) VALUES (NULL, '"+user+"', 1 , '"+local+"', 'not_done')");
        ResultSet rs = c.lire("select max(id) as id from tache_a_faire");
        Vector t = new Vector();
        while(rs.next()){
            t.add(rs.getInt("id"));
        }
        return (int) t.get(0);
        }catch(Exception e){
            System.out.println("the probleme in the requel");
            return -1;
        }
    }

    @Override
    public int selectRobot() throws RemoteException {
        try {
            c.connecter();
            ResultSet rs = c.lire("select id from robot where status = 'free'");
            Vector t = new Vector();
            while(rs.next()){
                int idRobot = rs.getInt("id");
                t.add(idRobot);
            }
            int idRobot = (int) t.get(0);
            return idRobot;
        } catch (Exception e) {
            System.out.println("Probleme de selection de robot ...");
            return -1;
        }
        
    }
    
    @Override
    public int setRobot(int id, int idr){
        try {
            c.connecter();
            int r = c.update("update tache_a_faire set robot = " + idr + " where id = " + id);
            return r;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean selectTime(int id) throws RemoteException {
           return true;
    }

    @Override
    public boolean sendProgramme(int id, int idr) throws RemoteException {
        try {
        c.connecter();
        c.update("update tache_a_faire set status = 'start' where id = " + id);
        
        c.update("update robot set status = 'busy' where id = " + idr);
        return true;
        } catch (Exception e) {
            System.out.println("there is a problem in the programSend");
            return false;
        }
        
    }
    
}
