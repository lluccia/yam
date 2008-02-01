package yam.engine;


import java.util.Random;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F357EC57-DB71-444D-5923-F44D4F79B412]
// </editor-fold> 
public class Dado implements Cloneable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.204E5959-824B-6580-A084-423AA74A7C5F]
    // </editor-fold> 
    private Random geradorRandomico;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.606993AF-CAF3-2E7E-8418-38AADF2CFAE7]
    // </editor-fold> 
    private int valor;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1045C251-0C0E-D1F0-11A7-4A75BE56B165]
    // </editor-fold> 
    private boolean marcado;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1692897A-8AAF-A9C9-CC3F-04260851A0A3]
    // </editor-fold> 
    public Dado () {
        geradorRandomico = new Random();
        this.setMarcado(false);
        this.setValor(geradorRandomico.nextInt(6)+1);
    }
    
    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.265EADB1-A8E3-E529-2229-C663F04CE732]
    // </editor-fold> 
    public int getValor () {
        return valor;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8A6F4EAA-F757-A410-CD25-93E6D90F9E7E]
    // </editor-fold> 
    public void jogar () {
        if ( !this.getMarcado() ){
            this.setValor(geradorRandomico.nextInt(6)+1);
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EDE0914D-C6FF-C480-7D4D-17B13169B805]
    // </editor-fold> 
    public void marcar () {
        this.setMarcado( !this.getMarcado() );
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A5E0793F-EDE3-BC5D-2533-C9005E95413E]
    // </editor-fold> 
    private void setMarcado (boolean val) {
        this.marcado = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7FB8A1C3-C563-1145-89EA-93ECF7985C65]
    // </editor-fold> 
    //private void setValor (int val) {
    public void setValor (int val) {
        this.valor = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2CB930AC-061F-6BEF-9EE5-A5D1ED0F07E1]
    // </editor-fold> 
    public boolean getMarcado () {
        return marcado;
    }

}

