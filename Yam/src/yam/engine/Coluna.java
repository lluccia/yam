package yam.engine;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

////////////// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
////////////// #[regen=yes,id=DCE.C7291326-29DF-5DE4-E24B-7B9C13E994FE]
////////////// </editor-fold> 
public class Coluna {

    private ArrayList<Linha> linhas;
    
    private TipoDeColuna tipoDeColuna;
   
    public Coluna (TipoDeColuna tipo) {
        this.setTipoDeColuna(tipo);
        this.linhas=new ArrayList<Linha>(16);
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
    public boolean marcaPontos (TipoDeLinha linha,int pontos) {
        if ( this.linhas.get(linha.ordinal()).getMarcavel() ) { 
            this.linhas.get(linha.ordinal()).setPontos(pontos);
            this.linhas.get(linha.ordinal()).setMarcada(true);
            this.sumarizaTotais();
            return true;
        }
        else { 
            if (this.linhas.get(linha.ordinal()).getRiscavel()) {
                this.linhas.get(linha.ordinal()).setRiscada(true);
                return true;
            }
            else { 
                return false; 
            }
        }
    }

    /** 
     * Verifica em quais linhas é possível marcar pontos ou riscar.
     * 
     * @param jogada jogada que será utilizada para análise das marcações possíveis.
     */
    public void verificarMarcacoes (Jogada jogada) {
        //inicializa variáveis (obs.: se a coluna for do tipo Seco, sempre pode riscar)
        Iterator<Linha> itr = this.linhas.iterator();
        while (itr.hasNext()) {
            Linha linhaAtual = itr.next();
            linhaAtual.setMarcavel(false);            
            if (this.tipoDeColuna.equals(TipoDeColuna.seco)) {
                linhaAtual.setRiscavel(true);
            }
            else {
                linhaAtual.setRiscavel(false);
            }
        }
        
        if (jogada.getCombUm()) {
            switch (this.getTipoDeColuna()) {
                case desce:
                    if ( linhas.get(TipoDeLinha.um.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.um.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.um.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.dois.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.um.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.um.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.um.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.um.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.um.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombDois()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.dois.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.um.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.dois.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.dois.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.tres.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.dois.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.dois.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.dois.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.dois.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.dois.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombTres()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.tres.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.dois.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.tres.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.tres.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.quatro.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.tres.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.tres.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.tres.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.tres.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.tres.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombQuatro()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.quatro.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.tres.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.quatro.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.quatro.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.cinco.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.quatro.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.quatro.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.quatro.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.quatro.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.quatro.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombCinco()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.cinco.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.quatro.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.cinco.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.cinco.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.seis.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.cinco.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.cinco.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.cinco.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.cinco.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.cinco.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombSeis()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.seis.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.cinco.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.seis.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.seis.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.quadra.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.seis.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.seis.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.seis.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.seis.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.seis.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombQuadra()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.quadra.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.seis.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.quadra.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.quadra.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.full.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.quadra.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.quadra.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.quadra.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.quadra.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.quadra.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombFull()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.full.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.quadra.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.full.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.full.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.seqMinima.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.full.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.full.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.full.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.full.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.full.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombSeqMinima()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.seqMinima.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.full.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.seqMinima.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.seqMinima.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.seqMaxima.ordinal()).getMarcada()==true  )
                        linhas.get(TipoDeLinha.seqMinima.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.seqMinima.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.seqMinima.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.seqMinima.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.seqMinima.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombSeqMaxima()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.seqMaxima.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.seqMinima.ordinal()).getMarcada()==true )
                        linhas.get(TipoDeLinha.seqMaxima.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.seqMaxima.ordinal()).getMarcada()==false & 
                          linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==true )
                        linhas.get(TipoDeLinha.seqMaxima.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.seqMaxima.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.seqMaxima.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.seqMaxima.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.seqMaxima.ordinal()).setMarcavel(true);
                    break;
            }
        }
        if (jogada.getCombYam()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.yam.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==true )
                        linhas.get(TipoDeLinha.yam.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.yam.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.yam.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.yam.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.yam.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.yam.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.yam.ordinal()).setMarcavel(true);
                    break;
            }
        }
        
        //verifica mínimo de pontos
        if (linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==false |
                jogada.getTotalNosDados() <  linhas.get(TipoDeLinha.maxDePontos.ordinal()).getPontos()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.seqMaxima.ordinal()).getMarcada()==true )
                        linhas.get(TipoDeLinha.minDePontos.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.minDePontos.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.minDePontos.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.minDePontos.ordinal()).setMarcavel(true);
                    break;
            }
        }
        
        //verifica maximo de pontos
        if (linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==false |
                jogada.getTotalNosDados() >  linhas.get(TipoDeLinha.minDePontos.ordinal()).getPontos()) {
            switch (this.getTipoDeColuna()) {
                 case desce:
                    if ( linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.minDePontos.ordinal()).getMarcada()==true )
                        linhas.get(TipoDeLinha.maxDePontos.ordinal()).setMarcavel(true);
                    break;
                case sobe:
                    if ( linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==false &
                            linhas.get(TipoDeLinha.yam.ordinal()).getMarcada()==true)
                        linhas.get(TipoDeLinha.maxDePontos.ordinal()).setMarcavel(true);
                    break;
                case desordem:
                    if ( linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==false )
                        linhas.get(TipoDeLinha.maxDePontos.ordinal()).setMarcavel(true);
                    break;
                case seco:
                    if ( linhas.get(TipoDeLinha.maxDePontos.ordinal()).getMarcada()==false & 
                            jogada.getSeqJogada()==1)
                        linhas.get(TipoDeLinha.maxDePontos.ordinal()).setMarcavel(true);
                    break;
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BCFA8715-96D6-E0CA-59D9-BA906D12228E]
    // </editor-fold> 
    public TipoDeColuna getTipoDeColuna () {
        return tipoDeColuna;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B0561E6C-84E1-3F9A-5C11-78FE742C7B2A]
    // </editor-fold> 
    public void setTipoDeColuna (TipoDeColuna val) {
        this.tipoDeColuna = val;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F26779FA-2545-51EF-A34A-4F550E3C9BA7]
    // </editor-fold> 
    public ArrayList<Linha> getLinhas () {
        return linhas;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.01A3F129-E41A-B761-8206-48A44B34B845]
    // </editor-fold> 
    public void setLinhas (ArrayList<Linha> val) {
        this.linhas = val;        
    }
    
    public boolean colunaCheia () {
        //inicializa variáveis (obs.: se a coluna for do tipo Seco, sempre pode riscar)
        Iterator<Linha> itr = this.linhas.iterator();
        
        boolean cheia=true;
        
        while (itr.hasNext()) {
            Linha linhaAtual = itr.next();
            if (!linhaAtual.getMarcada() & !linhaAtual.getRiscada()) {
                cheia=false;
                break;
            }
        }
        
        return cheia;
    }
    
    public int getPontos(TipoDeLinha linha) {
        return linhas.get(linha.ordinal()).getPontos();    
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
