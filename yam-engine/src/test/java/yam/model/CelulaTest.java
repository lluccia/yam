package yam.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CelulaTest {

    private Combinacao combinacaoInvalida = new Combinacao() {
        @Override
        boolean valida() {
            return false;
        }

        @Override
        protected int pontuacaoValida() {
            return 0;
        }
    };
    
    private Combinacao combinacaoValida = new Combinacao() {
        @Override
        boolean valida() {
            return true;
        }
        
        @Override
        protected int pontuacaoValida() {
            return 66;
        }
    };

    private Celula celula;
    
    @Before
    public void setup() {
        celula = new Celula();
    }
    
    @Test
    public void novaCelulaDeveEstarVazia() {
        assertThat(celula.estaVazia(), is(true));
    }
    
    @Test
    public void naoPodeMarcarCombinacaoInvalida() {
        celula.setCombinacao(combinacaoInvalida);
        assertThat(celula.podeMarcar(), is(false));
    }
    
    @Test
    public void podeMarcarCombinacaoValida() {
        celula.setCombinacao(combinacaoValida);
        assertThat(celula.podeMarcar(), is(true));
    }
    
    @Test
    public void marcaPontosAtribuiValorDaCombinacaoSeValida() {
        celula.setCombinacao(combinacaoValida);
        celula.marcaPontos();
        
        assertThat(celula.getPontos(), is(66));
    }
    
    @Test
    public void marcaPontosAtribuiZeroSeCombinacaoInvalida() {
        celula.setCombinacao(combinacaoInvalida);
        celula.marcaPontos();
        
        assertThat(celula.getPontos(), is(0));
    }

    @Test
    public void aposMarcarPontosCelulaNaoDeveEstarVazia() {
        celula.setCombinacao(combinacaoValida);
        celula.marcaPontos();
        
        assertThat(celula.estaVazia(), is(false));
    }
    
    @Test
    public void naoPodeMarcarCelulaMarcada() {
        celula.setCombinacao(combinacaoValida);
        celula.marcaPontos();
        
        assertThat(celula.podeMarcar(), is(false));
    }
    
    @Test(expected=Celula.CelulaJaMarcadaException.class)
    public void marcarCelulaMarcadaDeveLancarExcecao() {
        celula.setCombinacao(combinacaoValida);
        celula.marcaPontos();
        celula.marcaPontos();
    }
    
}
