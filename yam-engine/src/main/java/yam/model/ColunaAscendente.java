package yam.model;

import yam.engine.Coluna;
import yam.engine.Jogada;
import yam.engine.StatusDaLinha;
import yam.engine.TipoDeLinha;

public class ColunaAscendente extends Coluna {

    @Override
    public void verificarMarcacoes(Jogada jogada) {
        limpaMarcacoes();
        for (int i = 15; i >= 0; i--) {
            if (i == 8) {
                i = 5;
            }
            if (linhas.get(i).getStatusDaLinha() == StatusDaLinha.LIVRE) {
                // verifica mínimo e máximo de pontos
                if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.MAX_DE_PONTOS) {
                    linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                } else if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.MIN_DE_PONTOS) {
                    if (jogada.getTotalNosDados() < maxDePontos) {
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
                break;
            }
        }
    }

}
