package yam.model;

import yam.engine.Coluna;
import yam.engine.Jogada;
import yam.engine.StatusDaLinha;
import yam.engine.TipoDeLinha;

public class ColunaDesordem extends Coluna {

    @Override
    public void verificarMarcacoes(Jogada jogada) {
        limpaMarcacoes();
        
        for (int i = 0; i <= 15; i++) {
            if (i == 6) {
                i = 9;
            }
            if (linhas.get(i).getStatusDaLinha() == StatusDaLinha.LIVRE) {
                // verifica mínimo e máximo de pontos
                if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.MIN_DE_PONTOS) {
                    if (maxDePontos == 0 || jogada.getTotalNosDados() < maxDePontos) {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                    } else {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                    }
                } else if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.MAX_DE_PONTOS) {
                    if (minDePontos == 0 || jogada.getTotalNosDados() > minDePontos) {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                    } else {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                    }
                }
                // verifica outras combinações
                else if (jogada.verificaCombinacao(linhas.get(i).getTipoDeLinha())) {
                    linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                } else {
                    linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                }
            }
        }
    }

}
