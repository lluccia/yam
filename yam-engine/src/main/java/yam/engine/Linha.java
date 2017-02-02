package yam.engine;

public class Linha {

    private TipoDeLinha tipoDeLinha;

    private StatusDaLinha statusDaLinha;

    private int pontos;

    public Linha(TipoDeLinha tipo) {
        this.setTipoDeLinha(tipo);
        this.setStatusDaLinha(StatusDaLinha.LIVRE);
        this.setPontos(0);
    }

    public StatusDaLinha getStatusDaLinha() {
        return statusDaLinha;
    }

    public void setStatusDaLinha(StatusDaLinha statusDaLinha) {
        this.statusDaLinha = statusDaLinha;
    }

    public int getPontos() {
        return pontos;
    }

    public StatusDaLinha getStatus() {
        return statusDaLinha;
    }

    public void setStatus(StatusDaLinha stat) {
        this.statusDaLinha = stat;
    }

    public void setPontos(int val) {
        this.pontos = val;
    }

    public TipoDeLinha getTipoDeLinha() {
        return tipoDeLinha;
    }

    public void setTipoDeLinha(TipoDeLinha val) {
        this.tipoDeLinha = val;
    }
}