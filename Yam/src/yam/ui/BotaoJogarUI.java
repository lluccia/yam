/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yam.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 *
 * @author leandro.c.de.luccia
 */
public class BotaoJogarUI extends JComponent {

    public static final int dimBotaoX=150;
    public static final int dimBotaoY=50;
    
    private BufferedImage imgBotao;
    
    private boolean habilitado;
    
    public BotaoJogarUI(int posX,int posY) {
	super();
        this.setLocation(posX, posY);
        this.setBounds(posX,posY,dimBotaoX,dimBotaoY);

	this.habilitado = true;

		
	try {
	    imgBotao = ImageIO.read(new File("images/btnJogar.png"));
	} 
	catch (IOException e) {
	    e.printStackTrace();
	}

    }
    
    @Override
    public void paint(Graphics g) {
	int posDestX, posDestY, posOrigX, posOrigY;
	posDestX=0;
        posDestY=0;
        posOrigX=0;
        if (habilitado) {posOrigY = 0;} else {posOrigY = 50;}

        g.drawImage(imgBotao, 
                posDestX , posDestY, posDestX+dimBotaoX, posDestY+dimBotaoY,
                posOrigX , posOrigY, posOrigX+dimBotaoX, posOrigY+dimBotaoY, 
                null);

    }
    
    public boolean isHabilitado() {
        return habilitado;
    }
            
    public void sincronizar(boolean hab) {
        this.habilitado = hab;
    }
}
