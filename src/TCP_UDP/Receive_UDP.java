package TCP_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabredes1.Contato;
import trabredes1.ListaContatos;

public class Receive_UDP extends Thread {

    private int PORTA;
    private String host;
    private boolean exec = true;
    private ListaContatos listaContatos;

    public Receive_UDP(String host, int PORTA, ListaContatos listaContatos) {
        super();
        this.PORTA = PORTA;
        this.host = host;
        this.listaContatos=listaContatos;
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
                verifica(new String(pack.getData()),new String(pack.getAddress().toString()));
            }
            s.leaveGroup(InetAddress.getByName(host));
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Receive_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Receive_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finaliza(){
        exec=false;
    }
    
    private void verifica(String msg,String ip) throws InterruptedException{
        String tipo=""+msg.charAt(0)+msg.charAt(1)+msg.charAt(2)+msg.charAt(3);
        if(tipo.equals("USER")){
            System.out.println("USER");
            String[] s = msg.split(" ");
            listaContatos.Add_Contato(new Contato(s[1], Integer.parseInt(""+s[2].charAt(0)+s[2].charAt(1)+s[2].charAt(2)+s[2].charAt(3)), ip));
        }
        if(tipo.equals("EXIT")){
            String[] s = msg.split(" ");
            System.out.println("EXIT");
            listaContatos.Rem_Contato(new Contato(s[1], Integer.parseInt(""+s[2].charAt(0)+s[2].charAt(1)+s[2].charAt(2)+s[2].charAt(3)), ip));
        }
    }
}
