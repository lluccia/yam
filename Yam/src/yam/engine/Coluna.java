package yam.engine;

import java.util.ArrayList;
import java.util.EnumSet;

public class Coluna {

    private ArrayList<Linha> linhas;
    
    private TipoDeColuna tipoDeColuna;
   
    public Coluna (TipoDeColuna tipo) {
        this.setTipoDeColuna(tipo);
        this.linhas=new ArrayList<Linha>();
        for (TipoDeLinha tipoDeLinha: EnumSet.allOf(TipoDeLinha.class)) {
            linhas.add(new Linha(tipoDeLinha));
        }
    }
    
    /** 
     * Marca pontos na linha especificada.
     * Caso não seja possível marcar pontos na linha, ela será riscada se possível.
     * 
     * @param linha tipo da linha em que os pontos serão marcados.
     * @param pontos quantidade de pontos a ser marcado.
     * @return <b>true</b>: quando for possível marcar pontos ou riscar a linha <br/>
     * <b>false</b>: quando não for possível
     */
    public boolean marcaPontos (TipoDeLinha tpLinha, Jogada jog) {
        switch (linhas.get(tpLinha.ordinal()).getStatusDaLinha()) {
	    case marcavel:
		int pontos;
		if (tpLinha==TipoDeLinha.minDePontos | tpLinha==TipoDeLinha.maxDePontos) {
		    pontos=jog.getTotalNosDados();
		}
		else {
		    pontos=jog.getPontosComb(tpLinha);
		}
		linhas.get(tpLinha.ordinal()).setPontos(pontos);
		linhas.get(tpLinha.ordinal()).setStatusDaLinha(StatusDaLinha.marcada);
		sumarizaTotais();
		return true;
	    case riscavel:
		linhas.get(tpLinha.ordinal()).setStatusDaLinha(StatusDaLinha.riscada);
                return true;
	    default:
		return false;
	}
    }

    /** 
     * Verifica em quais linhas é possível marcar pontos ou riscar.
     * 
     * @param jogada jogada que será utilizada para análise das marcações possíveis.
     */
    public void verificarMarcacoes (Jogada jogada) {
	//limpa status das marcações possíveis anteriores
	for (Linha linha: linhas) {
	    if (linha.getStatusDaLinha() == StatusDaLinha.marcavel | 
		    linha.getStatusDaLinha() == StatusDaLinha.riscavel ) {
		linha.setStatusDaLinha(StatusDaLinha.livre);
	    }
	}
	
        switch (tipoDeColuna) {
	    case desce:			
		for (int i=0;i <= 15;i++) {
		    if (i==6) {i=9;}
		    if (linhas.get(i).getStatusDaLinha()==StatusDaLinha.livre) {
			if (jogada.verificaCombinacao(linhas.get(i).getTipoDeLinha())) {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.marcavel);
			}
			else {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.riscavel);
			}	    
			break;
		    }
		}
		break;
	    case sobe:
		for (int i=15;i >= 0;i++) {
		    if (i==8) {i=5;}
		    if (linhas.get(i).getStatusDaLinha()==StatusDaLinha.livre) {
			if (jogada.verificaCombinacao(linhas.get(i).getTipoDeLinha())) {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.marcavel);
			}
			else {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.riscavel);
			}
			break;
		    }
		}
		break;
	    case desordem:
		for (int i=0;i <= 15;i++) {
		    if (i==6) {i=9;}
		    if (linhas.get(i).getStatusDaLinha()==StatusDaLinha.livre) {
			if (jogada.verificaCombinacao(linhas.get(i).getTipoDeLinha())) {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.marcavel);
			}
			else {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.riscavel);
			}	    
		    }
		}
		break;
	    case seco:
		for (int i=0;i <= 15;i++) {
		    if (i==6) {i=9;}
		    if (linhas.get(i).getStatusDaLinha()==StatusDaLinha.livre) {
			if (jogada.getSeqJogada()==1 & jogada.verificaCombinacao(linhas.get(i).getTipoDeLinha())) {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.marcavel);
			}
			else {
			    linhas.get(i).setStatusDaLinha(StatusDaLinha.riscavel);
			}
		    }
		}
		break;
	}
    }
    
    public TipoDeColuna getTipoDeColuna () {
        return tipoDeColuna;
    }

    public void setTipoDeColuna (TipoDeColuna val) {
        this.tipoDeColuna = val;
    }
    
    public ArrayList<Linha> getLinhas () {
        return linhas;
    }

    public void setLinhas (ArrayList<Linha> val) {
        this.linhas = val;        
    }
    
    public boolean colunaCheia () {
        boolean cheia=true;
	
	for (Linha linha: linhas) {
	    if (linha.getStatusDaLinha() != StatusDaLinha.marcada &
		    linha.getStatusDaLinha() != StatusDaLinha.riscada ) {
                cheia=false;
                break;
            }
	}
	    
        return cheia;
    }
    
    public int getPontos(TipoDeLinha linha) {
        return linhas.get(linha.ordinal()).getPontos();    
    }
    
    public StatusDaLinha getStatus(TipoDeLinha linha) {
        return linhas.get(linha.ordinal()).getStatus();    
    }
    
    public void setPontos(TipoDeLinha linha,int pontos) {
        this.linhas.get(linha.ordinal()).setPontos(pontos);    
    }
    
    private void sumarizaTotais () {
        int temp1=0,temp2=0;
        for(int i=TipoDeLinha.um.ordinal();i<=TipoDeLinha.seis.ordinal();i++) {
            temp1+=linhas.get(i).getPontos();
        }
        linhas.get(TipoDeLinha.primeiroTotal.ordinal()).setPontos(temp1);
        
        //verifica se possui pontos suficientes para bonus
        if (temp1 >= 60) { 
            linhas.get(TipoDeLinha.bonus.ordinal()).setPontos(30);
        } 
        else {
            linhas.get(TipoDeLinha.bonus.ordinal()).setPontos(0);
        }
                
        temp1+=linhas.get(TipoDeLinha.bonus.ordinal()).getPontos();
        linhas.get(TipoDeLinha.segundoTotal.ordinal()).setPontos(temp1);
        
        for(int i=TipoDeLinha.quadra.ordinal();i<=TipoDeLinha.yam.ordinal();i++) {
            temp2+=linhas.get(i).getPontos();
        }
        linhas.get(TipoDeLinha.terceiroTotal.ordinal()).setPontos(temp2);

        linhas.get(TipoDeLinha.segundoEterceiroTotais.ordinal()).setPontos(temp1+temp2);
    }
}
