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
public class CartelaUI extends JPanel{
    
    private int posX,posY;
    
    private StatusDaLinha[][] statusDasLinhas;
    
    private int[][] valoresDasLinhas;
    
    public CartelaUI(int posX,int posY) {
	this.posX=posX;
	this.posY=posY;
	this.statusDasLinhas=new StatusDaLinha[4][19];
	this.valoresDasLinhas=new int[4][19];
	
	for (int i=0;i<4;i++) {
	    for (int j=0;j<19;j++) {
		statusDasLinhas[i][j]=StatusDaLinha.livre;
	    }
	}
	
    }
    
    public void desenhar(Graphics g) {
	int tamCelX=60;
	int tamCelY=27;
	boolean raised;
	String[] labelsX={ "▼", "▲", "D", "S" };
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
	g.setFont(new Font("Courier New", Font.BOLD, 16));
	
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
			if (i!=0 & j!=0 & j<19){
			    switch (statusDasLinhas[i-1][j-1]) {
				case livre:
				    g.setColor(Color.white);    
				    break;
				case marcavel:
				    g.setColor(Color.green);    
				    break;
				case riscavel:
				    g.setColor(Color.red);    
				    break;
			    }
			}
			raised=true;
		    }
		}
		if (!(i==0 & j==0) & j<19) {
		    g.fill3DRect(posX+i*tamCelX, posY+j*tamCelY, tamCelX, tamCelY, raised);
//		    g.fillRect(posX+i*tamCelX, posY+j*tamCelY, tamCelX, tamCelY);
		}
		else {
		    if (j==19 & i==3) {
			g.fill3DRect(posX+i*tamCelX, posY+j*tamCelY, tamCelX*2, tamCelY, raised);
//			g.fillRect(posX+i*tamCelX, posY+j*tamCelY, tamCelX*2, tamCelY);
		    }
		}
		
		//escreve labels
		if (i==0 & j > 0 & j < 19) {
		    int lblPosX = posX+i*tamCelX+(tamCelX/2) - labelsY[j-1].length()*5;
		    g.setColor(Color.black);
		    g.drawChars(labelsY[j-1].toCharArray(), 0,labelsY[j-1].length(),lblPosX, posY+j*tamCelY+tamCelY-10);
		}
		if (i > 0 & j == 0 ) {
		    int lblPosY = posY+(tamCelY/2) + 5;
		    g.setColor(Color.black);
		    g.drawChars(labelsX[i-1].toCharArray(), 0,labelsX[i-1].length(),posX+i*tamCelX+tamCelX/2-5, lblPosY );
		}
		
		//escreve pontos
		// TODO
		
	    }
	}
    }	 
    
    public void sincronizar(StatusDaLinha[][] status, int[][] pontos){
	this.statusDasLinhas = status;
	this.valoresDasLinhas = pontos;
    }
    

}
