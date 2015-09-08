/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_UDP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Suelin
 */
public class TCP extends Thread{
    private static int PORTA = 12345;
    private static String IP = "localhost";
    
    public static void main(String[] args){
        try{
            Socket socket = new Socket(IP, PORTA);
            byte[] buffer = "PING".getBytes();
            OutputStream out = socket.getOutputStream();//receber dados
            out.write(buffer);
            
            socket.close();                      
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    
}
