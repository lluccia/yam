package yam.engine;

import java.util.ArrayList;
import java.util.Iterator;

public class Jogada {

    private ArrayList<Dado> dados;
   
    private int pontosCombUm;

    private int pontosCombDois;

    private int pontosCombTres;

    private int pontosCombQuatro;

    private int pontosCombCinco;

    private int pontosCombSeis;

    private int pontosCombQuadra;

    private int pontosCombFull;

    private int pontosCombSeqMinima;

    private int pontosCombSeqMaxima;

    private int pontosCombMinDePontos;
    
    private int pontosCombMaxDePontos;
    
    private int pontosCombYam;

    private int totalNosDados;
    
    private int seqJogada;
   
    private boolean combUm;

    private boolean combDois;

    private boolean combTres;

    private boolean combQuatro;

    private boolean combCinco;

    private boolean combSeis;

    private boolean combQuadra;

    private boolean combFull;

    private boolean combSeqMinima;

    private boolean combSeqMaxima;

    private boolean combYam;

    public Jogada ()  {
        dados=new ArrayList<Dado>(5);
        for (int i=0; i<5; i++) {
            dados.add(new Dado(i+1));
        }
        this.totalNosDados=15;
        setSeqJogada(0);
    }
        
    /** 
     * Joga os dados.
     * Caso todos os dados estejam marcados para segurar, não efetiva a jogada.
     */
    public boolean jogarDados () {
        //se todos os dados estiverem marcados, não efetua a jogada
        int tmpTotalNosDados=0;
        boolean todosMarcados=true;
        for (Dado dadoAtual: dados) {
            if (!dadoAtual.isMarcado()) {todosMarcados = false;}
            dadoAtual.jogar();
            tmpTotalNosDados+=dadoAtual.getValor();
        }
        if (todosMarcados) {
            return false;
        } else {
            setTotalNosDados(tmpTotalNosDados);
            setSeqJogada(getSeqJogada()+1);
            ordenarDados();
            verificarCombinacoes();
            return true;
        }
    }

    void desmarcarDados() {
        for (Dado d: dados) {
            d.desmarcar();
        }
    }

    int getQuantDadosLivres() {
        int qtd = 0;
        for (Dado d: dados) {
            if (!d.isMarcado()) {qtd++;}
        }
        return qtd;
    }
    
    /** 
     * Ordena os dados em ordem crescente
     */
    private void ordenarDados () {
        Dado dadoTmp;
        for (int i=dados.size()-1; i>= 0; i--) {
            for (int j=1; j<=i; j++) {
                if (getValorDado(j-1) > getValorDado(j)) {
                   dadoTmp = (Dado)dados.get(j-1);
                   dados.set(j-1, (Dado)dados.get(j));
                   dados.set(j,   (Dado)dadoTmp);
                }
            }
        }
    }
    
