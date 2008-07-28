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
public class CartelaUI extends JComponent {
    
    public static int tamCelX=60;
    public static int tamCelY=27;
    
    private StatusDaLinha[][] statusDasLinhas;
    
    private int[][] valoresDasLinhas;
    
    public CartelaUI(int posX,int posY) {
        super();
        this.setLocation(posX, posY);
        this.setBounds(posX,posY, 5*tamCelX, 20*tamCelY);
        
	this.statusDasLinhas=new StatusDaLinha[4][19];
	this.valoresDasLinhas=new int[4][19];
	
	for (int i=0;i<4;i++) {
	    for (int j=0;j<19;j++) {
		statusDasLinhas[i][j]=StatusDaLinha.livre;
	    }
	}
	
    }
    
    @Override
    public void paintComponent(Graphics g) {
	
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
		    g.fill3DRect(i*tamCelX, j*tamCelY, tamCelX, tamCelY, raised);
		}
		else {
		    if (j==19 & i==3) {
			g.fill3DRect(i*tamCelX, j*tamCelY, tamCelX*2, tamCelY, raised);
		    }
		}
		
		//escreve labels
		if (i==0 & j > 0 & j < 19) {
		    int lblPosX = i*tamCelX+(tamCelX/2) - labelsY[j-1].length()*5;
		    g.setColor(Color.black);
		    g.drawChars(labelsY[j-1].toCharArray(), 0,labelsY[j-1].length(),lblPosX, j*tamCelY+tamCelY-10);
		}
		if (i > 0 & j == 0 ) {
		    int lblPosY = (tamCelY/2) + 5;
		    g.setColor(Color.black);
		    g.drawChars(labelsX[i-1].toCharArray(), 0,labelsX[i-1].length(),i*tamCelX+tamCelX/2-5, lblPosY );
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
