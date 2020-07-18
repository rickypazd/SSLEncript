/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import Server.Console;
import Server.Session;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import sslencript.SSLEncript;

/**
 *
 * @author ricardopazdemiquel
 */
public class server {

    private static server INSTANCE;
    private ServerSocket serverSocket;
    private Socket socket;
    private static JTextArea jtConsolea;
    private static JList jlSessioness;
    private static int PORT = 6868;
    private static final DefaultListModel model = new DefaultListModel();

    public static server getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new server();
        }
        return INSTANCE;
    }

    private server() {
        try {
            serverSocket = new ServerSocket(PORT);
            hilo();
            Console.print("Running on port: " + PORT);
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    final static ArrayList<Thread> hilos = new ArrayList<>();
    final static ArrayList<Session> Sessiones = new ArrayList<>();

    private void hilo() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        String idSession = new Date().getTime()+""+socket.getRemoteSocketAddress().toString().hashCode();
                        Session session = new Session(idSession, socket);
                        Sessiones.add(session);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        hilos.add(t);
    }

    public void notificar(String mensaje){
        for (Session session : Sessiones) {
            session.send(mensaje);
        }
    }
}
