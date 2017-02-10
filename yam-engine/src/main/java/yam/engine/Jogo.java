package yam.engine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jogo {

    public class InicioSemJogadoresException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    public class JogadorExistenteException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    private StatusDoJogo statusDoJogo;

    private List<Jogador> jogadores;

    private Jogada jogada;

    private boolean podeJogarDados;

    private boolean podeMarcarDados;


    private int jogadorAtual = 0;
    private int jogadorAnterior = 0;

    private Recordes recordes;

    private boolean novosRecordesGerados;

    private boolean ultimoJogador;

    public Jogo() {
        this.statusDoJogo = StatusDoJogo.INICIALIZACAO;
        this.jogadores = new ArrayList<>();
        this.jogada = new Jogada();
        this.podeJogarDados = false;
        this.podeMarcarDados = false;
        this.novosRecordesGerados = false;

        recordes = new Recordes();

        leArquivoDeRecordes();
    }

    public void adicionarJogador(String nomeJogador) {
        Jogador jogador = new Jogador(nomeJogador);

        if (jogadores.contains(jogador))
            throw new JogadorExistenteException();

        jogadores.add(jogador);
    }

    public void removerJogador(String nomeJogador) {
        jogadores.remove(new Jogador(nomeJogador));

    }

    /**
     * @deprecated utilizar métodos adicionarJogador e removerJogador
     */
    @Deprecated
    public void definirJogadores(String[] nomes) {
        this.jogadores.clear();
        for (String nom : nomes) {
            this.jogadores.add(new Jogador(nom));
        }
    }

    public int getQuantJogadores() {
        return jogadores.size();
    }

    public List<Jogador> getJogadores() {
        return Collections.unmodifiableList(jogadores);
    }

    public String[][] getJogadoresComTotalDePontos() {
        String[][] tmp = new String[getQuantJogadores()][2];
        int i = 0;
    
        for (Jogador j : jogadores) {
            tmp[i][0] = j.getNome();
            tmp[i][1] = Integer.toString(j.getTotalDePontos());
            i++;
        }
        return tmp;
    }

    public Jogador getJogadorAtual() {
        return jogadores.get(jogadorAtual);
    }

    public Jogador getJogadorAnterior() {
        return jogadores.get(jogadorAnterior);
    }

    public int getIntJogadorAtual() {
        return jogadorAtual;
    }

    public void iniciarJogo() {
        if (getQuantJogadores() == 0)
            throw new InicioSemJogadoresException();

        this.statusDoJogo = StatusDoJogo.EM_ANDAMENTO;
        for (Jogador j : jogadores) {
            j.getCartela().limpaCartela();
        }
        jogada.desmarcarDados();
        jogada.zeraSeqJogada();
        this.podeJogarDados = true;
    }

    public void finalizarJogo() {
        this.statusDoJogo = StatusDoJogo.FINALIZACAO;
        this.podeJogarDados = false;

        // verifica recordes
        for (Jogador j : jogadores) {
            recordes.verificarRecorde(j.getNome(), j.getTotalDePontos());
        }
        try {
            gravaArquivoDeRecordes();
        } catch (Exception ex) {
            Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.statusDoJogo = StatusDoJogo.FINALIZADO;
    }

    public StatusDoJogo getStatusDoJogo() {
        return statusDoJogo;
    }

    public void setStatusDoJogo(StatusDoJogo statusDoJogo) {
        this.statusDoJogo = statusDoJogo;
    }

    public void proximoJogador() {
        this.jogadorAnterior = this.jogadorAtual;
        this.jogadorAtual = (this.jogadorAtual + 1) % getQuantJogadores();
        if (jogadorAtual == getQuantJogadores() - 1) {
            ultimoJogador = true;
        }
    }

    public void jogarDados() {
        if (jogada.jogarDados()) {
            getJogadorAtual().getCartela().verificarMarcacoes(jogada);
            if (jogada.getSeqJogada() > 0 && jogada.getSeqJogada() < 3) {
                podeMarcarDados = true;
            } else {
                podeJogarDados = false;
                podeMarcarDados = false;
            }
        }
    }

    public boolean marcarPontos(TipoDeColuna tpColuna, TipoDeLinha tpLinha) {
        if (getJogadorAtual().getCartela().marcaPontos(tpColuna, tpLinha, jogada)) {
            proximoJogador();
            jogada.zeraSeqJogada();
            podeJogarDados = true;
            podeMarcarDados = false;
            getJogadorAtual().getCartela().limpaStatus();

            if (ultimoJogador && getJogadorAtual().getCartela().cartelaCheia()) {
                finalizarJogo();
            }

            return true;
        }
        return false;
    }

    public void marcarDado(int posicao) {
        if (this.podeMarcarDados) {
            this.jogada.marcarDado(posicao);
        }
    }

    public boolean getPodeJogarDados() {
        return podeJogarDados;
    }

    public boolean getPodeMarcarDados() {
        return podeMarcarDados;
    }

    public int getQuantDadosLivres() {
        return jogada.getQuantDadosLivres();
    }

    public Jogada getJogada() {
        return jogada;
    }

    public int getTotalNosDados() {
        return jogada.getTotalNosDados();
    }

    public void leArquivoDeRecordes() {
        if (new File("recordes.bin").exists()) {
            try {
                ObjectInputStream objectIn = new ObjectInputStream(
                        new BufferedInputStream(new FileInputStream("recordes.bin")));

                Recordes tmpRecordes = (Recordes) objectIn.readObject();
                objectIn.close();
                recordes = tmpRecordes;
            } catch (Exception e) {
                gravaArquivoDeRecordes();
                this.novosRecordesGerados = true;
            }
        } else {
            gravaArquivoDeRecordes();
        }
    }

    public void gravaArquivoDeRecordes() {
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream("recordes.bin")));

            objectOut.writeObject(recordes);
            objectOut.close();
        } catch (IOException ex) {
            Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Recordes getRecordes() {
        return recordes;
    }

    public boolean novosRecordesGerados() {
        return novosRecordesGerados;
    }

    public StatusDoJogo getStatus() {
        return statusDoJogo;
    }

}
