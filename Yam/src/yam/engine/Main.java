package yam.engine;

import java.awt.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author leandro
 */
public class Main {

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        String[] nomes={"Leandro","Sassá"};
	
	Jogada jg = new Jogada();
	jg.jogarDados();
	System.out.println(jg.getJogada());
	
	
	
	GraphicsEnvironment gfxEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	GraphicsDevice[] screenDevList = gfxEnv.getScreenDevices();
	
	GraphicsDevice defaultDevice = gfxEnv.getDefaultScreenDevice();
	
	
	
        System.exit(0);
    }

}