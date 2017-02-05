package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoMinimoPontosTest {

    @Test
    public void testCombinacaoDeveSerValidaComMaximoNulo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer maximoDePontos = null;
        CombinacaoMinimoDePontos combinacaoMinimoDePontos = new CombinacaoMinimoDePontos(jogada, maximoDePontos);
        
        assertThat(combinacaoMinimoDePontos.valida(), is(true));
        assertThat(combinacaoMinimoDePontos.pontuacao(), is(15));
    }
    
    @Test
    public void testCombinacaoDeveSerValidaComTotalNosDadosMenorQueMaximo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer maximoDePontos = jogada.getTotalNosDados() + 1;
        CombinacaoMinimoDePontos combinacaoMinimoDePontos = new CombinacaoMinimoDePontos(jogada, maximoDePontos);
        
        assertThat(combinacaoMinimoDePontos.valida(), is(true));
        assertThat(combinacaoMinimoDePontos.pontuacao(), is(15));
    }
    
    @Test
    public void testCombinacaoDeveSerInvalidaComTotalNosDadosIgualAoMaximo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer maximoDePontos = jogada.getTotalNosDados();
        CombinacaoMinimoDePontos combinacaoMinimoDePontos = new CombinacaoMinimoDePontos(jogada, maximoDePontos);
        
        assertThat(combinacaoMinimoDePontos.valida(), is(false));
        assertThat(combinacaoMinimoDePontos.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoDeveSerInvalidaComTotalNosDadosMaiorQueMaximo() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Integer maximoDePontos = jogada.getTotalNosDados() - 1;
        CombinacaoMinimoDePontos combinacaoMinimoDePontos = new CombinacaoMinimoDePontos(jogada, maximoDePontos);
        
        assertThat(combinacaoMinimoDePontos.valida(), is(false));
        assertThat(combinacaoMinimoDePontos.pontuacao(), is(0));
    }
}