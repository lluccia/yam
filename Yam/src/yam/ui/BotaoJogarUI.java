/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yam.ui;

import java.awt.*;
import java.awt.event.*;
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
    
    private boolean apertado;
    
    ActionListener actionListener;
    
    String actionCommand;
    
    public BotaoJogarUI(int posX,int posY) {
	super();
        this.setBounds(posX,posY,dimBotaoX,dimBotaoY);

	this.habilitado = false;
	this.apertado = false;

	try {
	    imgBotao = ImageIO.read(new File("images/btnJogar.png"));
	} 
	catch (IOException e) {
	    e.printStackTrace();
	}
        
        enableEvents( AWTEvent.MOUSE_EVENT_MASK );
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
    
    @Override
    public void processEvent( AWTEvent e ) {
        if (habilitado & e.getID() == MouseEvent.MOUSE_RELEASED){
            fireEvent();
        }
        super.processEvent(e);
    }

    public void addActionListener(ActionListener l) {
        actionListener = AWTEventMulticaster.add(actionListener, l);
    }

    public void removeActionListener(ActionListener l) {
        actionListener = AWTEventMulticaster.remove(actionListener, l);
    }

    private void fireEvent() {
        if (actionListener != null) {
            ActionEvent event = new ActionEvent( 
                    this, ActionEvent.ACTION_PERFORMED, actionCommand );
            actionListener.actionPerformed( event );
        }
    }
}
