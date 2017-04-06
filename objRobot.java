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

/**
 *
 * @author alcrdate
 */
public class objRobot extends UnicastRemoteObject implements interfaceRobot, Serializable {
    private static String adress;
    private static Connexion c = null;
    
    public objRobot(String adr) throws RemoteException{
        this.adress = adr;
        c = Connexion.getInstance("jdbc:mysql://localhost/projet","root","");
    }
    
    public objRobot() throws RemoteException{
        
    }
    
    @Override
    public boolean intialiseRobot(){
        c.connecter();
        int r = c.update("INSERT INTO `robot` (`id`, `adress`, `status`, `battery`) VALUES (NULL, '"+this.adress+"', 'busy', '0');");
        if(r == 1){
        return true;
        }else{
            return false;
        }
    }
    
    @Override
    public boolean iamFree() throws RemoteException {
        try {
            c.connecter();
            c.update("update robot set status = 'free' where adress = '" + this.adress +"'");
            return true;
        } catch (Exception e) {
            return false;
        }

        
    }

    @Override
    public void goToCharge() throws RemoteException {
        try {
            c.connecter();
            float b = 0;
            ResultSet rs = c.lire("select battery from robot where adress = '" + this.adress + "'");
            while (rs.next()){
                b = rs.getFloat("battery");
                int r = (int) b;
                for (int i = r; i < 100; i++){
                    b = i;
                    Thread.sleep(100);
                    System.out.println("battery : " + b + "%");
                    c.update("update robot set battery = "+ b + "where adress = '" +this.adress + "'");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public double batteryLevel() throws RemoteException {
        float b = 0;
        try {
            c.connecter();
            ResultSet rs = c.lire("select battery from robot where adress = '" + this.adress + "'");
            
            while(rs.next()){
                b = rs.getFloat("battery");
                return b;
            }
            
        }catch (Exception e) {
            return -1;
        }
        return b;
    }

    @Override
    public void sendState() throws RemoteException {
        try {
            c.connecter();
            c.lire("select status from robot where adress = '" + getAdress() + "'");
        } catch (Exception e) {
        }
    }

    @Override
    public String getProgramme() throws RemoteException {
        try {
            c.connecter();
            ResultSet rs = c.lire("select * from tache_a_faire as t, robot r where t.robot = r.id and adress = '" + this.adress + "' and t.status = 'start'");
            while (rs.next()){
                String s = rs.getString("local");
                return s;
            }
            return "no class";
        } catch (Exception e) {
            return "void";
        }
    }
    
    @Override
    public boolean iambusy() throws RemoteException{
         try {
            c.connecter();
            c.update("update robot set status = 'busy' where adress = '" + this.adress +"'");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public  String getAdress(){
        return this.adress;
    }
}
