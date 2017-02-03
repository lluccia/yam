package yam.engine;

import java.util.Random;

public class Dado {

    private Random geradorRandomico;

    private int valor;

    private boolean segurado;

    public Dado() {
        geradorRandomico = new Random();
        segurado = false;
        valor = geradorRandomico.nextInt(6) + 1;
    }

    public Dado(int valor) {
        geradorRandomico = new Random();
        segurado = false;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void jogar() {
        if (!this.isMarcado()) {
            valor = geradorRandomico.nextInt(6) + 1;
        }
    }

    public void marcar() {
        segurado = !segurado;
    }

    public void desmarcar() {
        segurado = false;
    }

    public boolean isMarcado() {
        return segurado;
    }

}
