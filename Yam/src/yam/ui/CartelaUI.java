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
public class CartelaUI {
    
    private int posX,posY;
    
    
    public CartelaUI(int posX,int posY) {
	this.posX=posX;
	this.posY=posY;
	
    }
    
    public void desenhar(Graphics g) {
	int tamCelX=60;
	int tamCelY=27;
	boolean raised;
	String[] labelsX={
	    "^",
	    "v",
	    "D",
	    "S",
	};
	String[] labelsY={
	    "1",
	    "2",
	    "3",
	    "4",
	    "5",
	    "6",
	    "1ºtot",
	    "bônus",
	    "2ºtot",
	    "Q",
	    "F",
	    "S-",
	    "S+",
	    "mín",
	    "máx",
	    "YAM",
	    "3ºtot",
	    "2º+3º"
	};
	g.setFont(new Font("Courier", Font.BOLD, 16));
	
	for (int i=0;i<5;i++){
	    for (int j=0;j<20;j++){
		if (i==0 & j>0 | j==0 & i>0) {
		    g.setColor(Color.lightGray);
		    raised=true;
		    
		}
		else {
		    if (i>0 & (j>=7 & j<=9 | j>=17 & j<19)) {
			g.setColor(Color.lightGray);
			raised=false;
		    }
		    else{
			g.setColor(Color.white);
			raised=true;
		    }
		}
		if (!(i==0 & j==0) & j<19) {
		    g.fill3DRect(posX+i*tamCelX, posY+j*tamCelY, tamCelX, tamCelY, raised);
		}
		else {
		    if (j==19 & i==3) {
			g.fill3DRect(posX+i*tamCelX, posY+j*tamCelY, tamCelX*2, tamCelY, raised);
		    }
		}
		if (i==0 & j > 0 & j < 19) {
		    int lblPosX = posX+i*tamCelX+(tamCelX/2) - labelsY[j-1].length()*5;
		    g.setColor(Color.black);
		    g.drawChars(labelsY[j-1].toCharArray(), 0,labelsY[j-1].length(),lblPosX, posY+j*tamCelY+tamCelY-10);
		}   
	    }
	}
	
	
	
    }	 

}
