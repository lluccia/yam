package yam.model;

public class Celula {

    public static class CelulaIndisponivelException extends RuntimeException {
        private static final long serialVersionUID = -8886771168250181197L;
    }

    private Combinacao combinacao;
    
    private Integer pontos;

    public Celula() {
    }

    public Celula(Combinacao combinacao) {
        this.combinacao = combinacao;
    }

    public void setCombinacao(Combinacao combinacao) {
        this.combinacao = combinacao;
    }

    public boolean estaVazia() {
        return pontos == null;
    }

    public boolean podeMarcar() {
        return estaVazia() && combinacao.valida();
    }

    public boolean podeRiscar() {
        return estaVazia() && !combinacao.valida();
    }

    public void marcaPontos() {
        if (!estaVazia())
            throw new CelulaIndisponivelException();
            
        this.pontos = combinacao.pontuacao();
    }

    public Integer getPontos() {
        return pontos;
    }

}
