package trabredes1;

import java.io.Serializable;

public class Contato implements Serializable{
    String nome;
    int porta;
    String IP;
    
    public Contato(String nome, int porta, String IP){
        this.nome = nome;
        this.porta = porta;
        this.IP = IP;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    
}
