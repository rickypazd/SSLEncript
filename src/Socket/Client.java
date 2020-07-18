/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import VISTA.jfCliente;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author ricky
 */
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ips = "ruddypazd.com";
    private int port = 5000;
    jfCliente view;
    
    public Client(String ip, jfCliente view){
        try {
            this.view = view; 
            System.out.print("Conectando con el server");
            //this.ip=ip;
            clientSocket = new Socket(ips, port);
            System.out.print(".");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.print(".");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.print(".");
            
            clientSession session = new clientSession(in, out, this);
        } catch (Exception ex) {
            System.out.print("Error en la conexion...");
        }
    }
    
    
    
    public void sendMessage(String msg) throws IOException {
        out.println(msg);
        out.flush();
        System.out.println(msg);
        //recivedMessage();
    }
    
    public void recivedMessage(){
        try {
            view.pintarRecivido(in.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void close() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
