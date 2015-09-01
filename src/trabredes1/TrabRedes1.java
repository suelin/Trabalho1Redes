/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabredes1;

import TCP_UDP.Receive_UDP;
import TCP_UDP.UDP;

/**
 *
 * @author Suelin
 */
public class TrabRedes1 {

    
    public static void main(String[] args) {
        Receive_UDP rec_udp = new Receive_UDP(9112);
        rec_udp.start();
        UDP obj_udp = new UDP("Suelin", 9112, "localhost");
        obj_udp.start();
        UDP obj_udp2 = new UDP("Daniel", 9113, "localhost");
        obj_udp2.start();
    }
}
