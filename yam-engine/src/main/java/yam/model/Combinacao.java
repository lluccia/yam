package yam.model;

import yam.engine.Jogada;

public abstract class Combinacao {

    Jogada jogada;
    
    public Combinacao() {
    }

    public Combinacao(Jogada jogada) {
        this.jogada = jogada;
    }

    public void setJogada(Jogada jogada) {
        this.jogada = jogada;
    }

    /**
     * Indica se a combinação é válida para a jogada em análise
     */
    abstract boolean valida();

    /**
     * Total de pontos obtidos com a combinação válida, 0 se inválida
     */
    int pontuacao() {
        if (valida())
            return pontuacaoValida();
        else
            return 0;
    }
    
    protected abstract int pontuacaoValida();

}
