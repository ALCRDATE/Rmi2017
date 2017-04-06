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
public interface interfaceRobot extends Remote{
    boolean iamFree() throws RemoteException;
    void goToCharge() throws RemoteException;
    double batteryLevel() throws RemoteException;
    void sendState() throws RemoteException;
    String getProgramme() throws RemoteException;
    String getAdress()throws RemoteException;
    boolean intialiseRobot() throws RemoteException;
    boolean iambusy() throws RemoteException;
}
