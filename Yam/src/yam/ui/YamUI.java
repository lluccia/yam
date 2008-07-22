package yam.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class YamUI extends JFrame {

    private static final int windowWidth=585;
    private static final int windowHeigth=750;
       
    private DadoUI dadoUI;
    private CartelaUI cartelaUI;
    
    public YamUI() {
	super();
	setSize(windowWidth,windowHeigth);
	this.dadoUI=new DadoUI(30,50);
	this.cartelaUI=new CartelaUI(30,170);
	setVisible(true);
	setResizable(false);
	setLocationRelativeTo(null);
	addWindowListener(new WindowAdapter(){
	    @Override
	    public void windowClosing(WindowEvent we){
		System.exit(0);
	    }
	});
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	g.setColor(Color.getHSBColor(0.36f, 1.0f, 0.5f));
	g.fillRect(0,0,windowWidth,windowHeigth);
	
	dadoUI.desenhar(g);
	cartelaUI.desenhar(g);
    }
    
    
    static public void main(String[] args) {
	new YamUI();
    }

}