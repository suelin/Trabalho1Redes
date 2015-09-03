package TCP_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receive_UDP extends Thread {

    private int PORTA;
    private String host;
    private boolean exec = true;

    public Receive_UDP(String host, int PORTA) {
        super();
        this.PORTA = PORTA;
        this.host = host;
    }

    @Override
    public void run() {
        try {
            MulticastSocket s = new MulticastSocket(PORTA);
            s.joinGroup(InetAddress.getByName(host));
            byte buf[] = new byte[1024];
            DatagramPacket pack = new DatagramPacket(buf, buf.length);
            while (exec) {
                s.receive(pack);
                System.out.println("Received data from: " + pack.getAddress().toString()
                        + ":" + pack.getPort() + " with length: "
                        + pack.getLength());
                System.out.write(pack.getData(), 0, pack.getLength());
                System.out.println();
            }
            s.leaveGroup(InetAddress.getByName(host));
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Receive_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finaliza(){
        exec=false;
    }
    
}
