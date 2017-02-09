package yam.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cartela {

    private List<Coluna> colunas;

    public Cartela() {
        this.colunas = new ArrayList<>();
        this.colunas.add(Coluna.of(TipoDeColuna.DESCE));
        this.colunas.add(Coluna.of(TipoDeColuna.SOBE));
        this.colunas.add(Coluna.of(TipoDeColuna.DESORDEM));
        this.colunas.add(Coluna.of(TipoDeColuna.SECO));
    }

    public List<Coluna> getColunas() {
        return Collections.unmodifiableList(colunas);
    }

    public int getTotalDePontos() {
        int totalDePontos = 0;

        for (TipoDeColuna col : TipoDeColuna.values())
            totalDePontos += getPontos(col, TipoDeLinha.SEGUNDO_E_TERCEIRO_TOTAIS);

        return totalDePontos;
    }

    public int getPontos(TipoDeColuna coluna, TipoDeLinha linha) {
        return colunas.get(coluna.ordinal()).getPontos(linha);
    }

    public StatusDaLinha getStatus(TipoDeColuna coluna, TipoDeLinha linha) {
        return colunas.get(coluna.ordinal()).getStatus(linha);
    }

    public boolean marcaPontos(TipoDeColuna coluna, TipoDeLinha linha, Jogada jog) {
        return colunas.get(coluna.ordinal()).marcaPontos(linha, jog);
    }

    public boolean cartelaCheia() {
        boolean cheia = true;

        for (TipoDeColuna col : TipoDeColuna.values()) {
            if (!colunas.get(col.ordinal()).colunaCheia()) {
                cheia = false;
                break;
            }
        }

        return cheia;
    }

    public void limpaStatus() {
        for (Coluna col : colunas) {
            for (TipoDeLinha lin : TipoDeLinha.values()) {
                if (col.getStatus(lin) == StatusDaLinha.MARCAVEL || col.getStatus(lin) == StatusDaLinha.RISCAVEL) {
                    col.setStatus(lin, StatusDaLinha.LIVRE);
                }
            }
        }
    }

    public void limpaPontos() {
        for (Coluna col : colunas) {
            col.limpaMaxeMin();
            for (TipoDeLinha lin : TipoDeLinha.values()) {
                col.limpaPontos(lin);
                col.setStatus(lin, StatusDaLinha.LIVRE);
            }
        }
    }

    public void limpaCartela() {
        limpaPontos();
        limpaStatus();
    }

    public int[][] getArrPontos() {
        int[][] retArray = new int[colunas.size()][colunas.get(0).getLinhas().size() + 1];

        for (TipoDeColuna col : TipoDeColuna.values()) {
            for (TipoDeLinha lin : TipoDeLinha.values()) {
                retArray[col.ordinal()][lin.ordinal()] = getPontos(col, lin);
            }
        }

        retArray[2][18] = getTotalDePontos();

        return retArray;
    }

    public StatusDaLinha[][] getArrStatus() {
        StatusDaLinha[][] retArray = new StatusDaLinha[colunas.size()][colunas.get(0).getLinhas().size() + 1];

        for (TipoDeColuna col : TipoDeColuna.values()) {
            for (TipoDeLinha lin : TipoDeLinha.values()) {
                if ((lin == TipoDeLinha.PRIMEIRO_TOTAL || lin == TipoDeLinha.BONUS || lin == TipoDeLinha.SEGUNDO_TOTAL
                        || lin == TipoDeLinha.TERCEIRO_TOTAL || lin == TipoDeLinha.SEGUNDO_E_TERCEIRO_TOTAIS)
                        && getPontos(col, lin) > 0) {
                    retArray[col.ordinal()][lin.ordinal()] = StatusDaLinha.MARCADA;
                } else {
                    retArray[col.ordinal()][lin.ordinal()] = getStatus(col, lin);
                }
            }
        }
        if (getTotalDePontos() > 0) {
            retArray[2][18] = StatusDaLinha.MARCADA;
        }
        return retArray;
    }

    public void verificarMarcacoes(Jogada jogada) {
        for (Coluna c : colunas) {
            c.verificarMarcacoes(jogada);
        }
    }
}