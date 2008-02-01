package yam.engine;



// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F89A74DA-3116-9669-511B-823D37F84927]
// </editor-fold> 
public class Linha {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C3517E91-5897-47C4-415E-CEDAFA851442]
    // </editor-fold> 
    private boolean riscada;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.77D3D977-F272-0FD4-C356-8508A27240D0]
    // </editor-fold> 
    private boolean riscavel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.14055574-5E0F-BD7E-FA03-DB13D94408F1]
    // </editor-fold> 
    private TipoDeLinha tipoDeLinha;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.55835181-B10E-39C0-E435-0AB0BACB0D6C]
    // </editor-fold> 
    private int pontos;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3E2BC226-38ED-90E2-93B1-45D630B2A200]
    // </editor-fold> 
    private boolean marcada;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.552DE972-7C45-A551-F0FC-3307035F95D8]
    // </editor-fold> 
    private boolean marcavel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4AD2BC7A-1090-F673-13F3-F1104FF6853A]
    // </editor-fold> 
    public boolean getMarcada () {
        return marcada;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B5F40BB6-2361-D446-D009-BD8AFC962039]
    // </editor-fold> 
    public void setMarcada (boolean val) {
        this.marcada = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7BC5642E-419A-9E47-0A3B-72A265B743DF]
    // </editor-fold> 
    public boolean getMarcavel () {
        return marcavel;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4A853F62-39AF-3BA1-B1C5-7533D9476E66]
    // </editor-fold> 
    public void setMarcavel (boolean val) {
        this.marcavel = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A6EE639F-21F1-7942-EE38-A73AC7BDC433]
    // </editor-fold> 
    public int getPontos () {
        return pontos;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B1F68004-6EFE-AC55-6361-FCDF29F3AF5F]
    // </editor-fold> 
    public void setPontos (int val) {
        this.pontos = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A1F72929-B13A-1562-65FC-B082BB8B276A]
    // </editor-fold> 
    public TipoDeLinha getTipoDeLinha () {
        return tipoDeLinha;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2462152C-F97A-D7DB-F045-FC342A68BBEF]
    // </editor-fold> 
    public void setTipoDeLinha (TipoDeLinha val) {
        this.tipoDeLinha = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.353F7974-50C8-62EE-74D5-2C765A17E3C7]
    // </editor-fold> 
    public Linha (TipoDeLinha tipo) {
        this.setTipoDeLinha(tipo);
        this.setPontos(0);
        this.setMarcada(false);
        this.setMarcavel(false);
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BF5E519F-85C1-CD60-EF96-7392F7927E9D]
    // </editor-fold> 
    public boolean getRiscavel () {
        return riscavel;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.45991244-5456-0C1B-1D1C-7047BEA48164]
    // </editor-fold> 
    public void setRiscavel (boolean val) {
        this.riscavel = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F97DE8C3-4B3C-89C9-0414-993AF95EB63A]
    // </editor-fold> 
    public boolean getRiscada () {
        return riscada;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.978A84E1-B301-1113-8FAF-BE6EEEC35243]
    // </editor-fold> 
    public void setRiscada (boolean val) {
        this.riscada = val;
    }

}

