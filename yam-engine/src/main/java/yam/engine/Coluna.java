package yam.engine;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Coluna {

    private List<Linha> linhas;

    private int minDePontos;
    private int maxDePontos;

    private TipoDeColuna tipoDeColuna;

    public Coluna(TipoDeColuna tipo) {
        this.setTipoDeColuna(tipo);
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
            case minDePontos:
                pontos = jog.getTotalNosDados();
                minDePontos = pontos;
                break;
            case maxDePontos:
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
    public void verificarMarcacoes(Jogada jogada) {
        // limpa status das marcações possíveis anteriores
        for (Linha linha : linhas) {
            if (linha.getStatusDaLinha() == StatusDaLinha.MARCAVEL
                    || linha.getStatusDaLinha() == StatusDaLinha.RISCAVEL) {
                linha.setStatusDaLinha(StatusDaLinha.LIVRE);
            }
        }

        switch (tipoDeColuna) {
        case DESCE:
            for (int i = 0; i <= 15; i++) {
                if (i == 6) {
                    i = 9;
                }
                if (linhas.get(i).getStatusDaLinha() == StatusDaLinha.LIVRE) {
                    // verifica mínimo e máximo de pontos
                    if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.minDePontos) {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                    } else if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.maxDePontos) {
                        if (jogada.getTotalNosDados() > minDePontos) {
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
            break;
        case SOBE:
            for (int i = 15; i >= 0; i--) {
                if (i == 8) {
                    i = 5;
                }
                if (linhas.get(i).getStatusDaLinha() == StatusDaLinha.LIVRE) {
                    // verifica mínimo e máximo de pontos
                    if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.maxDePontos) {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                    } else if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.minDePontos) {
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
            break;
        case DESORDEM:
            for (int i = 0; i <= 15; i++) {
                if (i == 6) {
                    i = 9;
                }
                if (linhas.get(i).getStatusDaLinha() == StatusDaLinha.LIVRE) {
                    // verifica mínimo e máximo de pontos
                    if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.minDePontos) {
                        if (maxDePontos == 0 || jogada.getTotalNosDados() < maxDePontos) {
                            linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                        } else {
                            linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                        }
                    } else if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.maxDePontos) {
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
            break;
        case SECO:
            for (int i = 0; i <= 15; i++) {
                if (i == 6) {
                    i = 9;
                }
                if (linhas.get(i).getStatusDaLinha() == StatusDaLinha.LIVRE) {
                    // verifica mínimo e máximo de pontos
                    if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.minDePontos) {
                        if (jogada.getSeqJogada() == 1
                                && (maxDePontos == 0 || jogada.getTotalNosDados() < maxDePontos)) {
                            linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                        } else {
                            linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                        }
                    } else if (linhas.get(i).getTipoDeLinha() == TipoDeLinha.maxDePontos) {
                        if (jogada.getSeqJogada() == 1
                                && (minDePontos == 0 || jogada.getTotalNosDados() > minDePontos)) {
                            linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                        } else {
                            linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                        }
                    }
                    // verifica outras combinações
                    else if (jogada.getSeqJogada() == 1 && jogada.verificaCombinacao(linhas.get(i).getTipoDeLinha())) {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.MARCAVEL);
                    } else {
                        linhas.get(i).setStatusDaLinha(StatusDaLinha.RISCAVEL);
                    }
                }
            }
            break;
        }
    }

    public TipoDeColuna getTipoDeColuna() {
        return tipoDeColuna;
    }

    public void setTipoDeColuna(TipoDeColuna val) {
        this.tipoDeColuna = val;
    }

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> val) {
        this.linhas = val;
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
            if (linha.getTipoDeLinha() != TipoDeLinha.primeiroTotal && linha.getTipoDeLinha() != TipoDeLinha.bonus
                    && linha.getTipoDeLinha() != TipoDeLinha.segundoTotal
                    && linha.getTipoDeLinha() != TipoDeLinha.terceiroTotal
                    && linha.getTipoDeLinha() != TipoDeLinha.segundoEterceiroTotais) {

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
        linhas.get(TipoDeLinha.primeiroTotal.ordinal()).setPontos(temp1);

        // verifica se possui pontos suficientes para bonus
        if (temp1 >= 60) {
            linhas.get(TipoDeLinha.bonus.ordinal()).setPontos(30);
        } else {
            linhas.get(TipoDeLinha.bonus.ordinal()).setPontos(0);
        }

        temp1 += linhas.get(TipoDeLinha.bonus.ordinal()).getPontos();
        linhas.get(TipoDeLinha.segundoTotal.ordinal()).setPontos(temp1);

        for (int i = TipoDeLinha.QUADRA.ordinal(); i <= TipoDeLinha.YAM.ordinal(); i++) {
            temp2 += linhas.get(i).getPontos();
        }
        linhas.get(TipoDeLinha.terceiroTotal.ordinal()).setPontos(temp2);

        linhas.get(TipoDeLinha.segundoEterceiroTotais.ordinal()).setPontos(temp1 + temp2);
    }
}
