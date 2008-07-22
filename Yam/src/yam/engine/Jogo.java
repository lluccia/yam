package yam.engine;


import java.util.ArrayList;

public class Jogo {

    public StatusDoJogo statusDoJogo;
    
    private ArrayList<Jogador> jogadores;
    
    private int jogadorAtual = 0;
    
    private int quantJogadores;

    public Jogo (String[] nomes) {
        this.statusDoJogo = StatusDoJogo.inicializacao;
	this.quantJogadores = nomes.length;
	this.jogadores = new ArrayList<Jogador>();
	for (String nom: nomes) {
	    this.jogadores.add(new Jogador(nom));
	}
    }

    public void iniciarJogo() {
	this.statusDoJogo = StatusDoJogo.emAndamento;
	
    }
    
    public void finalizarJogo() {
	this.statusDoJogo = StatusDoJogo.finalizacao;
	
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

    public void setJogadores (ArrayList<Jogador> val) {
        this.jogadores = val;
    }

    public void proximoJogador() {
        this.jogadorAtual = (this.jogadorAtual + 1) % this.quantJogadores;
    } 
    
}

