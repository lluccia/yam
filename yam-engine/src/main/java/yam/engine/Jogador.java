package yam.engine;

public class Jogador {

    private String nome;

    private Cartela cartela;

    public Jogador(String nome) {
        this.nome = nome;
        this.cartela = new Cartela();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jogador other = (Jogador) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}
