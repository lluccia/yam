package yam.engine;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A9FAD5C8-7A2D-7DB5-2BA8-5B555F269D84]
// </editor-fold> 
public class Cartela {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.04A35566-5E09-85A7-23CE-5E0A461E88BF]
    // </editor-fold> 
    private ArrayList<Coluna> colunas;

    private int totalDePontos;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.56946E82-7EA7-4615-5D5F-0465E07D6051]
    // </editor-fold> 
    public Cartela () {
        this.colunas=new ArrayList<Coluna>(4);
        this.colunas.add(new Coluna(TipoDeColuna.desce));
        this.colunas.add(new Coluna(TipoDeColuna.sobe));
        this.colunas.add(new Coluna(TipoDeColuna.desordem));
        this.colunas.add(new Coluna(TipoDeColuna.seco));
        this.setTotalDePontos(0);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4C884A24-7123-7966-845E-E33BFD7D417C]
    // </editor-fold> 
    public ArrayList<Coluna> getColunas () {
        return colunas;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C7D6DCDB-51C1-0743-4FE3-243F64663316]
    // </editor-fold> 
    public void setColunas (ArrayList<Coluna> val) {
        this.colunas = val;
    }
    
    public int getTotalDePontos() {
        return totalDePontos;
    }

    public void setTotalDePontos(int totalDePontos) {
        this.totalDePontos = totalDePontos;
    }
    
    public int getPontos(TipoDeColuna coluna,TipoDeLinha linha) {
        return colunas.get(coluna.ordinal()).getPontos(linha);
    }
    
    public void setPontos(TipoDeColuna coluna,TipoDeLinha linha,int pontos) {
        this.colunas.get(coluna.ordinal()).setPontos(linha,pontos);
    }

    public boolean marcaPontos (TipoDeColuna coluna, TipoDeLinha linha,int pontos) {
        if ( colunas.get(coluna.ordinal()).marcaPontos(linha, pontos) ) { 
            sumarizaTotais();
            return true;
        }
        else { 
            return false; 
        }
    }
    
     public void sumarizaTotais () {
        int temp=0;
        
        for (TipoDeColuna col: TipoDeColuna.values()) {
            temp += getPontos(col,TipoDeLinha.segundoEterceiroTotais);
        }
        
        this.setTotalDePontos(temp);
    }
    
    public boolean cartelaCheia() {
        boolean cheia=true;
        
        for (TipoDeColuna col: TipoDeColuna.values()) {
            if (!colunas.get(col.ordinal()).colunaCheia()) {
                cheia=false;
                break;
            }
        }
       
        return cheia;
    }
    
    public int[][] getArrPontos() {
        int[][] retArray = new int[colunas.size()][colunas.get(0).getLinhas().size()];
        
        for (TipoDeColuna col: TipoDeColuna.values()) {
            for (TipoDeLinha lin: TipoDeLinha.values()) {
                retArray[col.ordinal()][lin.ordinal()]=getPontos(col,lin);
            }
        }
       
        return retArray;
    }
    
    public boolean[][] getArrMarcaveis() {
        boolean[][] retArray = new boolean[colunas.size()][colunas.get(0).getLinhas().size()];
        
        Iterator<Coluna> itrColunas = colunas.iterator();
        
        int intColunaAtual=0;
        
        while (itrColunas.hasNext()) {
            Coluna colunaAtual = itrColunas.next();
            int intLinhaAtual=0;
            
            Iterator<Linha> itrLinhas = colunaAtual.getLinhas().iterator();
            while (itrLinhas.hasNext()) {
                Linha linhaAtual = itrLinhas.next();
                
                retArray[intColunaAtual][intLinhaAtual]=linhaAtual.getMarcavel();
                intLinhaAtual+=1;
            }
            intColunaAtual+=1;
        }
        
        return retArray;
    }
    
    public boolean[][] getArrMarcadas() {
        boolean[][] retArray = new boolean[colunas.size()][colunas.get(0).getLinhas().size()];
        
        Iterator<Coluna> itrColunas = colunas.iterator();
        
        int intColunaAtual=0;
        
        while (itrColunas.hasNext()) {
            Coluna colunaAtual = itrColunas.next();
            int intLinhaAtual=0;
            
            Iterator<Linha> itrLinhas = colunaAtual.getLinhas().iterator();
            while (itrLinhas.hasNext()) {
                Linha linhaAtual = itrLinhas.next();
                
                retArray[intColunaAtual][intLinhaAtual]=linhaAtual.getMarcada();
                intLinhaAtual+=1;
            }
            intColunaAtual+=1;
        }
        
        return retArray;
    }
    
    public boolean[][] getArrRiscaveis() {
        boolean[][] retArray = new boolean[colunas.size()][colunas.get(0).getLinhas().size()];
        
        Iterator<Coluna> itrColunas = colunas.iterator();
        
        int intColunaAtual=0;
        
        while (itrColunas.hasNext()) {
            Coluna colunaAtual = itrColunas.next();
            int intLinhaAtual=0;
            
            Iterator<Linha> itrLinhas = colunaAtual.getLinhas().iterator();
            while (itrLinhas.hasNext()) {
                Linha linhaAtual = itrLinhas.next();
                
                retArray[intColunaAtual][intLinhaAtual]=linhaAtual.getRiscavel();
                intLinhaAtual+=1;
            }
            intColunaAtual+=1;
        }
        
        return retArray;
    }
    
    public boolean[][] getArrRiscadas() {
        boolean[][] retArray = new boolean[colunas.size()][colunas.get(0).getLinhas().size()];
        
        Iterator<Coluna> itrColunas = colunas.iterator();
        
        int intColunaAtual=0;
        
        while (itrColunas.hasNext()) {
            Coluna colunaAtual = itrColunas.next();
            int intLinhaAtual=0;
            
            Iterator<Linha> itrLinhas = colunaAtual.getLinhas().iterator();
            while (itrLinhas.hasNext()) {
                Linha linhaAtual = itrLinhas.next();
                
                retArray[intColunaAtual][intLinhaAtual]=linhaAtual.getRiscada();
                intLinhaAtual+=1;
            }
            intColunaAtual+=1;
        }
        
        return retArray;
    }
    
    // * método utilizado para teste
    public void randomFillPontos() {
        
        Random geradorRandomico = new Random();
        
        Iterator<Coluna> itrColunas = colunas.iterator();
        
        while (itrColunas.hasNext()) {
            Coluna colunaAtual = itrColunas.next();
            Iterator<Linha> itrLinhas = colunaAtual.getLinhas().iterator();
            while (itrLinhas.hasNext()) {
                Linha linhaAtual = itrLinhas.next();
                linhaAtual.setMarcavel(true);
            }
        }
        
        itrColunas = colunas.iterator();
        
        while (itrColunas.hasNext()) {
            Coluna colunaAtual = itrColunas.next();
            Iterator<Linha> itrLinhas = colunaAtual.getLinhas().iterator();
            while (itrLinhas.hasNext()) {
                Linha linhaAtual = itrLinhas.next();
                marcaPontos(colunaAtual.getTipoDeColuna(),linhaAtual.getTipoDeLinha(),geradorRandomico.nextInt(6));
            }
        }
    }
}

