package yam.model;

import java.util.Arrays;

import yam.engine.Jogada;

public class CombinacaoSeqMaxima extends Combinacao {

    public CombinacaoSeqMaxima(Jogada jogada) {
        super(jogada);
    }

    @Override
    public boolean valida() {
        int[] valoresDados = jogada.getValoresDados();
        
        return Arrays.equals(valoresDados, new int[] {2, 3, 4, 5, 6});
    }

    @Override
    protected int pontuacaoValida() {
        return jogada.getTotalNosDados() + 40;
    }

}
