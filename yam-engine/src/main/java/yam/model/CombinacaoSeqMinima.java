package yam.model;

import java.util.Arrays;

import yam.engine.Jogada;

public class CombinacaoSeqMinima extends Combinacao {

    public CombinacaoSeqMinima(Jogada jogada) {
        super(jogada);
    }

    @Override
    public boolean valida() {
        int[] valoresDados = jogada.getValoresDados();
        
        return Arrays.equals(valoresDados, new int[] {1, 2, 3, 4, 5});
    }

    @Override
    protected int pontuacaoValida() {
        return jogada.getTotalNosDados() + 35;
    }

}