    /** 
     * Verifica quais combinações podem ser marcadas com o resultado dos dados.
     */
    private void verificarCombinacoes () {
        //limpa combinacoes
        setCombUm(false);
        setCombDois(false);
        setCombTres(false);
        setCombQuatro(false);
        setCombCinco(false);
        setCombSeis(false);
        setCombQuadra(false);
        setCombFull(false);
        setCombSeqMinima(false);
        setCombSeqMaxima(false);
        setCombYam(false);
        setPontosCombUm(0);
        setPontosCombDois(0);
        setPontosCombTres(0);
        setPontosCombQuatro(0);
        setPontosCombCinco(0);
        setPontosCombSeis(0);
        setPontosCombQuadra(0);
        setPontosCombFull(0);
        setPontosCombSeqMinima(0);
        setPontosCombSeqMaxima(0);
        setPontosCombYam(0);
        
        int tmpPontosCombUm=0;
        int tmpPontosCombDois=0;
        int tmpPontosCombTres=0;
        int tmpPontosCombQuatro=0;
        int tmpPontosCombCinco=0;
        int tmpPontosCombSeis=0;
        int tmpPontosCombQuadra=20; //
        int tmpPontosCombFull=30;
        int tmpPontosCombSeqMinima=35;
        int tmpPontosCombSeqMaxima=40;
        int tmpPontosCombYam=50;
        
        //verifica combUm a combSeis
        Iterator<Dado> itr = this.dados.iterator();
        while (itr.hasNext()) {
            Dado dadoAtual = itr.next();
            switch (dadoAtual.getValor()) {
                case 1:
                    setCombUm(true);
                    tmpPontosCombUm+=1;
                    break;
                case 2:
                    setCombDois(true);
                    tmpPontosCombDois+=2;
                    break;
                case 3:setCombTres(true);
                    tmpPontosCombTres+=3;
                    break;
                case 4:setCombQuatro(true);
                    tmpPontosCombQuatro+=4;
                    break;
                case 5:setCombCinco(true); 
                    tmpPontosCombCinco+=5;
                    break;
                case 6:setCombSeis(true); 
                    tmpPontosCombSeis+=6;
                    break;
            }
        }
        setPontosCombUm(tmpPontosCombUm);
        setPontosCombDois(tmpPontosCombDois);
        setPontosCombTres(tmpPontosCombTres);
        setPontosCombQuatro(tmpPontosCombQuatro);
        setPontosCombCinco(tmpPontosCombCinco);
        setPontosCombSeis(tmpPontosCombSeis);
        
        //verifica combQuadra
        if ( getValorDado(1) == getValorDado(2) &
             getValorDado(2) == getValorDado(3) ) {
            
            if (getValorDado(0) == getValorDado(1)) {
                tmpPontosCombQuadra = 4 * getValorDado(0);
                setCombQuadra(true);
                setPontosCombQuadra(tmpPontosCombQuadra + 40);
                
            }

            else if (getValorDado(3) == getValorDado(4)) {
                tmpPontosCombQuadra = 4 * getValorDado(4);
                setCombQuadra(true);
                setPontosCombQuadra(tmpPontosCombQuadra + 40);
            }
        }
        
        //verifica combFull
        if (
                (
                    getValorDado(0) == getValorDado(1) &
                    getValorDado(1) == getValorDado(2) &
                    getValorDado(2) != getValorDado(3) &
                    getValorDado(3) == getValorDado(4) 
                ) |
                (
                    getValorDado(0) == getValorDado(1) &
                    getValorDado(1) != getValorDado(2) &
                    getValorDado(2) == getValorDado(3) &
                    getValorDado(3) == getValorDado(4) 
                    
                ) 
           ) {
            setCombFull(true);
            tmpPontosCombFull+=getTotalNosDados();
            setPontosCombFull(tmpPontosCombFull);
        }
        
        //verifica combSeqMinima
        if (
                    getValorDado(0) == 1 &
                    getValorDado(1) == 2 &
                    getValorDado(2) == 3 &
                    getValorDado(3) == 4 &
                    getValorDado(4) == 5
           ) {
            setCombSeqMinima(true);
            tmpPontosCombSeqMinima+=getTotalNosDados();
            setPontosCombSeqMinima(tmpPontosCombSeqMinima);
        }
        
        //verifica combSeqMaxima
        if (
                    getValorDado(0) == 2 &
                    getValorDado(1) == 3 &
                    getValorDado(2) == 4 &
                    getValorDado(3) == 5 &
                    getValorDado(4) == 6
           )
        {
            setCombSeqMaxima(true);
            tmpPontosCombSeqMaxima+=getTotalNosDados();
            setPontosCombSeqMaxima(tmpPontosCombSeqMaxima);
        }
        
        //verifica combYam
        if (
                    getValorDado(0) == getValorDado(1) &
                    getValorDado(1) == getValorDado(2) &
                    getValorDado(2) == getValorDado(3) &
                    getValorDado(3) == getValorDado(4) 
           )
        {
            setCombYam(true);
            tmpPontosCombYam+=getTotalNosDados();
            setPontosCombYam(tmpPontosCombYam);
        }
	
	//sumariza pontos para mínimo e máximo de pontos
	setPontosCombMinDePontos(getTotalNosDados());
	setPontosCombMaxDePontos(getTotalNosDados());
    }
    
