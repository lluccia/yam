package yam.model;

import java.util.Arrays;

import yam.engine.Jogada;

public class CombinacaoFullHand extends Combinacao {

    public CombinacaoFullHand(Jogada jogada) {
        super(jogada);
    }

    @Override
    public boolean valida() {
        int[] valoresDados = jogada.getValoresDados();
        
        Arrays.sort(valoresDados);
        return (valoresDados[0] == valoresDados[1] && valoresDados[1] == valoresDados[2]
                    && valoresDados[2] != valoresDados[3] && valoresDados[3] == valoresDados[4])
                || (valoresDados[0] == valoresDados[1] && valoresDados[1] != valoresDados[2]
                    && valoresDados[2] == valoresDados[3] && valoresDados[3] == valoresDados[4]);
        
    }

    @Override
    protected int pontuacaoValida() {
        return jogada.getTotalNosDados() + 30;
    }

}
