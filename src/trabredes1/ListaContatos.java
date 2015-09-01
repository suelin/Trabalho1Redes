/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabredes1;

import java.util.ArrayList;

/**
 *
 * @author Suelin
 */
public class ListaContatos{
    ArrayList contatos = new ArrayList<Contato>();
   
    public void Add_Contato(Contato contato){
        if(contatos.contains(contato)){
            return;//irá atualizar o time
        } else{
            contatos.add(contato);
        }
    }
    
    public void Rem_Contato(Contato contato){
        contatos.remove(contato); 
    }

    public ArrayList getContatos() {
        return contatos;
    }
}
