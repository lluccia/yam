/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package yam.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author leandro.c.de.luccia
 */
public class DadoUI extends JComponent {
    
    public static final int dimDado=100;
    public static final int espacoEntreDados=20;
    public static final float escalaDosDados=0.9f;
    
    private BufferedImage imgDados;
    
    private boolean[] marcados;
    
    private int[] valores;
    
    public DadoUI(int posX,int posY) {
	super();
        this.setLocation(posX, posY);
        this.setBounds(posX,posY,Math.round(5*dimDado*escalaDosDados+4*espacoEntreDados),Math.round(dimDado*escalaDosDados));
        
	this.marcados = new boolean[]{false,false,false,false,false};
	this.valores = new int[]{1,2,3,4,5};
	
	try {
	    imgDados = ImageIO.read(new File("images/dados.png"));
	} 
	catch (IOException e) {
	    
	}
	
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int posDestX, posDestY, posOrigX, posOrigY;
	for (int i=0;i<5;i++) {
            posDestX = Math.round(i*(dimDado*escalaDosDados+espacoEntreDados));
	    posDestY = 0;
	    
	    posOrigX = (valores[i]-1)*dimDado;
	    if (marcados[i]==true) {posOrigY=100;} else {posOrigY=0;}
	     
	    g.drawImage(imgDados, 
		    posDestX , posDestY, Math.round(posDestX+dimDado*escalaDosDados), Math.round(posDestY+dimDado*escalaDosDados),
		    posOrigX , posOrigY, posOrigX+dimDado, posOrigY+dimDado, 
		    null);
	
	
	}	
    }

    public void sincronizar(int[] val,boolean[] mar) {
       this.valores = val;
       this.marcados = mar;
   }

}
