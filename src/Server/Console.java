/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import javax.swing.JTextArea;

/**
 *
 * @author ricardopazdemiquel
 */
public class Console {
     private static JTextArea jtConsolea;

    public static void setJtConsolea(JTextArea jtConsolea) {
        Console.jtConsolea = jtConsolea;
    }
     
     
     public static void print(String mensaje){
              jtConsolea.setText(jtConsolea.getText() + "\n" + mensaje);
     }
}
