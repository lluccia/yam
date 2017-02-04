/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yam.ui;

import java.awt.*;
import javax.swing.*;
import yam.engine.*;

/**
 *
 * @author leandro.c.de.luccia
 */
public class JogadoresUI extends JComponent {

    public static final int dimJogadoresX=200;
    public static final int dimJogadoresY=180;
    
    private String[][] jogadores;
    private int jogadorAtual;
   
    public JogadoresUI(int posX,int posY) {
	super();
        this.setBounds(posX,posY,dimJogadoresX,dimJogadoresY);
        jogadores = new String[1][2];
        jogadores[0][0] = "N/A";
        jogadores[0][1] = "0";
    }
    
    @Override
    public void paint(Graphics g) {
        int offset=25;
               
        g.setFont(new Font("Courier New", Font.BOLD, 16));
        
        for (int i=0; i < jogadores.length; i++) {
            g.setColor(Color.yellow);
            if (i==jogadorAtual) {g.setColor(Color.white);}
            
            g.drawChars(jogadores[i][0].toCharArray(), 0, jogadores[i][0].length(), 0, 10 + i*offset);    
            g.drawChars(jogadores[i][1].toCharArray(), 0, jogadores[i][1].length(), dimJogadoresY - jogadores[i][1].length()*10, 10 + i*offset);    
        }
        
    }
    
    public void sincronizar(Jogo j) {
        jogadores = j.getJogadoresComTotalDePontos();
        jogadorAtual = j.getIntJogadorAtual();
    }
}
