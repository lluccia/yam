package yam.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JogoTest {

    private Jogo jogo;

    @Before
    public void setup() {
        jogo = new Jogo();
    }
    
    @Test
    public void testNovoJogoDeveEstarEmInicializacao() {
        assertThat(jogo.statusDoJogo, is(StatusDoJogo.INICIALIZACAO));
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
        assertThat(jogo.getJogadores(), is(new String[][]{
            {"jogador 1","0"}
        }));
        
        jogo.adicionarJogador("jogador 2");
        
        assertThat(jogo.getQuantJogadores(), is(2));
        assertThat(jogo.getJogadores(), is(new String[][]{
            {"jogador 1","0"},
            {"jogador 2","0"}
        }));
    }
    
    @Test
    public void testDevePermitirRemoverJogadores() {
        jogo.adicionarJogador("jogador 1");
        jogo.adicionarJogador("jogador 2");
        
        assertThat(jogo.getQuantJogadores(), is(2));
        assertThat(jogo.getJogadores(), is(new String[][]{
            {"jogador 1","0"},
            {"jogador 2","0"}
        }));
        
        jogo.removerJogador("jogador 1");
        
        assertThat(jogo.getQuantJogadores(), is(1));
        assertThat(jogo.getJogadores(), is(new String[][]{
            {"jogador 2","0"}
        }));
    }
    
    @Test(expected=Jogo.JogadorExistenteException.class)
    public void testNaoDevePermitirAdicionarJogadoresComMesmoNome() {
        jogo.adicionarJogador("jogador 1");
        jogo.adicionarJogador("jogador 1");
    }
}
