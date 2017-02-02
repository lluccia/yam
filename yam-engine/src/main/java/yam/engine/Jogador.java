package yam.engine;

public class Jogador {

    private Cartela cartela;

    private String nome;

    public Jogador(String nome) {
        this.setNome(nome);
        this.setCartela(new Cartela());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String val) {
        this.nome = val;
    }

    public Cartela getCartela() {
        return cartela;
    }

    public void setCartela(Cartela cartela) {
        this.cartela = cartela;
    }

    public int getTotalDePontos() {
        return cartela.getTotalDePontos();
    }
}
