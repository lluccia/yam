package yam.engine;


import java.util.ArrayList; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A9FAD5C8-7A2D-7DB5-2BA8-5B555F269D84]
// </editor-fold> 
public class Cartela {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.04A35566-5E09-85A7-23CE-5E0A461E88BF]
    // </editor-fold> 
    private ArrayList<Coluna> coluna;

    private int totalDePontos;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.56946E82-7EA7-4615-5D5F-0465E07D6051]
    // </editor-fold> 
    public Cartela () {
        this.coluna=new ArrayList<Coluna>(4);
        this.coluna.add(new Coluna(TipoDeColuna.desce));
        this.coluna.add(new Coluna(TipoDeColuna.sobe));
        this.coluna.add(new Coluna(TipoDeColuna.desordem));
        this.coluna.add(new Coluna(TipoDeColuna.seco));
        this.setTotalDePontos(0);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4C884A24-7123-7966-845E-E33BFD7D417C]
    // </editor-fold> 
    public ArrayList<Coluna> getColuna () {
        return coluna;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.C7D6DCDB-51C1-0743-4FE3-243F64663316]
    // </editor-fold> 
    public void setColuna (ArrayList<Coluna> val) {
        this.coluna = val;
    }
    
    public int getTotalDePontos() {
        return totalDePontos;
    }

    public void setTotalDePontos(int totalDePontos) {
        this.totalDePontos = totalDePontos;
    }
    
}

