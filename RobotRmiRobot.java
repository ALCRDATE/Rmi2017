/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotrmirobot;

import com.mysql.jdbc.Connection;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;
import serverrmirobot.Connexion;
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
        int progression = 0;
        try {    
            interfaceRobot robot = (interfaceRobot) Naming.lookup("rmi://localhost:1099/rbt");
            System.out.println("Bonjour to Robot_2017\n=========================");
            if(!robot.robotExiste()){
                //Le cas de la premiere utilistation du robot : 
                boolean t = robot.intialiseRobot();
                System.out.println("le nouveau robot est de l'adress : " + robot.getAdress());
                while(true){
                    if(t){
                        System.out.println("le robot " + robot.getAdress() + " est intialise");
                        System.out.println("battery level : " + robot.batteryLevel() + "%");
                            if(robot.batteryLevel() < 30.0){
                                robot.goToCharge();
                            }else{
                                System.out.println("le robot est plein ");
                            }
                            /////////////////////////////////////////////
                            boolean bo = robot.iamFree();
                            if (bo) {
                                while(true){
                                int  s  = robot.getProgramme();
                                if(s == -1){
                                    System.out.println("waiting for a task ....");
                                }else{
                                    robot.iambusy();
                                    System.out.println("la classe target is number : " +  s + " est en cours");
                                }
                                }
                            }
                             
                    }else{
                        System.out.println("Le robot n'est pas intialise .....");
                    }
                }
            }else{
                //Le cas ou le robot existe deja : 
                System.out.println("Le robot deja existe ....");
                System.out.println("Le Robot : " +robot.getAdress());
                while(true){
                System.out.println("Batterie Level : " + robot.batteryLevel());
                Thread.sleep(1000);
                    if(robot.batteryLevel() < 30.0){
                        robot.goToCharge();
                        }else{
                        System.out.println("le robot est plein ");
                        }
                    boolean bo = robot.iamFree();
                            if (bo) {
                                int  s  = robot.getProgramme();
                                int task = robot.getTaskID();
                                while(true){
                                if(s == -1){
                                    System.out.println("waiting for a task ....");
                                }else{
                                    robot.changeState("enCours", task);
                                    //Le robot commence a depoussrier : 
                                    robot.iambusy();
                                    System.out.println("la classe target is number : " +  s + " est en cours");
                                    System.out.println("L'ID de la tache est : " + task);
                                    System.out.println("La progression de la tache est : " + robot.getProgress(task));
                                    int batterie;
                                    progression = robot.getProgress(task) + 3;
                                    batterie = (int) (robot.batteryLevel() - 1);
                                    robot.updateRobotBattery(batterie);
                                    robot.updateTaskProgrss(progression, task);
                                    Thread.sleep(500);
                                    System.out.println("Batterie = " + batterie);
                                    if(progression >= 99){
                                        robot.changeState("end", task);
                                        break;
                                    }
                                }
                                
                                }
                            }
                    }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
