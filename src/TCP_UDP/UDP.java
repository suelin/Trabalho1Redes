/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_UDP;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabredes1.Contato;

/**
 *
 * @author Suelin
 */
public class UDP extends Thread {

    Contato contato;
    String host = "localhost";//IP de destino
    int PORTA = 9112;

    public UDP(String nome, int porta, String IP) {
        super(nome);
        contato = new Contato(nome, porta, IP);
    }

    public void run() {
        //passar objeto contato para byte
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(out);
            Object ob = new Object();
            ob = contato;
            os.writeObject(ob);
        } catch (IOException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {

                DatagramSocket socket = new DatagramSocket();
                byte[] buffer = out.toByteArray();
                InetSocketAddress address = new InetSocketAddress(host, PORTA);

                //opcional para facilitar - evita ter que informar o endereÃ§o
                //em cada pacote
                //socket.connect(address);

                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, address);
                socket.send(pacote);
                socket.close();
                sleep(15000);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
