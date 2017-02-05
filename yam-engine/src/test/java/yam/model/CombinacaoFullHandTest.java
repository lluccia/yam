package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoFullHandTest {

    @Test
    public void testCombinacaoFullHandInvalida() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Combinacao combinacaoFullHand = new CombinacaoFullHand(jogada);
        
        assertThat(combinacaoFullHand.valida(), is(false));
        assertThat(combinacaoFullHand.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoFullHandValida() {
        Jogada jogada = Jogada.of(2, 2, 2, 3, 3);
        Combinacao combinacaoFullHand = new CombinacaoFullHand(jogada);
        
        assertThat(combinacaoFullHand.valida(), is(true));
        assertThat(combinacaoFullHand.pontuacao(), is(42));
    }
    
    @Test
    public void testCombinacaoFullHandValidaDesordenada() {
        Jogada jogada = Jogada.of(2, 3, 2, 3, 2);
        Combinacao combinacaoFullHand = new CombinacaoFullHand(jogada);
        
        assertThat(combinacaoFullHand.valida(), is(true));
        assertThat(combinacaoFullHand.pontuacao(), is(42));
    }
}
