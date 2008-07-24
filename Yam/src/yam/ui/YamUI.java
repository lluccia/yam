package yam.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import yam.engine.*;

public class YamUI extends JFrame implements KeyListener,MouseListener {

    private static final int windowWidth = 585;
    private static final int windowHeigth = 750;
    private DadoUI dadoUI;
    private CartelaUI cartelaUI;
    
    
    //debug start
    private Jogo jogo;
    //debug end

    public YamUI() {
	super();
	setSize(windowWidth, windowHeigth);
	this.dadoUI = new DadoUI(30, 50);
	this.cartelaUI = new CartelaUI(30, 170);	
	
	setVisible(true);
	setResizable(false);
	setLocationRelativeTo(null);
	addWindowListener(new WindowAdapter() {
	
	    @Override
	    public void windowClosing(WindowEvent we) {
		System.exit(0);
	    }
	});
	addKeyListener(this);
	addMouseListener(this);
	
	this.jogo = new Jogo(new String[]{"teste"});
	
	this.createBufferStrategy(2);
	this.desenhar();
    }

    public void desenhar() {
	BufferStrategy bf = this.getBufferStrategy();
	Graphics gBuffer = bf.getDrawGraphics();
	super.paint(gBuffer);
	gBuffer.setColor(Color.getHSBColor(0.36f, 1.0f, 0.5f));
	gBuffer.fillRect(0, 0, windowWidth, windowHeigth);

	dadoUI.desenhar(gBuffer);
	cartelaUI.desenhar(gBuffer);
	
	gBuffer.dispose();
	
	bf.show();
    }
    
//    @Override
//    public void paint(Graphics g) {
//	desenhar();
//    }

    static public void main(String[] args) {
	new YamUI();
    }

    public void keyTyped(KeyEvent e) {
	
    }

    public void keyPressed(KeyEvent e) {
	switch (e.getKeyCode()) {
	    case KeyEvent.VK_SPACE:
		jogo.jogarDados();
		break;
	    case KeyEvent.VK_1:
		jogo.marcarDado(0);
		break;
	    case KeyEvent.VK_2:
		jogo.marcarDado(1);
		break;
	    case KeyEvent.VK_3:
		jogo.marcarDado(2);
		break;
	    case KeyEvent.VK_4:
		jogo.marcarDado(3);
		break;
	    case KeyEvent.VK_5:
		jogo.marcarDado(4);
		break;
	}
	sincronizar();
    }

    public void keyReleased(KeyEvent e) {
	
    }
    
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	int clickX = e.getX();
	int clickY = e.getY();
	int dadoX = dadoUI.getPosX();
	int dadoY = dadoUI.getPosY();
	
	
	System.out.println(e.getPoint().toString());
	if ( clickY > dadoY & clickY < Math.round(DadoUI.dimDado*DadoUI.escalaDosDados + dadoY)) {
	    for (int i=0; i<5;i++) {
		if ( clickX >= Math.round(i*(DadoUI.dimDado*DadoUI.escalaDosDados + DadoUI.espacoEntreDados) + dadoX ) &
		    clickX <= Math.round((i+1)*DadoUI.dimDado*DadoUI.escalaDosDados + i*DadoUI.espacoEntreDados + dadoX )) {
		    jogo.marcarDado(i);
		    sincronizar();
		    return;
		}
	    }
	}
    }

    public void mouseReleased(MouseEvent e) {
	
    }

    public void mouseEntered(MouseEvent e) {
	
    }

    public void mouseExited(MouseEvent e) {
	
    }
    
    public void sincronizar() {
	dadoUI.sincronizar(jogo.getJogada().getValoresDados(), jogo.getJogada().getMarcadosDados());
	cartelaUI.sincronizar(jogo.getJogadorAtual().getCartela().getArrStatus(), 
		jogo.getJogadorAtual().getCartela().getArrPontos());
	this.desenhar();
    }
}