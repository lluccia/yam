package yam.engine;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import yam.model.ColunaAscendente;
import yam.model.ColunaDescendente;
import yam.model.ColunaDesordem;
import yam.model.ColunaSeco;

public class CartelaTest extends Cartela {
    
    private static final Jogada YAM_1 = Jogada.of(1, 1, 1, 1, 1);
    private static final Jogada YAM_2 = Jogada.of(2, 2, 2, 2, 2);
    private static final Jogada YAM_3 = Jogada.of(3, 3, 3, 3, 3);
    private static final Jogada YAM_4 = Jogada.of(4, 4, 4, 4, 4);
    private static final Jogada YAM_5 = Jogada.of(5, 5, 5, 5, 5);
    private static final Jogada YAM_6 = Jogada.of(6, 6, 6, 6, 6);

    private static final Jogada FULL_1_2 = Jogada.of(1, 1, 1, 2, 2);;
    private static final Jogada SEQ_MIN = Jogada.of(1, 2, 3, 4, 5);
    private static final Jogada SEQ_MAX = Jogada.of(2, 3, 4, 5, 6);
    
    private Cartela cartela;
    
    @Before
    public void setUp() {
        cartela = new Cartela();
    }
    
    @Test
    public void cartelaDevePossuirQuatroColunas() {
        List<Coluna> colunas = cartela.getColunas();
        
        assertThat(colunas.size(), is(4));
        assertThat(colunas.get(0), instanceOf(ColunaDescendente.class));
        assertThat(colunas.get(1), instanceOf(ColunaAscendente.class));
        assertThat(colunas.get(2), instanceOf(ColunaDesordem.class));
        assertThat(colunas.get(3), instanceOf(ColunaSeco.class));
        
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
                " , , , , , , , , , , , , , , , , , ,n," +
                " , , , , , , , , , , , , , , , , , ,n," +
                " , , , , , , , , , , , , , , , , , ,n," +
                " , , , , , , , , , , , , , , , , , ,n,"
        ));
        
        assertThat(pontosCartela(), is(
            " , , , , , , , , , , , , , , , , , , ," +
            " , , , , , , , , , , , , , , , , , , ," +
            " , , , , , , , , , , , , , , , , , , ," +
            " , , , , , , , , , , , , , , , , , , ,"
        ));
    }
    
    @Test
    public void verificaMarcacoesPrimeiraJogada() {
        cartela.verificarMarcacoes(YAM_1);
        assertThat(statusCartela(), is(
                "+,o,o,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                "+,o,o,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "+, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(YAM_2);
        assertThat(statusCartela(), is(
                "o,+,o,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                "o,+,o,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "o, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(YAM_3);
        assertThat(statusCartela(), is(
                "o,o,+,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                "o,o,+,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "o, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(YAM_4);
        assertThat(statusCartela(), is(
                "o,o,o,+,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                "o,o,o,+,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "o, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(YAM_5);
        assertThat(statusCartela(), is(
                "o,o,o,o,+,o, , , ,+,o,o,o,+,+,+, , ,n," +
                "o,o,o,o,+,o, , , ,+,o,o,o,+,+,+, , ,n," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "o, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(YAM_6);
        assertThat(statusCartela(), is(
                "o,o,o,o,o,+, , , ,+,o,o,o,+,+,+, , ,n," +
                "o,o,o,o,o,+, , , ,+,o,o,o,+,+,+, , ,n," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "o, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(FULL_1_2);
        assertThat(statusCartela(), is(
                "+,+,o,o,o,o, , , ,o,+,o,o,+,+,o, , ,n," +
                "+,+,o,o,o,o, , , ,o,+,o,o,+,+,o, , ,n," +
                " , , , , , , , , , , , , , , ,o, , ,n," +
                "+, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(SEQ_MIN);
        assertThat(statusCartela(), is(
                "+,+,+,+,+,o, , , ,o,o,+,o,+,+,o, , ,n," +
                "+,+,+,+,+,o, , , ,o,o,+,o,+,+,o, , ,n," +
                " , , , , , , , , , , , , , , ,o, , ,n," +
                "+, , , , , , , , , , , , , , , , , ,n,"
        ));
        
        cartela.verificarMarcacoes(SEQ_MAX);
        assertThat(statusCartela(), is(
                "o,+,+,+,+,+, , , ,o,o,o,+,+,+,o, , ,n," +
                "o,+,+,+,+,+, , , ,o,o,o,+,+,+,o, , ,n," +
                " , , , , , , , , , , , , , , ,o, , ,n," +
                "o, , , , , , , , , , , , , , , , , ,n,"
        ));
    }
    
    @Test
    public void marcaPontos() {
        cartela.verificarMarcacoes(YAM_1);
        cartela.marcaPontos(TipoDeColuna.DESCE, TipoDeLinha.UM, YAM_1);
        
        assertThat(statusCartela(), is(
                "+,o,o,o,o,o, , , ,+,o,o,o,+,+,+, , ,n," +
                "+,o,o,o,o,o, , , ,+,o,o,o,+,+,+, , ,#," +
                " , , , , , , , , , , , , , , ,+, , ,n," +
                "#, , , , , ,#, ,#, , , , , , , , ,#,n,"
        ));
        
        assertThat(pontosCartela(), is(
                " , , , , , , , , , , , , , , , , , , ," +
                " , , , , , , , , , , , , , , , , , ,5," +
                " , , , , , , , , , , , , , , , , , , ," +
                "5, , , , , ,5, ,5, , , , , , , , ,5, ,"
        ));
    }

    private String pontosCartela() {
        int[][] arrPontos = cartela.getArrPontos();
        String pontos = "";

        for (int i = arrPontos.length-1 ; i >= 0; i--) {           
            int[] pontosDaColuna = arrPontos[i];
            for (int pontosDaLinha: pontosDaColuna) {
                if (pontosDaLinha == 0)
                    pontos += " ";
                else
                    pontos += pontosDaLinha;
                pontos += ",";
            }
        }
        return pontos;
    }
    
    /**
     * Status da cartela representado em String para validações nos testes.
     * 
     *   LIVRE
     * # MARCADA
     * - RISCADA
     * + MARCAVEL
     * o RISCAVEL
     */
    private String statusCartela() {
        StatusDaLinha[][] arrStatus = cartela.getArrStatus();
        String status = "";

        for (int i = arrStatus.length-1 ; i >= 0; i--) {           
            StatusDaLinha[] statusDaColuna = arrStatus[i];
            for (StatusDaLinha statusDaLinha: statusDaColuna) {
                if (statusDaLinha == null) {
                    status += "n,";
                } else {
                    switch (statusDaLinha) {
                    case LIVRE:
                        status += " ,";
                        break;
                    case MARCADA:
                        status += "#,";
                        break;
                    case RISCADA:
                        status += "-,";
                        break;
                    case MARCAVEL:
                        status += "+,";
                        break;
                    case RISCAVEL:
                        status += "o,";
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        
        return status;
    }
}
