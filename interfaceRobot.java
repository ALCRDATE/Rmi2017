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
    int getProgramme() throws RemoteException;
    String getAdress()throws RemoteException;
    boolean intialiseRobot() throws RemoteException;
    boolean iambusy() throws RemoteException;
    boolean robotExiste() throws RemoteException;
    void updateRobotBattery(int b) throws RemoteException;
    int getTaskID() throws RemoteException;
    void updateTaskProgrss(int p, int task) throws RemoteException;
    int getProgress(int task) throws RemoteException;
    void changeState(String state, int task) throws RemoteException;
}
