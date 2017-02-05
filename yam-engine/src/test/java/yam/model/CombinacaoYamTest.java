package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoYamTest {

    @Test
    public void testCombinacaoYamInvalida() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        CombinacaoYam combinacaoYam = new CombinacaoYam(jogada);
        
        assertThat(combinacaoYam.valida(), is(false));
        assertThat(combinacaoYam.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoYamValida() {
        Jogada jogada = Jogada.of(1, 1, 1, 1, 1);
        CombinacaoYam combinacaoYam = new CombinacaoYam(jogada);
        
        assertThat(combinacaoYam.valida(), is(true));
        assertThat(combinacaoYam.pontuacao(), is(55));
        
        Jogada jogada2 = Jogada.of(6, 6, 6, 6, 6);
        CombinacaoYam combinacaoYam2 = new CombinacaoYam(jogada2);
        
        assertThat(combinacaoYam2.valida(), is(true));
        assertThat(combinacaoYam2.pontuacao(), is(80));
    }

}
