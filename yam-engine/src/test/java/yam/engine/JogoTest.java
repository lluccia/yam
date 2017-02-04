package yam.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class JogoTest {

    private Jogo jogo;

    private Object listOf(Object... elements) {
        return Arrays.asList(elements);
    }

    @Before
    public void setup() {
        jogo = new Jogo();
    }
    
    @Test
    public void testNovoJogoDeveEstarEmInicializacao() {
        assertThat(jogo.getStatus(), is(StatusDoJogo.INICIALIZACAO));
    }
    
    @Test
    public void testNovoJogoNaoDevePossuirJogadores() {
        assertThat(jogo.getQuantJogadores(), is(0));
    }
    
    @Test(expected=Jogo.InicioSemJogadoresException.class)
    public void testNaoDevePermitirInicioSemJogadores() {
        jogo.iniciarJogo();
    }
    
    @Test
    public void testDevePermitirAdicionarJogadores() {
        jogo.adicionarJogador("jogador 1");
        
        assertThat(jogo.getQuantJogadores(), is(1));
        assertThat(jogo.getJogadores(), is(listOf(
                new Jogador("jogador 1")
            ))
        );
        
        jogo.adicionarJogador("jogador 2");
        
        assertThat(jogo.getQuantJogadores(), is(2));
        assertThat(jogo.getJogadores(), is(listOf(
                new Jogador("jogador 1"),
                new Jogador("jogador 2")
            ))
        );
    }
    
    @Test
    public void testDevePermitirRemoverJogadores() {
        jogo.adicionarJogador("jogador 1");
        jogo.adicionarJogador("jogador 2");
        
        assertThat(jogo.getQuantJogadores(), is(2));
        assertThat(jogo.getJogadores(), is(listOf(
                new Jogador("jogador 1"),
                new Jogador("jogador 2")
            ))
        );
        
        jogo.removerJogador("jogador 1");
        
        assertThat(jogo.getQuantJogadores(), is(1));
        assertThat(jogo.getJogadores(), is(listOf(
                new Jogador("jogador 2")
            ))
        );
    }
    
    @Test(expected=Jogo.JogadorExistenteException.class)
    public void testNaoDevePermitirAdicionarJogadoresComMesmoNome() {
        jogo.adicionarJogador("jogador 1");
        jogo.adicionarJogador("jogador 1");
    }
}
