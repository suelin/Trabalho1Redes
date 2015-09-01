/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_UDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Soundbank;
import trabredes1.Contato;

/**
 *
 * @author Suelin
 */
public class Receive_UDP extends Thread {

    private int PORTA = 8080;

    public Receive_UDP(int PORTA) {
        super();
        this.PORTA = PORTA;
    }

    public void run() {
        
        ByteArrayInputStream in;
        ObjectInputStream is;        

        try {
            DatagramSocket socket = new DatagramSocket(PORTA);
            DatagramPacket pacote = new DatagramPacket(new byte[1024], 1024);
            while (true) {
                socket.receive(pacote);
                /*System.out.println("Pacote de: " + pacote.getAddress().getHostName()
                        + ": " + pacote.getPort()
                        + ": " + (new String(pacote.getData())));*/
                in = new ByteArrayInputStream(pacote.getData());
                is = new ObjectInputStream(in);
                Object ob = is.readObject();
                Contato contato = (Contato) ob;
                System.out.println(contato.getNome() + " " + contato.getPorta() + " " + contato.getIP());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Receive_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
