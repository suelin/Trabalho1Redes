package trabredes1;

import java.io.Serializable;

public class Contato implements Serializable{
    String nome;
    int porta;
    String IP;
    int count;
    
    public Contato(String nome, int porta, String IP){
        this.nome = nome;
        this.porta = porta;
        this.IP = IP;
        this.count=0;
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
    
    public int getCount(){
        return this.count;
    }
    public void setCount(){
        this.count=0;
    }
    
    public void atualizaCount(){
        count++;
    }
}
