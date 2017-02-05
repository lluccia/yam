package yam.model;

import java.util.Arrays;

import yam.engine.Jogada;

public class CombinacaoQuadra extends Combinacao {

    public CombinacaoQuadra(Jogada jogada) {
        super(jogada);
    }

    @Override
    boolean valida() {
        int[] valoresDados = jogada.getValoresDados();
        Arrays.sort(valoresDados);
        
        if (valoresDados[1] == valoresDados[2] && valoresDados[2] == valoresDados[3]
            && (valoresDados[0] == valoresDados[1] || valoresDados[3] == valoresDados[4]))
                return true;
        
        return false;
    }

    @Override
    protected int pontuacaoValida() {
        int[] valoresDados = jogada.getValoresDados();
        Arrays.sort(valoresDados);
        
        if (valoresDados[1] == valoresDados[2] && valoresDados[2] == valoresDados[3]) {
            if (valoresDados[0] == valoresDados[1]) {
                return 4 * valoresDados[0] + 20;
            } else if (valoresDados[3] == valoresDados[4]) {
                return 4 * valoresDados[4] + 20;
            }
        }
        return 0;
    }

}
