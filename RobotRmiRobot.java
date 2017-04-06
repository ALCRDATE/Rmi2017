/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrmirobot;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;
import serverrmirobot.interfaceRobot;

/**
 *
 * @author alcrdate
 */
public class RobotRmiRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            interfaceRobot robot = (interfaceRobot) Naming.lookup("rmi://localhost:1099/rbt");
           
            System.out.println("le nouveau robot est de l'adress : " + robot.getAdress());
            boolean t = robot.intialiseRobot();
            while(true){
                 System.out.println("initialize le robot ...");
                if(t){
                System.out.println("le robot " + robot.getAdress() + " est intialise");
                System.out.println("battery level : " + robot.batteryLevel() + "%");
                //se charger     
                if(robot.batteryLevel() < 30.0){
                    robot.goToCharge();
                }else{
                    System.out.println("le robot est plein ");
                }
                boolean bo = robot.iamFree();
                if(bo){
                    System.out.println("le robot est free");
                }
                while(true){
                    String  s  = robot.getProgramme();
                    if(s.equals("no class")){
                        System.out.println("waiting for a task ....");
                    }else{
                        robot.iambusy();
                        System.out.println("la classe target est : " +  s + "est en cours");
                    }

                }
                    
                }else{
                    System.out.println("Le robot n'est pas intialise");
                }
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
