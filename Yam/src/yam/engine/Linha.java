package yam.engine;



public class Linha {
	    
    @Deprecated
    private boolean riscada;
    
    @Deprecated
    private boolean riscavel;

    private TipoDeLinha tipoDeLinha;

    private StatusDaLinha statusDaLinha;
	    
    private int pontos;

    @Deprecated
    private boolean marcada;

    @Deprecated
    private boolean marcavel;

    public StatusDaLinha getStatusDaLinha() {
	return statusDaLinha;
    }

    public void setStatusDaLinha(StatusDaLinha statusDaLinha) {
	this.statusDaLinha = statusDaLinha;
    }
    @Deprecated
    public boolean getMarcada () {
        return marcada;
    }

    @Deprecated
    public void setMarcada (boolean val) {
        this.marcada = val;
    }
    
    @Deprecated
    public boolean getMarcavel () {
        return marcavel;
    }

    @Deprecated
    public void setMarcavel (boolean val) {
        this.marcavel = val;
    }

    public int getPontos () {
        return pontos;
    }
    
    public StatusDaLinha getStatus () {
        return statusDaLinha;
    }
    
    public void setStatus (StatusDaLinha stat) {
        this.statusDaLinha=stat;
    }
    
    public void setPontos (int val) {
        this.pontos = val;
    }

    public TipoDeLinha getTipoDeLinha () {
        return tipoDeLinha;
    }

    public void setTipoDeLinha (TipoDeLinha val) {
        this.tipoDeLinha = val;
    }

    public Linha (TipoDeLinha tipo) {
        this.setTipoDeLinha(tipo);
	this.setStatusDaLinha(StatusDaLinha.livre);
        this.setPontos(0);
        this.setMarcada(false);
        this.setMarcavel(false);
    }

    @Deprecated
    public boolean getRiscavel () {
        return riscavel;
    }

    @Deprecated
    public void setRiscavel (boolean val) {
        this.riscavel = val;
    }
    
    @Deprecated
    public boolean getRiscada () {
        return riscada;
    }

    @Deprecated
    public void setRiscada (boolean val) {
        this.riscada = val;
    }

}

