package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoSeqMaximaTest {

    @Test
    public void testCombinacaoSeqMaximaInvalida() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Combinacao combinacaoSeqMaxima = new CombinacaoSeqMaxima(jogada);
        
        assertThat(combinacaoSeqMaxima.valida(), is(false));
        assertThat(combinacaoSeqMaxima.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoSeqMaximaValida() {
        Jogada jogada = Jogada.of(2, 3, 4, 5, 6);
        Combinacao combinacaoSeqMaxima = new CombinacaoSeqMaxima(jogada);
        
        assertThat(combinacaoSeqMaxima.valida(), is(true));
        assertThat(combinacaoSeqMaxima.pontuacao(), is(60));
    }
}
