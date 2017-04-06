/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverrmirobot;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alcrdate
 */
public interface interfaceTache extends Remote {
    int addTache(String user, String local) throws RemoteException;
    int selectRobot() throws RemoteException;
    boolean selectTime(int id) throws RemoteException;
    boolean sendProgramme(int id, int idr) throws RemoteException;
    int setRobot(int id,int idr) throws RemoteException;
}
