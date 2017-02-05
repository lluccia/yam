package yam.model;

import yam.engine.Jogada;

public class CombinacaoYam extends Combinacao {

    public CombinacaoYam(Jogada jogada) {
        super(jogada);
    }

    @Override
    public boolean valida() {
        int[] valoresDados = jogada.getValoresDados();
        
        int primeiroDado = valoresDados[0];
        for (int i = 1; i < 5; i++)
            if (valoresDados[i] != primeiroDado)
                return false;
            
        return true;
    }

    @Override
    public int pontuacaoValida() {
        return jogada.getTotalNosDados() + 50;
    }
}