    /** 
     * Marca/desmarca o dado na posição desejada.
     * 
     * @param posição do dado a marcar/desmarcar.
     */
    public void marcarDado(int posicao) {
        this.dados.get(posicao).marcar();
    }
    
    /** 
     * Zera o número sequencial da jogada.
     */
    public void zeraSeqJogada () {
        this.setSeqJogada(0);
        for (Dado d: dados) {
            d.desmarcar();
        }
    }
    
    public boolean getCombCinco () {
        return combCinco;
    }

    private void setCombCinco (boolean val) {
        this.combCinco = val;
    }

    private void setCombDois (boolean val) {
        this.combDois = val;
    }

    public boolean getCombFull () {
        return combFull;
    }

    private void setCombFull (boolean val) {
        this.combFull = val;
    }

    public boolean getCombQuadra () {
        return combQuadra;
    }

    private void setCombQuadra (boolean val) {
        this.combQuadra = val;
    }

    public boolean getCombQuatro () {
        return combQuatro;
    }

    private void setCombQuatro (boolean val) {
        this.combQuatro = val;
    }

    public boolean getCombSeis () {
        return combSeis;
    }

    private void setCombSeis (boolean val) {
        this.combSeis = val;
    }

    public boolean getCombSeqMaxima () {
        return combSeqMaxima;
    }

    private void setCombSeqMaxima (boolean val) {
        this.combSeqMaxima = val;
    }

    public boolean getCombSeqMinima () {
        return combSeqMinima;
    }

    private void setCombSeqMinima (boolean val) {
        this.combSeqMinima = val;
    }

    public boolean getCombTres () {
        return combTres;
    }

    private void setCombTres (boolean val) {
        this.combTres = val;
    }

    public boolean getCombYam () {
        return combYam;
    }

    private void setCombYam (boolean val) {
        this.combYam = val;
    }

    public boolean getCombDois () {
        return combDois;
    }

    public boolean getCombUm () {
        return combUm;
    }

    private void setCombUm (boolean val) {
        this.combUm = val;
    }
    
    public int getTotalNosDados () {
        return totalNosDados;
    }

    private void setTotalNosDados (int val) {
        this.totalNosDados = val;
    }
   
    public int getSeqJogada() {
        return seqJogada;
    }

    private void setSeqJogada(int seqJogada) {
        this.seqJogada = seqJogada;
    }
    
    public int getPontosCombCinco() {
        return pontosCombCinco;
    }

    private void setPontosCombCinco(int pontosCombCinco) {
        this.pontosCombCinco = pontosCombCinco;
    }

    public int getPontosCombDois() {
        return pontosCombDois;
    }

    private void setPontosCombDois(int pontosCombDois) {
        this.pontosCombDois = pontosCombDois;
    }

    public int getPontosCombFull() {
        return pontosCombFull;
    }

    private void setPontosCombFull(int pontosCombFull) {
        this.pontosCombFull = pontosCombFull;
    }

    public int getPontosCombQuadra() {
        return pontosCombQuadra;
    }

    private void setPontosCombQuadra(int pontosCombQuadra) {
        this.pontosCombQuadra = pontosCombQuadra;
    }

    public int getPontosCombQuatro() {
        return pontosCombQuatro;
    }

    private void setPontosCombQuatro(int pontosCombQuatro) {
        this.pontosCombQuatro = pontosCombQuatro;
    }

    public int getPontosCombSeis() {
        return pontosCombSeis;
    }

    private void setPontosCombSeis(int pontosCombSeis) {
        this.pontosCombSeis = pontosCombSeis;
    }

    public int getPontosCombSeqMaxima() {
        return pontosCombSeqMaxima;
    }

    private void setPontosCombSeqMaxima(int pontosCombSeqMaxima) {
        this.pontosCombSeqMaxima = pontosCombSeqMaxima;
    }

    public int getPontosCombSeqMinima() {
        return pontosCombSeqMinima;
    }

    private void setPontosCombSeqMinima(int pontosCombSeqMinima) {
        this.pontosCombSeqMinima = pontosCombSeqMinima;
    }
    
