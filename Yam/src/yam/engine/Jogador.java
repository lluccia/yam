package yam.engine;



// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A8AC28DC-F7A3-A4D4-9B8C-7501939D61B9]
// </editor-fold> 
public class Jogador {

    private Cartela cartela;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8DFFEE97-316F-5CBD-862D-B3A072989E55]
    // </editor-fold> 
    private String nome;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2B0AC93D-E77E-3C28-614A-B339D8DA551A]
    // </editor-fold> 
    public Jogador (String nome) {
        this.setNome(nome);
        this.setCartela(new Cartela());
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3B18E6FC-8D20-9791-637E-9A4D6D30A2C1]
    // </editor-fold> 
    public String getNome () {
        return nome;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2507D0CB-34DC-1E1F-3E75-82815E3E709B]
    // </editor-fold> 
    public void setNome (String val) {
        this.nome = val;
    }

    public Cartela getCartela() {
        return cartela;
    }

    public void setCartela(Cartela cartela) {
        this.cartela = cartela;
    }
}

