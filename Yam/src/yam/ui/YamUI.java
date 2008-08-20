// TODO Acrescentar delay na demonstração da cartela após marcação para jogo multiplayer

package yam.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.sound.sampled.*;
import yam.engine.*;

public class YamUI extends JFrame implements MouseListener {

    private static final int windowWidth = 530;
    private static final int windowHeigth = 680;
    private DadoUI dadoUI;
    private CartelaUI cartelaUI;
    private BotaoJogarUI botaoJogarUI;
    private JogadoresUI jogadoresUI;
    
    private JLabel lblTotalNosDados;
    private JLabel lblJogada;

    private Jogo jogo;

    private Clip[] clipDados;
    private Clip clipMarca, clipRisca;
    
    public YamUI() throws Exception {    
        super();
        setSize(windowWidth, windowHeigth);
        
        //preparação do engine
        this.jogo = new Jogo();
        
        //cria as classes
        this.dadoUI = new DadoUI(40, 20);
	this.cartelaUI = new CartelaUI(20, 110);
	this.botaoJogarUI = new BotaoJogarUI(330, 200);
	this.jogadoresUI = new JogadoresUI(320, 300);
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().setBackground(Color.getHSBColor(0.36f, 1.0f, 0.5f));
        setTitle("YAM!");
        
        //adiciona os componentes ao form principal
        getContentPane().add(dadoUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(dadoUI.getLocation(),dadoUI.getSize()));
        getContentPane().add(cartelaUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(cartelaUI.getLocation(),cartelaUI.getSize()));
        getContentPane().add(botaoJogarUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(botaoJogarUI.getLocation(),botaoJogarUI.getSize()));
        getContentPane().add(jogadoresUI,new org.netbeans.lib.awtextra.AbsoluteConstraints(jogadoresUI.getLocation(),jogadoresUI.getSize()));
        
        
        //monta estrutura do menu
        JMenuBar menuBarUI;
        JMenu menuJogoUI;
        JMenuItem menuJogoNovoUI, menuJogoRecordesUI;
        menuBarUI = new JMenuBar();
        menuJogoUI = new JMenu("Jogo");
        menuJogoNovoUI = new JMenuItem("Novo");
        menuJogoNovoUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoJogoUIClick(evt);
            }
        });
        menuJogoRecordesUI = new JMenuItem("Recordes");
        menuJogoRecordesUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuJogoRecordesUIClick(evt);
            }
        });
        
        menuJogoUI.add(menuJogoNovoUI);
        menuJogoUI.add(menuJogoRecordesUI);
        menuBarUI.add(menuJogoUI);
        
        this.setJMenuBar(menuBarUI);
        
        lblTotalNosDados = new JLabel("Total nos dados: 15");
        lblTotalNosDados.setFont(new Font("Courier New", Font.BOLD, 17));
        lblTotalNosDados.setBounds(310, 120, 200, 20);
        getContentPane().add(lblTotalNosDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(lblTotalNosDados.getLocation(),lblTotalNosDados.getSize()));
        lblJogada = new JLabel("Jogadas restantes: 0");
        lblJogada.setFont(new Font("Courier New", Font.BOLD, 17));
        lblJogada.setBounds(310, 150, 200, 20);
        getContentPane().add(lblJogada, new org.netbeans.lib.awtextra.AbsoluteConstraints(lblJogada.getLocation(),lblJogada.getSize()));
        
        
        // configura comportamento da janela
        setLocationRelativeTo(null); // centraliza a janela
	setVisible(true);
	setResizable(false);
      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dadoUI.addMouseListener(this);
        cartelaUI.addMouseListener(this);

        botaoJogarUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoJogarUIClick(evt);
            }
        });
	
        // inicializa sistema de áudio
        AudioInputStream[] asDados = new AudioInputStream[5];
        AudioInputStream asMarca,asRisca;
        
        clipDados = new Clip[5];
        for (int i=0 ; i<5; i++) {
            asDados[i] = AudioSystem.getAudioInputStream(new File("sounds/d" + (i+1) + ".au"));
            clipDados[i] = (Clip) AudioSystem.getLine(
                    new DataLine.Info(Clip.class, asDados[i].getFormat())
                    );
            clipDados[i].open(asDados[i]);
        }

        asMarca = AudioSystem.getAudioInputStream(new File("sounds/marca.au"));
        clipMarca = (Clip) AudioSystem.getLine(
                    new DataLine.Info(Clip.class, asMarca.getFormat())
                    );
        clipMarca.open(asMarca);
        
        asRisca = AudioSystem.getAudioInputStream(new File("sounds/risca.au"));
        clipRisca =(Clip) AudioSystem.getLine(
                    new DataLine.Info(Clip.class, asRisca.getFormat())
                    );
        clipRisca.open(asRisca);
    }
        
    public void sincronizar() {
	dadoUI.sincronizar(jogo.getJogada().getValoresDados(), jogo.getJogada().getMarcadosDados());
	cartelaUI.sincronizar(jogo.getJogadorAtual().getCartela().getArrStatus(), 
                jogo.getJogadorAtual().getCartela().getArrPontos());
        botaoJogarUI.sincronizar(jogo.getPodeJogarDados());
        jogadoresUI.sincronizar(jogo);
        
        lblTotalNosDados.setText("Total nos dados: " + jogo.getTotalNosDados());
        lblJogada.setText("Jogadas restantes: " + (3 - jogo.getJogada().getSeqJogada()));
	repaint();
    }

    static public void main(String[] args) throws Exception {
	new YamUI();
    }

    public void mouseClicked(MouseEvent e) {
        // não faz nada
    }
    
    public void mousePressed(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON1){
            if (e.getComponent() == dadoUI ) {
                int clickX = e.getX();
                for (int i=0; i<5;i++) {
                    if ( clickX >= Math.round(i*(DadoUI.dimDado*DadoUI.escalaDosDados + DadoUI.espacoEntreDados)) &
                            clickX <= Math.round((i+1)*DadoUI.dimDado*DadoUI.escalaDosDados + i*DadoUI.espacoEntreDados )) {
                        jogo.marcarDado(i);
                        sincronizar();
                        break;
                    }
		}
            }
            else if (e.getComponent() == cartelaUI ) {
                int col = e.getX() / CartelaUI.tamCelX;
                int lin = e.getY() / CartelaUI.tamCelY;

                if (col>0 & lin>0) {
                    if (jogo.marcarPontos(TipoDeColuna.values()[col-1], TipoDeLinha.values()[lin-1])) {
                        switch (jogo.getJogadorAtual().getCartela().
                                getStatus(TipoDeColuna.values()[col-1], TipoDeLinha.values()[lin-1])) {
                            case marcada:
                                audioMarca();
                                break;
                            case riscada:
                                audioRisca();
                                break;          
                        }  
                    }
                    sincronizar();
                }
            }
        }  
    }

    public void mouseReleased(MouseEvent e) {
        // não faz nada
    }

    public void mouseEntered(MouseEvent e) {
        // não faz nada
    }

    public void mouseExited(MouseEvent e) {
        // não faz nada
    }

    /**
     * Reproduz som do arremesso dos dados.
     * 
     * @param qtd quantidade de dados arremessados (de 1 a 5)
     */
    @SuppressWarnings("static-access")
    private void audioDados(int qtd) {
        if (qtd > 0 & qtd < 6) {
            clipDados[qtd-1].start();
            clipDados[qtd-1].setFramePosition(0);
            while (clipDados[qtd-1].isRunning()) {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(YamUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Reproduz som da marcação de pontos na cartela.
     */
    @SuppressWarnings("static-access")
    private void audioMarca() {
        clipMarca.start();
        while (clipMarca.isRunning()) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(YamUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clipMarca.setFramePosition(0);
    }
    
    /**
     * Reproduz som da marcação de um risco na cartela.
     */
    @SuppressWarnings("static-access")
    private void audioRisca() {
        clipRisca.start();
        while (clipRisca.isRunning()) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(YamUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clipRisca.setFramePosition(0);
    }
    
    @SuppressWarnings("static-access")
    private void menuNovoJogoUIClick(java.awt.event.ActionEvent evt) {

        NovoJogoUI novoJogoUI = new NovoJogoUI(this);
        
        jogo.definirJogadores (novoJogoUI.getJogadores());
            
        jogo.iniciarJogo();
        sincronizar();
    }
    
    private void menuJogoRecordesUIClick(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, jogo.getRecordes().getStringRecordes(), "Recordes", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void botaoJogarUIClick(java.awt.event.ActionEvent evt) {
        if (jogo.getPodeJogarDados()) {
            jogo.jogarDados();
            audioDados(jogo.getQuantDadosLivres());
            sincronizar();     
        }
    }
}