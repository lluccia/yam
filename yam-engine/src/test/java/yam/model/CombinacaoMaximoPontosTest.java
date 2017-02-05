package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoMaximoPontosTest {

    @Test
    public void testCombinacaoDeveSerValidaComMinimoNulo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer minimoDePontos = null;
        CombinacaoMaximoDePontos combinacaoMaximoDePontos = new CombinacaoMaximoDePontos(jogada, minimoDePontos);
        
        assertThat(combinacaoMaximoDePontos.valida(), is(true));
        assertThat(combinacaoMaximoDePontos.pontuacao(), is(15));
    }
    
    @Test
    public void testCombinacaoDeveSerValidaComTotalNosDadosMaiorQueMinimo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer minimoDePontos = jogada.getTotalNosDados() - 1;
        CombinacaoMaximoDePontos combinacaoMaximoDePontos = new CombinacaoMaximoDePontos(jogada, minimoDePontos);
        
        assertThat(combinacaoMaximoDePontos.valida(), is(true));
        assertThat(combinacaoMaximoDePontos.pontuacao(), is(15));
    }
    
    @Test
    public void testCombinacaoDeveSerInvalidaComTotalNosDadosIgualAoMinimo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer minimoDePontos = jogada.getTotalNosDados();
        CombinacaoMaximoDePontos combinacaoMaximoDePontos = new CombinacaoMaximoDePontos(jogada, minimoDePontos);
        
        assertThat(combinacaoMaximoDePontos.valida(), is(false));
        assertThat(combinacaoMaximoDePontos.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoDeveSerInvalidaComTotalNosDadosMenorQueMinimo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer minimoDePontos = jogada.getTotalNosDados() + 1;
        CombinacaoMaximoDePontos combinacaoMaximoDePontos = new CombinacaoMaximoDePontos(jogada, minimoDePontos);
        
        assertThat(combinacaoMaximoDePontos.valida(), is(false));
        assertThat(combinacaoMaximoDePontos.pontuacao(), is(0));
    }
}