package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoSeqMinimaTest {

    @Test
    public void testCombinacaoSeqMinimaInvalida() {
        Jogada jogada = Jogada.of(2, 3, 4, 5, 6);
        Combinacao combinacaoSeqMinima = new CombinacaoSeqMinima(jogada);
        
        assertThat(combinacaoSeqMinima.valida(), is(false));
        assertThat(combinacaoSeqMinima.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoSeqMinimaValida() {
        Jogada jogada = Jogada.of(1, 2, 3, 4, 5);
        Combinacao combinacaoSeqMinima = new CombinacaoSeqMinima(jogada);
        
        assertThat(combinacaoSeqMinima.valida(), is(true));
        assertThat(combinacaoSeqMinima.pontuacao(), is(50));
    }
}
