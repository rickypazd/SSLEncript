/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricardopazdemiquel
 */
public class Session extends Thread {

    private String id;
    private Socket socket;
    private PrintWriter outpw = null;

    public Session(String id, Socket socket) {

        this.id = id;
        this.socket = socket;
        try {
            outpw = new PrintWriter(socket.getOutputStream(), true);
            outpw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.start();

    }
////

    public void send(String mensaje) {
        outpw.print(mensaje);
        outpw.flush();
    }

    public void setId(String id) {
        this.id = id;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Console.print("SESSION INICIADA");
        Console.print("ID: " + id);
        InputStream inp = null;
        BufferedReader brinp = null;
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                } else {
                    Console.print("MENSAJE: " + line);

                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
