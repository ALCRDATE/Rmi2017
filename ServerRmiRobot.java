/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrmirobot;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author alcrdate
 */
public class ServerRmiRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            objTache tache = new objTache();
            objRobot objs = new objRobot("192.168.1.2555");
            Naming.rebind("rmi://localhost:1099/rbt",objs);
            Naming.rebind("rmi://localhost:1099/tsk",tache);
        } catch (Exception e) {
        }
    }
    
}