    public int getPontosCombMinDePontos() {
        return pontosCombMinDePontos;
    }

    private void setPontosCombMinDePontos(int pontosCombMinDePontos) {
        this.pontosCombMinDePontos = pontosCombMinDePontos;
    }

    public int getPontosCombMaxDePontos() {
        return pontosCombMaxDePontos;
    }

    private void setPontosCombMaxDePontos(int pontosCombMaxDePontos) {
        this.pontosCombMaxDePontos = pontosCombMaxDePontos;
    }
    public int getPontosCombTres() {
        return pontosCombTres;
    }

    private void setPontosCombTres(int pontosCombTres) {
        this.pontosCombTres = pontosCombTres;
    }

    public int getPontosCombUm() {
        return pontosCombUm;
    }

    private void setPontosCombUm(int pontosCombUm) {
        this.pontosCombUm = pontosCombUm;
    }

    public int getPontosCombYam() {
        return pontosCombYam;
    }

    private void setPontosCombYam(int pontosCombYam) {
        this.pontosCombYam = pontosCombYam;
    }
    
    public int getValorDado(int dado) {
        return dados.get(dado).getValor();
    }
    
    public int[] getValoresDados() {
	int[] ret = new int[5];
	for (int i=0;i<5;i++){
	    ret[i]=this.dados.get(i).getValor();
	}
	return ret;
    }
    
    public boolean[] getMarcadosDados() {
	boolean[] ret = new boolean[5];
	for (int i=0;i<5;i++){
	    ret[i]=this.dados.get(i).isMarcado();
	}
	return ret;
    }
    
    public int getPontosComb(TipoDeLinha linha) {
        switch (linha) {
            case um:
                return pontosCombUm;
            case dois:
                return pontosCombDois;
            case tres:
                return pontosCombTres;
            case quatro:
                return pontosCombQuatro;
            case cinco:
                return pontosCombCinco;
            case seis:
                return pontosCombSeis;
            case quadra:
                return pontosCombQuadra;
            case full:
                return pontosCombFull;
            case seqMinima:
                return pontosCombSeqMinima;
            case seqMaxima:
                return pontosCombSeqMaxima;
            case minDePontos:
                return pontosCombMinDePontos;
            case maxDePontos:
                return pontosCombMaxDePontos;
            case yam:
                return pontosCombYam;
        }
        return 0;
    }
    
    public ArrayList<Dado> getDado() {
        return dados;
    }
    
    public String getJogada() {
	String retString="";
	for (Dado d: dados) {
	    retString=retString.concat(Integer.toString(d.getValor()));
	    retString=retString.concat(" ");
	}
	return retString;
    }
    
    public boolean verificaCombinacao(TipoDeLinha tpLinha) {
	switch (tpLinha) {
	    case um:
		return getCombUm();
	    case dois:
		return getCombDois();
	    case tres:
		return getCombTres();
	    case quatro:
		return getCombQuatro();
	    case cinco:
		return getCombCinco();
	    case seis:
		return getCombSeis();
	    case quadra:
		return getCombQuadra();
	    case full:
		return getCombFull();
	    case seqMinima:
		return getCombSeqMinima();
	    case seqMaxima:
		return getCombSeqMaxima();
	    case yam:
		return getCombYam();
	    default:
		return false;
	}
    }
    
    public int verificaPontosCombinacao(TipoDeLinha tpLinha) {
	switch (tpLinha) {
	    case um:
		return getPontosCombUm();
	    case dois:
		return getPontosCombDois();
	    case tres:
		return getPontosCombTres();
	    case quatro:
		return getPontosCombQuatro();
	    case cinco:
		return getPontosCombCinco();
	    case seis:
		return getPontosCombSeis();
	    case quadra:
		return getPontosCombQuadra();
	    case full:
		return getPontosCombFull();
	    case seqMinima:
		return getPontosCombSeqMinima();
	    case seqMaxima:
		return getPontosCombSeqMaxima();
	    case yam:
		return getPontosCombYam();
	    default:
		return 0;
	}
    }
}