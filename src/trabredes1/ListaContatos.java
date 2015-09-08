package trabredes1;

import Janelas.Menu;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaContatos extends Thread {

    ArrayList<Contato> contatos = new ArrayList<Contato>();
    boolean exec = true;
    private Semaphore semaforo=new Semaphore(1);
    private Menu m;
    public ListaContatos(Menu m){
        this.m=m;
    }
    
    public void run() {
        while (exec) {
            try {
                semaforo.acquire();
                System.out.println("VERIFICANDO");
                for (Contato c : contatos) {
                    
                    if (c.getCount() > 2) {
                        contatos.remove(c);
                    } else {
                        c.atualizaCount();
                    }                    
                }
                m.atualizaLista(contatos);
                semaforo.release();
                sleep(5000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(ListaContatos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (java.util.ConcurrentModificationException ex){
                System.out.println("BUGO");
                semaforo.release();
            }
        }
    }

    public void Add_Contato(Contato contato) throws InterruptedException {
        semaforo.acquire();
        for (Contato c : contatos) {
            if (c.getNome().equals(contato.getNome()) && c.getPorta() == contato.getPorta() && c.getIP().equals(contato.getIP())) {
                c.setCount();
                semaforo.release();
                return;
                
            }
        }
        contatos.add(contato);
        semaforo.release();
    }

    public void Rem_Contato(Contato contato) throws InterruptedException {
        semaforo.acquire();
        for (Contato c : contatos) {
            if (c.getNome().equals(contato.getNome()) && c.getPorta() == contato.getPorta() && c.getIP().equals(contato.getIP())) {
                contatos.remove(c);
                semaforo.release();
                return;
            }
        }
        semaforo.release();
    }

    public void finaliza() {
        exec = false;
    }

    public ArrayList getContatos() throws InterruptedException {
        semaforo.acquire();
        ArrayList<Contato> contat = contatos;
        semaforo.release();
        return contat;
    }
   
}
