package yam.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import yam.engine.*;

public class YamUI extends JFrame implements MouseListener {

    private static final int windowWidth = 585;
    private static final int windowHeigth = 750;
    private DadoUI dadoUI;
    private CartelaUI cartelaUI;
    private BotaoJogarUI botaoJogarUI;
    
    //debug start
    private Jogo jogo;
    //debug end

    public YamUI() {
	super();
	setSize(windowWidth, windowHeigth);
	this.dadoUI = new DadoUI(30, 50);
	this.cartelaUI = new CartelaUI(30, 170);
	this.botaoJogarUI = new BotaoJogarUI(400, 200);
        
	setVisible(true);
	setResizable(false);
	setLocationRelativeTo(null);
	addWindowListener(new WindowAdapter() {
	
	    @Override
	    public void windowClosing(WindowEvent we) {
		System.exit(0);
	    }
	});

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
	botaoJogarUI.desenhar(gBuffer);
        
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
    
    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	int clickX = e.getX();
	int clickY = e.getY();
	int dadoX = dadoUI.getPosX();
	int dadoY = dadoUI.getPosY();
        boolean sincronizar=false;

        //verifica clique no dado
	if ( clickY >= dadoY & clickY <= Math.round(DadoUI.dimDado*DadoUI.escalaDosDados + dadoY)) {
	    for (int i=0; i<5;i++) {
		if ( clickX >= Math.round(i*(DadoUI.dimDado*DadoUI.escalaDosDados + DadoUI.espacoEntreDados) + dadoX ) &
		    clickX <= Math.round((i+1)*DadoUI.dimDado*DadoUI.escalaDosDados + i*DadoUI.espacoEntreDados + dadoX )) {
		    jogo.marcarDado(i);
		    
                    sincronizar=true;
                    System.out.println(botaoJogarUI.isHabilitado());
                    break;
		}
	    }
	}
        else {
            //verifica clique no botão
            if ( clickX >= botaoJogarUI.getPosX() & clickX <= botaoJogarUI.getPosX() + BotaoJogarUI.dimBotaoX &
                  clickY >= botaoJogarUI.getPosY() & clickY <= botaoJogarUI.getPosY() + BotaoJogarUI.dimBotaoY  ) {
                jogo.jogarDados();
                
                sincronizar=true;
            }
        }    
            
        if (sincronizar) {sincronizar();}
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
        botaoJogarUI.sincronizar(jogo.getPodeJogarDados());
	this.desenhar();
    }
}