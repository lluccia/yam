package yam.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import yam.engine.*;

public class YamUI extends JFrame implements MouseListener,ActionListener  {

    private static final int windowWidth = 530;
    private static final int windowHeigth = 680;
    private DadoUI dadoUI;
    private CartelaUI cartelaUI;
    private BotaoJogarUI botaoJogarUI;
    private JLabel lblTotalNosDados;
    private JLabel lblJogada;

    private Jogo jogo;

    public YamUI() {    
        super();
        setSize(windowWidth, windowHeigth);
        
        //preparação do engine
        this.jogo = new Jogo(new String[]{"teste"});
        
        //criação das classes
        this.dadoUI = new DadoUI(40, 20);
	this.cartelaUI = new CartelaUI(20, 110);
	this.botaoJogarUI = new BotaoJogarUI(330, 200);
        
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().setBackground(Color.getHSBColor(0.36f, 1.0f, 0.5f));
        setTitle("YAM!");
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
        menuJogoNovoUI.setActionCommand("actNovoJogo");
        menuJogoNovoUI.addActionListener(this);
        menuJogoUI.add(menuJogoNovoUI);
        menuBarUI.add(menuJogoUI);
        
        this.setJMenuBar(menuBarUI);
        
        lblTotalNosDados = new JLabel("Total nos dados: 15");
        lblTotalNosDados.setFont(new Font("Courier New", Font.BOLD, 17));
        lblTotalNosDados.setBounds(310, 120, 200, 20);
        getContentPane().add(lblTotalNosDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(lblTotalNosDados.getLocation(),lblTotalNosDados.getSize()));
        lblJogada = new JLabel("Jogada: 0");
        lblJogada.setFont(new Font("Courier New", Font.BOLD, 17));
        lblJogada.setBounds(355, 150, 200, 20);
        getContentPane().add(lblJogada, new org.netbeans.lib.awtextra.AbsoluteConstraints(lblJogada.getLocation(),lblJogada.getSize()));
        
        
        // configura comportamento da janela
        setLocationRelativeTo(null); // centraliza a janela
	setVisible(true);
	setResizable(false);
	
	addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
                }
	});

        dadoUI.addMouseListener(this);
        cartelaUI.addMouseListener(this);

        botaoJogarUI.addActionListener(this);
        botaoJogarUI.setActionCommand("actJogarDados");
	
    }

    public void mouseClicked(MouseEvent e) {
        // não faz nada
    }
        
    public void sincronizar() {
	dadoUI.sincronizar(jogo.getJogada().getValoresDados(), jogo.getJogada().getMarcadosDados());
	cartelaUI.sincronizar(jogo.getJogadorAtual().getCartela().getArrStatus(), 
                jogo.getJogadorAtual().getCartela().getArrPontos());
        botaoJogarUI.sincronizar(jogo.getPodeJogarDados());
        
        lblTotalNosDados.setText("Total nos dados: " + jogo.getTotalNosDados());
        lblJogada.setText("Jogada: " + jogo.getJogada().getSeqJogada());
	repaint();
    }

    static public void main(String[] args) {
	new YamUI();
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
                    jogo.marcarPontos(TipoDeColuna.values()[col-1], TipoDeLinha.values()[lin-1]);
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

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
//        System.out.println(action);
        
        if (action.equals("actJogarDados") & jogo.getPodeJogarDados()) {
            jogo.jogarDados();
            sincronizar();            
        }
        else if (action.equals("actNovoJogo")) {
            jogo.iniciarJogo();
            sincronizar();
        }
    }

}