package trabredes1;

import Janelas.Menu;
import TCP_UDP.Receive_UDP;
import TCP_UDP.UDP;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Scanner;

public class TrabRedes1 {

    public static void main(String[] args) throws InterruptedException {
//        ListaContatos listaContatos = new ListaContatos();
//        listaContatos.start();
//        Receive_UDP rec_udp = new Receive_UDP("239.255.255.255",9123,listaContatos);
//        rec_udp.start();
//        Scanner in = new Scanner(System.in);
//        int porta=in.nextInt();
//        UDP obj_udp = new UDP("Suelin", porta, "localhost","239.255.255.255",9123);
//        obj_udp.start();
//        sleep(30000);
//        obj_udp.finaliza();
//        rec_udp.finaliza();
//        sleep(5000);
//        
//        ArrayList<Contato> lista = listaContatos.getContatos();
//        for(Contato c: lista){
//            System.out.println(c.getNome());
//        }
//        sleep(5000);
//        listaContatos.finaliza();
//        listaContatos.interrupt();
//        lista = listaContatos.getContatos();
//        for(Contato c: lista){
//            System.out.println(c.getNome());
//        }
//        System.out.println("fim");
        Menu jPrincipal = new Menu();
        jPrincipal.setVisible(true);
        ListaContatos listaContatos = new ListaContatos(jPrincipal);
        listaContatos.start();
        Receive_UDP rec_udp = new Receive_UDP("239.255.255.255",9123,listaContatos);
        rec_udp.start();
        Scanner in = new Scanner(System.in);
        int porta=in.nextInt();
        jPrincipal.setTitle(":) "+porta+" (:");
        UDP obj_udp = new UDP(""+porta, porta, "localhost","239.255.255.255",9123);
        obj_udp.start();
    }
}
