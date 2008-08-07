package yam.engine;


import java.util.ArrayList;

public class Jogo {

    public StatusDoJogo statusDoJogo;
    
    private ArrayList<Jogador> jogadores;
    
    private Jogada jogada;

    private boolean podeJogarDados;
    
    private boolean podeMarcarDados;
    
    private boolean podeMarcarPontos;
    
    private int jogadorAtual = 0;
    
    private int quantJogadores;

    public Jogo (String[] nomes) {
        this.statusDoJogo = StatusDoJogo.inicializacao;
	this.quantJogadores = nomes.length;
	this.jogadores = new ArrayList<Jogador>();
	this.jogada = new Jogada();
	this.podeJogarDados = false;
	this.podeMarcarDados = false;
	this.podeMarcarPontos = false;
        
        
	for (String nom: nomes) {
	    this.jogadores.add(new Jogador(nom));
	}
    }

    public void iniciarJogo() {
        this.statusDoJogo = StatusDoJogo.emAndamento;
        for (Jogador j: jogadores) {
            j.getCartela().limpaCartela();
        }
        jogada.desmarcarDados();
        jogada.zeraSeqJogada();
	this.podeJogarDados = true;
    }
    
    public void finalizarJogo() {
	this.statusDoJogo = StatusDoJogo.finalizacao;
        this.podeJogarDados = false;
    }
    
    public StatusDoJogo getStatusDoJogo() {
	return statusDoJogo;
    }

    public void setStatusDoJogo(StatusDoJogo statusDoJogo) {
	this.statusDoJogo = statusDoJogo;
    }
    
    public ArrayList<Jogador> getJogadores () {
        return jogadores;
    }

    public Jogador getJogadorAtual () {
        return jogadores.get(jogadorAtual);
    }
    
    public void setJogadores (ArrayList<Jogador> val) {
        this.jogadores = val;
    }

    public void proximoJogador() {
        this.jogadorAtual = (this.jogadorAtual + 1) % this.quantJogadores;
    }
    
    public void jogarDados(){
	if (jogada.jogarDados()) {
            getJogadorAtual().getCartela().verificarMarcacoes(jogada);
            if (jogada.getSeqJogada() > 0 & jogada.getSeqJogada() < 3) {
                podeMarcarPontos = true;
                podeMarcarDados = true;
            } else {
                podeJogarDados = false;
                podeMarcarDados = false;
            }
        }
    }
    
    public boolean marcarPontos(TipoDeColuna tpColuna,TipoDeLinha tpLinha){
	if ( getJogadorAtual().getCartela().marcaPontos(tpColuna, tpLinha, jogada)) {
            proximoJogador();
            jogada.zeraSeqJogada();
            podeJogarDados = true;
            podeMarcarPontos = false;
            getJogadorAtual().getCartela().limpaStatus();
            if (getJogadorAtual().getCartela().cartelaCheia()) { finalizarJogo(); }
            return true;
        }
        return false;
    }
    
    public void marcarDado(int posicao) {
	if (this.podeMarcarDados) { this.jogada.marcarDado(posicao); }
    }
    
    public boolean getPodeJogarDados() {
        return podeJogarDados;
    }
    
    public boolean getPodeMarcarDados() {
        return podeMarcarDados;
    }
    
    public int getQuantDadosLivres(){
        return jogada.getQuantDadosLivres();        
    }
    
    public Jogada getJogada() {
	return jogada;
    }
    
    public int getTotalNosDados() {
	return jogada.getTotalNosDados();
    }
}

