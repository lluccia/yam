package yam.engine;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author leandro
 */
public class Main {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Jogada jogada=new Jogada();

        jogada.jogarDados();
        //System.out.println(jogada.serializarValores());
        System.exit(0);
    }

}