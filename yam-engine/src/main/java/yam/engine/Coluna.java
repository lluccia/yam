package yam.engine;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import yam.model.Celula;
import yam.model.ColunaAscendente;
import yam.model.ColunaDescendente;
import yam.model.ColunaDesordem;
import yam.model.ColunaSeco;

public abstract class Coluna {

    @Deprecated
    protected List<Linha> linhas;
    
    protected List<Celula> celulasSuperiores;

    protected int primeiroTotal;
    protected int bonus;
    protected int segundoTotal;
    
    protected List<Celula> celulasInferiores;
    
    protected int terceiroTotal;
    protected int segundoETerceiroTotais;
    
    @Deprecated
    protected int minDePontos;
    @Deprecated
    protected int maxDePontos;

    public static Coluna of(TipoDeColuna tipo) {
        switch(tipo) {
        case DESCE:
            return new ColunaDescendente();
        case SOBE:
            return new ColunaAscendente();
        case DESORDEM:
            return new ColunaDesordem();
        case SECO:
            return new ColunaSeco();
        default:
            throw new IllegalArgumentException("Tipo de coluna inválido: " + tipo);
        }
    }
    
    protected Coluna() {
        this.linhas = new ArrayList<>();
        for (TipoDeLinha tipoDeLinha : EnumSet.allOf(TipoDeLinha.class)) {
            linhas.add(new Linha(tipoDeLinha));
        }
    }
    

    /**
     * Marca pontos na linha especificada. Caso não seja possível marcar pontos
     * na linha, ela será riscada se possível.
     * 
     * @param tpLinha
     *            tipo da linha em que os pontos serão marcados.
     * @param jog
     *            jogada que deve ser utilizada para verificar se a marcação é
     *            possível.
     * @return <b>true</b>: quando for possível marcar pontos ou riscar a linha
     *         <br/>
     *         <b>false</b>: quando não for possível
     */
    public boolean marcaPontos(TipoDeLinha tpLinha, Jogada jog) {
        switch (linhas.get(tpLinha.ordinal()).getStatusDaLinha()) {
        case MARCAVEL:
            int pontos;

            switch (tpLinha) {
            case MIN_DE_PONTOS:
                pontos = jog.getTotalNosDados();
                minDePontos = pontos;
                break;
            case MAX_DE_PONTOS:
                pontos = jog.getTotalNosDados();
                maxDePontos = pontos;
                break;
            default:
                pontos = jog.getPontosComb(tpLinha);
                break;
            }

            linhas.get(tpLinha.ordinal()).setPontos(pontos);
            linhas.get(tpLinha.ordinal()).setStatusDaLinha(StatusDaLinha.MARCADA);
            sumarizaTotais();
            return true;
        case RISCAVEL:
            linhas.get(tpLinha.ordinal()).setStatusDaLinha(StatusDaLinha.RISCADA);
            return true;
        default:
            return false;
        }
    }

    /**
     * Verifica em quais linhas é possível marcar pontos ou riscar.
     * 
     * @param jogada
     *            jogada que será utilizada para análise das marcações
     *            possíveis.
     */
    public abstract void verificarMarcacoes(Jogada jogada); 

    protected void limpaMarcacoes() {
        for (Linha linha : linhas) {
            linha.limpaMarcacao();
        }
    }


    public List<Linha> getLinhas() {
        return linhas;
    }

    /**
     * Verifica se a coluna está toda preenchida
     * 
     * @return <b>true</b>: se estiver toda preenchida<br/>
     *         <b>false</b>: se houver alguma linha em branco
     */
    public boolean colunaCheia() {
        boolean cheia = true;

        for (Linha linha : linhas) {
            if (linha.getTipoDeLinha() != TipoDeLinha.PRIMEIRO_TOTAL
                    && linha.getTipoDeLinha() != TipoDeLinha.BONUS
                    && linha.getTipoDeLinha() != TipoDeLinha.SEGUNDO_TOTAL
                    && linha.getTipoDeLinha() != TipoDeLinha.TERCEIRO_TOTAL
                    && linha.getTipoDeLinha() != TipoDeLinha.SEGUNDO_E_TERCEIRO_TOTAIS) {

                if (linha.getStatusDaLinha() != StatusDaLinha.MARCADA
                        && linha.getStatusDaLinha() != StatusDaLinha.RISCADA) {
                    cheia = false;
                    break;
                }
            }
        }

        return cheia;
    }

    public int getPontos(TipoDeLinha linha) {
        return linhas.get(linha.ordinal()).getPontos();
    }

    public StatusDaLinha getStatus(TipoDeLinha linha) {
        return linhas.get(linha.ordinal()).getStatus();
    }

    public void setStatus(TipoDeLinha linha, StatusDaLinha stat) {
        linhas.get(linha.ordinal()).setStatus(stat);
    }

    public void setPontos(TipoDeLinha linha, int pontos) {
        this.linhas.get(linha.ordinal()).setPontos(pontos);
    }

    public void limpaPontos(TipoDeLinha linha) {
        setPontos(linha, 0);
    }

    public void limpaMaxeMin() {
        this.minDePontos = 0;
        this.maxDePontos = 0;
    }

    private void sumarizaTotais() {
        int temp1 = 0, temp2 = 0;
        for (int i = TipoDeLinha.UM.ordinal(); i <= TipoDeLinha.SEIS.ordinal(); i++) {
            temp1 += linhas.get(i).getPontos();
        }
        linhas.get(TipoDeLinha.PRIMEIRO_TOTAL.ordinal()).setPontos(temp1);

        // verifica se possui pontos suficientes para bonus
        if (temp1 >= 60) {
            linhas.get(TipoDeLinha.BONUS.ordinal()).setPontos(30);
        } else {
            linhas.get(TipoDeLinha.BONUS.ordinal()).setPontos(0);
        }

        temp1 += linhas.get(TipoDeLinha.BONUS.ordinal()).getPontos();
        linhas.get(TipoDeLinha.SEGUNDO_TOTAL.ordinal()).setPontos(temp1);

        for (int i = TipoDeLinha.QUADRA.ordinal(); i <= TipoDeLinha.YAM.ordinal(); i++) {
            temp2 += linhas.get(i).getPontos();
        }
        linhas.get(TipoDeLinha.TERCEIRO_TOTAL.ordinal()).setPontos(temp2);

        linhas.get(TipoDeLinha.SEGUNDO_E_TERCEIRO_TOTAIS.ordinal()).setPontos(temp1 + temp2);
    }
}
