/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_UDP;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Daniel
 */
public class Receive_TCP {
    public static int PORTA = 12345;
    
    public static void main(String[] args){
        InputStream in;
        try {
            ServerSocket server = new ServerSocket(PORTA);
			while(true){
				System.out.println("Aguardando conexÃ£o...");
				Socket socket = server.accept();//bloqueado atÃ© alguÃ©m inciar uma conexÃ£o com ele
				//quando alguÃ©m conectar com ele, ele vai atribuir o socket
				System.out.println("Conectando com " + socket.getInetAddress().getHostAddress());
				
				byte[] buffer = new byte[1024];
				in = socket.getInputStream();//enviar dados
				int bytesLidos = in.read(buffer);
				
				System.out.println(bytesLidos + ": " + new String(buffer));
				
				socket.close();
			}
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
