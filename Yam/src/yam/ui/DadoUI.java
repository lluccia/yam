/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yam.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author leandro.c.de.luccia
 */
public class DadoUI {
    
    private static final int dimDado=100;
    private static final int espacoEntreDados=20;
    private static final float escalaDosDados=0.9f;
    
    private BufferedImage imgDados;
    
    private boolean[] marcados;
    
    private int[] valores;
    
    private int posX,posY;
    
    public DadoUI(int posX,int posY) {
	this.posX=posX;
	this.posY=posY;
	this.marcados = new boolean[]{false,true,true,false,false};
	this.valores = new int[]{3,3,3,4,5};
	
	try {
	    imgDados = ImageIO.read(new File("images/dados.png"));
	} 
	catch (IOException e) {
	    
	}
    }
    
    public void desenhar(Graphics g) {
	int posDestX, posDestY, posOrigX, posOrigY;
	for (int i=0;i<5;i++) {
	    posDestX = Math.round(i*(dimDado*escalaDosDados+espacoEntreDados) + posX);
	    posDestY = posY;
	    posOrigX = (valores[i]-1)*dimDado;
	    if (marcados[i]==true) {posOrigY=100;} else {posOrigY=0;}
	    
	    g.setColor(Color.lightGray);
	    g.fill3DRect(posDestX-3, posDestY-3, Math.round(dimDado*escalaDosDados+6), Math.round(dimDado*escalaDosDados+6), true);
	    
	    g.drawImage(imgDados, 
		    posDestX , posDestY, Math.round(posDestX+dimDado*escalaDosDados), Math.round(posDestY+dimDado*escalaDosDados),
		    posOrigX , posOrigY, posOrigX+dimDado, posOrigY+dimDado, 
		    null);
	
	
	}	
    }	 

}
