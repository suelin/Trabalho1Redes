package TCP_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabredes1.Contato;

public class UDP extends Thread {

    Contato contato;
    String host;//IP do grupo
    int PORTA;
    boolean exec = true;//indica se a thread deve continuar executando ou não

    public UDP(String nome, int porta, String IP, String grupo, int PortaGrupo) {
        super(nome);
        contato = new Contato(nome, porta, IP);
        this.host = grupo;
        this.PORTA = PortaGrupo;
    }

    @Override
    public void run() {
        String msg = ("USER " + contato.getNome() + " " + contato.getPorta());
        try {
            MulticastSocket s = new MulticastSocket();
            byte buf[] = msg.getBytes();
            while (exec) {//primeira mensagem e mensagens de atualização
                DatagramPacket pacote = new DatagramPacket(buf, buf.length, InetAddress.getByName(host), PORTA);
                s.send(pacote);
                sleep(5000);
            }
            msg = ("EXIT " + contato.getNome() + " " + contato.getPorta());
            buf = msg.getBytes();//mensagem de saida do grupo
            DatagramPacket pacote = new DatagramPacket(buf, buf.length, InetAddress.getByName(host), PORTA);
            s.send(pacote);
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finaliza(){
        exec=false;
    }
}
