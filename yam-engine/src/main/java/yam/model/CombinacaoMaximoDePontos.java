package yam.model;

import yam.engine.Jogada;

public class CombinacaoMaximoDePontos extends Combinacao {

    private Integer minimoDePontos;

    public CombinacaoMaximoDePontos(Jogada jogada, Integer minimoDePontos) {
        super(jogada);
        this.minimoDePontos = minimoDePontos;
    }

    @Override
    boolean valida() {
        return minimoDePontos == null || jogada.getTotalNosDados() > minimoDePontos;
    }

    @Override
    protected int pontuacaoValida() {
        return jogada.getTotalNosDados();
    }

}
