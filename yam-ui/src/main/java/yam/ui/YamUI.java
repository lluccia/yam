package yam.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import yam.engine.Jogo;
import yam.engine.StatusDoJogo;
import yam.engine.TipoDeColuna;
import yam.engine.TipoDeLinha;

public class YamUI extends JFrame implements MouseListener {

    private static final long serialVersionUID = -8484147352851325770L;

    private static final int WINDOW_WIDTH = 530;
    private static final int WINDOW_HEIGTH = 680;

    private DadoUI dadoUI;
    private CartelaUI cartelaUI;
    private BotaoJogarUI botaoJogarUI;
    private JogadoresUI jogadoresUI;

    private Jogo jogo;

    private JLabel lblTotalNosDados;
    private JLabel lblJogada;

    private Clip[] clipDados;
    private Clip clipMarca, clipRisca;

    private Timer timerCartela; // utilizado para manter a exibição da cartela
                                // atual por alguns segundos na tela no jogo
                                // multiplayer
    private boolean flagTimerCartela; // indica se o timer está sendo executado

    class TimerCartela extends TimerTask {
        public void run() {
            flagTimerCartela = false;
            sincronizar();
        }
    }

    public YamUI() throws Exception {
        super();
        setSize(WINDOW_WIDTH, WINDOW_HEIGTH);

        // inicialização do timer
        timerCartela = new Timer();
        flagTimerCartela = false;

        // preparação do engine
        this.jogo = new Jogo();
        if (jogo.novosRecordesGerados()) {
            JOptionPane.showMessageDialog(this, "Erro abrindo o arquivo de recordes. Um novo arquivo foi criado!");
        }

        // cria as classes
        this.dadoUI = new DadoUI(40, 20);
        this.cartelaUI = new CartelaUI(20, 110);
        this.botaoJogarUI = new BotaoJogarUI(330, 200);
        this.jogadoresUI = new JogadoresUI(320, 300);

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().setBackground(Color.getHSBColor(0.36f, 1.0f, 0.5f));
        setTitle("YAM!");

        // adiciona os componentes ao form principal
        getContentPane().add(dadoUI,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(dadoUI.getLocation(), dadoUI.getSize()));
        getContentPane().add(cartelaUI,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(cartelaUI.getLocation(), cartelaUI.getSize()));
        getContentPane().add(botaoJogarUI,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(botaoJogarUI.getLocation(), botaoJogarUI.getSize()));
        getContentPane().add(jogadoresUI,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(jogadoresUI.getLocation(), jogadoresUI.getSize()));

        // monta estrutura do menu
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
        getContentPane().add(lblTotalNosDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                lblTotalNosDados.getLocation(), lblTotalNosDados.getSize()));
        lblJogada = new JLabel("Jogadas restantes: 0");
        lblJogada.setFont(new Font("Courier New", Font.BOLD, 17));
        lblJogada.setBounds(310, 150, 200, 20);
        getContentPane().add(lblJogada,
                new org.netbeans.lib.awtextra.AbsoluteConstraints(lblJogada.getLocation(), lblJogada.getSize()));

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
        AudioInputStream asMarca, asRisca;

