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
public class BotaoJogarUI {
    
    public static final int dimBotaoX=150;
    public static final int dimBotaoY=50;
    
    private BufferedImage imgBotao;
    
    private boolean habilitado;

    public boolean isHabilitado() {
        return habilitado;
    }
    
    private int posX,posY;

    public int getPosX() {
	return posX;
    }

    public int getPosY() {
	return posY;
    }
    
    public BotaoJogarUI(int posX,int posY) {
	super();
	this.posX=posX;
	this.posY=posY;
	this.habilitado = true;
		
	try {
	    imgBotao = ImageIO.read(new File("images/btnJogar.png"));
	} 
	catch (IOException e) {
	    e.printStackTrace();
	}
	
    }
    
    public void desenhar(Graphics g) {
	int posDestX, posDestY, posOrigX, posOrigY;
	posDestX=posX;
        posDestY=posY;
        posOrigX=0;
        if (habilitado) {posOrigY = 0;} else {posOrigY = 50;}

        g.drawImage(imgBotao, 
                posDestX , posDestY, posDestX+dimBotaoX, posDestY+dimBotaoY,
                posOrigX , posOrigY, posOrigX+dimBotaoX, posOrigY+dimBotaoY, 
                null);

    }	 

   public void sincronizar(boolean hab) {
       this.habilitado = hab;
   }

    
}
