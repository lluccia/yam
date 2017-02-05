package yam.model;

import yam.engine.Jogada;

public class CombinacaoMinimoDePontos extends Combinacao {

    private Integer maximoDePontos;

    public CombinacaoMinimoDePontos(Jogada jogada, Integer maximoDePontos) {
        super(jogada);
        this.maximoDePontos = maximoDePontos;
    }

    @Override
    boolean valida() {
        return maximoDePontos == null || jogada.getTotalNosDados() < maximoDePontos;
    }

    @Override
    protected int pontuacaoValida() {
        return jogada.getTotalNosDados();
    }

}
