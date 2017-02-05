package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoQuadraTest {

    @Test
    public void testCombinacaoQuadraInvalida() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Combinacao combinacaoQuadra = new CombinacaoQuadra(jogada);
        
        assertThat(combinacaoQuadra.valida(), is(false));
        assertThat(combinacaoQuadra.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoQuadraValida() {
        Jogada jogada = Jogada.of(2, 2, 2, 2, 3);
        Combinacao combinacaoQuadra = new CombinacaoQuadra(jogada);
        
        assertThat(combinacaoQuadra.valida(), is(true));
        assertThat(combinacaoQuadra.pontuacao(), is(28));
        
        Jogada jogada2 = Jogada.of(1, 2, 2, 2, 2);
        Combinacao combinacaoQuadra2 = new CombinacaoQuadra(jogada2);
        
        assertThat(combinacaoQuadra2.valida(), is(true));
        assertThat(combinacaoQuadra2.pontuacao(), is(28));
    }
    
    @Test
    public void testCombinacaoQuadraValidaDesordenada() {
        Jogada jogada = Jogada.of(3, 2, 3, 3, 3);
        Combinacao combinacaoQuadra = new CombinacaoQuadra(jogada);
        
        assertThat(combinacaoQuadra.valida(), is(true));
        assertThat(combinacaoQuadra.pontuacao(), is(32));
    }
}
