package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import yam.engine.Jogada;

public class CombinacaoValorTest {

    @Test(expected=CombinacaoValor.ValorInvalidoException.class)
    public void testCombinacaoValorNaoPodeSerMenorQueUm() {
        Jogada jogada = Jogada.of(2, 3, 4, 5, 6);
        new CombinacaoValor(jogada, 0);
    }
    
    @Test(expected=CombinacaoValor.ValorInvalidoException.class)
    public void testCombinacaoValorNaoPodeSerMaiorQueSeis() {
        Jogada jogada = Jogada.of(2, 3, 4, 5, 6);
        new CombinacaoValor(jogada, 7);
    }
    
    @Test
    public void testCombinacaoValorInvalida() {
        Jogada jogada = Jogada.of(2, 3, 4, 5, 6);
        CombinacaoValor combinacaoValor = new CombinacaoValor(jogada, 1);
        
        assertThat(combinacaoValor.valida(), is(false));
        assertThat(combinacaoValor.pontuacao(), is(0));
    }
    
    @Test
    public void testCombinacaoValorValida() {
        Jogada jogada = Jogada.of(2, 3, 4, 5, 6);
        CombinacaoValor combinacaoValor = new CombinacaoValor(jogada, 2);
        
        assertThat(combinacaoValor.valida(), is(true));
        assertThat(combinacaoValor.pontuacao(), is(2));

        Jogada jogada2 = Jogada.of(2, 3, 4, 2, 6);
        CombinacaoValor combinacaoValor2 = new CombinacaoValor(jogada2, 2);
        
        assertThat(combinacaoValor2.valida(), is(true));
        assertThat(combinacaoValor2.pontuacao(), is(4));
    }

}
