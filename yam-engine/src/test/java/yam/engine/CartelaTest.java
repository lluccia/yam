package yam.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CartelaTest extends Cartela {
    
    private static final Jogada YAM_6 = Jogada.of(6, 6, 6, 6, 6);
    
    private Cartela cartela;
    
    @Before
    public void setUp() {
        cartela = new Cartela();
    }
    
    @Test
    public void cartelaDevePossuirQuatroColunas() {
        assertThat(cartela.getColunas().size(), is(4));
    }
    
    @Test
    public void totalDePontosDeveSerZero() {
        assertThat(cartela.getTotalDePontos(), is(0));
    }
    
    @Test
    public void cartelaNaoDeveEstarCheia() {
        assertThat(cartela.cartelaCheia(), is(false));
    }
    
    @Test
    public void cartelaIniciaComTodasCelulasLivresEZeroPontos() {
        assertThat(statusCartela(), is(
            "LLLLLLLLLLLLLLLLLLn " +
            "LLLLLLLLLLLLLLLLLLn " +
            "LLLLLLLLLLLLLLLLLLn " +
            "LLLLLLLLLLLLLLLLLLn "
        ));
        
        assertThat(cartela.getArrPontos(), is(new int[][] { 
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        }));
    }
    
    @Test
    public void verificaMarcacoes() {
        cartela.verificarMarcacoes(YAM_6);
        assertThat(statusCartela(), is(
            "rLLLLLLLLLLLLLLLLLn " +
            "LLLLLLLLLLLLLLLrLLn " +
            "rrrrrrLLLrrrrvvrLLn " +
            "rrrrrrLLLrrrrrrrLLn " 
        ));
    }
    
    /**
     * Status da cartela representado em String para validações nos testes
     * rotacionada para esquerda
     * 
     * L LIVRE
     * M MARCADA
     * - RISCADA
     * v MARCAVEL
     * r RISCAVEL
     */
    private String statusCartela() {
        StatusDaLinha[][] arrStatus = cartela.getArrStatus();
        String status = "";
        
        for (StatusDaLinha[] statusDaColuna: arrStatus) {
            for (StatusDaLinha statusDaLinha: statusDaColuna) {
                if (statusDaLinha == null) {
                    status += "n";
                } else {
                    switch (statusDaLinha) {
                    case LIVRE:
                        status += "L";
                        break;
                    case MARCADA:
                        status += "M";
                        break;
                    case RISCADA:
                        status += "-";
                        break;
                    case MARCAVEL:
                        status += "v";
                        break;
                    case RISCAVEL:
                        status += "r";
                        break;
                    default:
                        break;
                    }
                }
            }
            status += " ";
        }
        
        return status;
    }
}
