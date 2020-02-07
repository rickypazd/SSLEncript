/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricky
 */
public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip = "192.168.1.103";
    private int port = 6868;
    
    public Client(String ip){
        try {
            System.out.print("Conectando con el server.");
            this.ip=ip;
            clientSocket = new Socket(ip, port);
            System.out.print(".");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.print(".");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.print(".");
        } catch (Exception ex) {
            System.out.print("Error en la conexion...");
        }
    }
    
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
 
    public void close() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