        clipDados = new Clip[5];
        for (int i = 0; i < 5; i++) {
            asDados[i] = AudioSystem
                    .getAudioInputStream(getClass().getClassLoader().getResourceAsStream("sounds/d" + (i + 1) + ".au"));
            clipDados[i] = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, asDados[i].getFormat()));
            clipDados[i].open(asDados[i]);
        }

        asMarca = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("sounds/marca.au"));
        clipMarca = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, asMarca.getFormat()));
        clipMarca.open(asMarca);

        asRisca = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("sounds/risca.au"));
        clipRisca = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, asRisca.getFormat()));
        clipRisca.open(asRisca);
    }

    public void sincronizar() {
        if (!flagTimerCartela) {
            cartelaUI.sincronizar(jogo.getJogadorAtual().getCartela().getArrStatus(),
                    jogo.getJogadorAtual().getCartela().getArrPontos());
            jogadoresUI.sincronizar(jogo);
            dadoUI.sincronizar(jogo.getJogada().getValoresDados(), jogo.getJogada().getMarcadosDados());
            botaoJogarUI.sincronizar(jogo.getPodeJogarDados());
        } else {
            cartelaUI.sincronizar(jogo.getJogadorAnterior().getCartela().getArrStatus(),
                    jogo.getJogadorAnterior().getCartela().getArrPontos());
            jogadoresUI.sincronizar(jogo);
        }

        lblTotalNosDados.setText("Total nos dados: " + jogo.getTotalNosDados());
        lblJogada.setText("Jogadas restantes: " + (3 - jogo.getJogada().getSeqJogada()));
        repaint();
        if (jogo.getStatusDoJogo() == StatusDoJogo.FINALIZADO) {
            exibeRecordes();

        }
    }

    static public void main(String[] args) throws Exception {
        new YamUI();
    }

    public void mouseClicked(MouseEvent e) {
        // não faz nada
    }

    public void mousePressed(MouseEvent e) {
        if (jogo.getStatusDoJogo() == StatusDoJogo.EM_ANDAMENTO) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getComponent() == dadoUI) {
                    int clickX = e.getX();
                    for (int i = 0; i < 5; i++) {
                        if (clickX >= Math.round(i * (DadoUI.dimDado * DadoUI.escalaDosDados + DadoUI.espacoEntreDados))
                                & clickX <= Math.round((i + 1) * DadoUI.dimDado * DadoUI.escalaDosDados
                                        + i * DadoUI.espacoEntreDados)) {
                            jogo.marcarDado(i);
                            sincronizar();
                            break;
                        }
                    }
                } else if (e.getComponent() == cartelaUI) {
                    int col = e.getX() / CartelaUI.tamCelX;
                    int lin = e.getY() / CartelaUI.tamCelY;

                    if (col > 0 & lin > 0) {
                        if (jogo.marcarPontos(TipoDeColuna.values()[col - 1], TipoDeLinha.values()[lin - 1])) {
                            switch (jogo.getJogadorAtual().getCartela().getStatus(TipoDeColuna.values()[col - 1],
                                    TipoDeLinha.values()[lin - 1])) {
                            case MARCADA:
                                audioMarca();
                                break;
                            case RISCADA:
                                audioRisca();
                                break;
                            }
                            if (jogo.getQuantJogadores() > 1) {
                                flagTimerCartela = true;
                                botaoJogarUI.sincronizar(false);
                                timerCartela.schedule(new TimerCartela(), 2 * 1000L);
                            }
                            sincronizar();
                        }

                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Reproduz som do arremesso dos dados.
     * 
     * @param qtd
     *            quantidade de dados arremessados (de 1 a 5)
     */
    @SuppressWarnings("static-access")
    private void audioDados(int qtd) {
        if (qtd > 0 && qtd < 6) {
            clipDados[qtd - 1].start();
            clipDados[qtd - 1].setFramePosition(0);
            while (clipDados[qtd - 1].isRunning()) {
                try {
                    Thread.sleep(100);
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
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(YamUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        clipRisca.setFramePosition(0);
    }

    @SuppressWarnings("static-access")
    private void menuNovoJogoUIClick(java.awt.event.ActionEvent evt) {

        NovoJogoUI novoJogoUI = new NovoJogoUI(this);

        if (novoJogoUI.getJogadores() != null) {

            jogo.definirJogadores(novoJogoUI.getJogadores());

            jogo.iniciarJogo();
            sincronizar();
        }
    }

    private void menuJogoRecordesUIClick(java.awt.event.ActionEvent evt) {
        exibeRecordes();
    }

    private void botaoJogarUIClick(java.awt.event.ActionEvent evt) {
        if (jogo.getStatusDoJogo() == StatusDoJogo.EM_ANDAMENTO) {
            if (jogo.getPodeJogarDados()) {
                jogo.jogarDados();
                audioDados(jogo.getQuantDadosLivres());
                sincronizar();
            }
        }
    }

    private void exibeRecordes() {
        JOptionPane.showMessageDialog(this, jogo.getRecordes().getStringRecordes(), "Recordes",
                JOptionPane.PLAIN_MESSAGE);
    }
}