package yam.engine;


import java.util.Random;

public class Dado {

    private Random geradorRandomico;

    private int valor;

    private boolean marcado;

    public Dado () {
        geradorRandomico = new Random();
        this.setMarcado(false);
        this.setValor(geradorRandomico.nextInt(6)+1);
    }
        
    public int getValor () {
        return valor;
    }

    public void jogar () {
        if ( !this.getMarcado() ){
            this.setValor(geradorRandomico.nextInt(6)+1);
        }
    }

    public void marcar () {
        this.setMarcado( !this.getMarcado() );
    }

    private void setMarcado (boolean val) {
        this.marcado = val;
    }

    private void setValor (int val) {
        this.valor = val;
    }

    public boolean getMarcado () {
        return marcado;
    }

}

