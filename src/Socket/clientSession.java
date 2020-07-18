/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 *
 * @author ricky
 */
public class clientSession extends Thread {
    
    BufferedReader request;
    PrintWriter response;
    Client cliente; 
    public clientSession(BufferedReader request, PrintWriter response, Client cliente) {
        this.request = request;
        this.response = response;
        this.cliente = cliente;
        this.start();
    }

    @Override
    public void run() {
        
        try {
            String eventos;
            while(true){
                eventos = request.readLine();
                System.out.println(eventos);
                switch(eventos){
                    case "init":
                        init();
                        break;
                    case "close":
                        close();
                        break;
                    default :
                        onMesagge(eventos);
                }
            }
        } catch (Exception ex) {
            onError(ex);
        }
    }
    
    private void onError(Exception ex){
        System.out.println("Error:"+ex.getLocalizedMessage());
    }
    
    private void init(){
        System.out.println("me dijo init diske");
    }
    
    private void close(){
        System.out.println("me dijo close dike");
    }

    private void onMesagge(String msg) {
        cliente.view.pintarRecivido(msg);
    }

}
