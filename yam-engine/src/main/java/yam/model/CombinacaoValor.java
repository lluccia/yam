package yam.model;

import yam.engine.Jogada;

public class CombinacaoValor extends Combinacao {

    public class ValorInvalidoException extends RuntimeException {
        private static final long serialVersionUID = -1887229065136097877L;
    }

    private int valor;

    public CombinacaoValor(Jogada jogada, int valor) {
        super(jogada);
        if (valor < 1 || valor > 6)
            throw new ValorInvalidoException();
        
        this.valor = valor;
    }

    @Override
    boolean valida() {
        for (int valorDado: jogada.getValoresDados())
            if (valorDado == valor)
                return true;
        
        return false;
    }

    @Override
    protected int pontuacaoValida() {
        int pontos = 0;
        
        for (int valorDado: jogada.getValoresDados())
            if (valorDado == valor)
                pontos += valorDado;
        
        return pontos;
    }
    

}
