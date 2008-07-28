package yam.ui;

import java.awt.*;
import java.awt.event.*;
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
        
        //preparação do engine
        this.jogo = new Jogo(new String[]{"teste"});
        
        //criação das classes
        this.dadoUI = new DadoUI(20, 20);
	this.cartelaUI = new CartelaUI(30, 150);
	this.botaoJogarUI = new BotaoJogarUI(390, 200);
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(dadoUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(dadoUI.getLocation(),dadoUI.getSize()));
        getContentPane().add(cartelaUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(cartelaUI.getLocation(),cartelaUI.getSize()));
        getContentPane().add(botaoJogarUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(botaoJogarUI.getLocation(),botaoJogarUI.getSize()));

        
        //monta estrutura do menu
        JMenuBar menuBarUI;
        JMenu menuJogoUI;
        JMenuItem menuJogoNovoUI;
        menuBarUI = new JMenuBar();
        menuJogoUI = new JMenu("Jogo");
        menuJogoNovoUI = new JMenuItem("Novo");
        menuJogoUI.add(menuJogoNovoUI);
        menuBarUI.add(menuJogoUI);
        
        this.setJMenuBar(menuBarUI);
        
        this.getContentPane().setBackground(Color.getHSBColor(0.36f, 1.0f, 0.5f));

	setVisible(true);
	setResizable(false);
	setLocationRelativeTo(null); // centraliza a janela
	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
                }
	});

        botaoJogarUI.addMouseListener(this);
        dadoUI.addMouseListener(this);
	addMouseListener(this);
	
    }



    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON1){
            if (e.getComponent() == botaoJogarUI ) {
                jogo.jogarDados();
                sincronizar();            
            }
            else if (e.getComponent() == dadoUI ) {
                int clickX = e.getX();
                for (int i=0; i<5;i++) {
                    if ( clickX >= Math.round(i*(DadoUI.dimDado*DadoUI.escalaDosDados + DadoUI.espacoEntreDados)) &
                            clickX <= Math.round((i+1)*DadoUI.dimDado*DadoUI.escalaDosDados + i*DadoUI.espacoEntreDados )) {
                        jogo.getJogada().marcarDado(i);
                        
                    
                        sincronizar();
                        break;
                    }
		}
            }
        }  
    }
        
    public void sincronizar() {
	dadoUI.sincronizar(jogo.getJogada().getValoresDados(), jogo.getJogada().getMarcadosDados());
	cartelaUI.sincronizar(jogo.getJogadorAtual().getCartela().getArrStatus(), 
                jogo.getJogadorAtual().getCartela().getArrPontos());
        botaoJogarUI.sincronizar(jogo.getPodeJogarDados());
	repaint();
    }

    static public void main(String[] args) {
	new YamUI();
    }

    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

}