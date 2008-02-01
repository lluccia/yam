package yam.engine;

import java.util.ArrayList;
import java.util.Iterator;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3FAD86FC-F28A-6C0F-CB39-DF93290E172F]
// </editor-fold> 
public class Jogada {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0BB66FD6-236D-BDAB-4C4A-6E33B6EF0A69]
    // </editor-fold> 
    private ArrayList<Dado> dados;

    private int totalNosDados;
    
    private int seqJogada;
   
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7B6B63E0-2FA4-9309-0488-5CDE3EF1BAC5]
    // </editor-fold> 
    private boolean combUm;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.45FE1018-057C-DDDE-E977-BE184CB5578C]
    // </editor-fold> 
    private boolean combDois;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EEAFF71B-7FD5-B30E-8B2E-6FCDE40D624B]
    // </editor-fold> 
    private boolean combTres;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9A4D2D18-74E9-2968-01A1-731576A91AF7]
    // </editor-fold> 
    private boolean combQuatro;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A119AC1A-4F74-9CD6-0D26-1CBA0B0DA0BD]
    // </editor-fold> 
    private boolean combCinco;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E0ABC7E2-140A-FD7B-6BFF-BB0B9C9131B5]
    // </editor-fold> 
    private boolean combSeis;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1F4CACC2-A831-BF0D-9BF9-3112FF1990ED]
    // </editor-fold> 
    private boolean combQuadra;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C4C70571-7EE8-3854-08E0-C3B51FBDC268]
    // </editor-fold> 
    private boolean combFull;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A2E8E869-CC99-AC10-FEEE-CA8BF1EEA7FE]
    // </editor-fold> 
    private boolean combSeqMinima;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6E792031-E5D6-E6D5-DCDF-26FE45605392]
    // </editor-fold> 
    private boolean combSeqMaxima;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5DB1AAB7-514A-D28C-9449-10CA887E9910]
    // </editor-fold> 
    private boolean combYam;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1DC64B16-8ADB-9FC3-38ED-31A070A35509]
    // </editor-fold> 
    public Jogada () throws CloneNotSupportedException {
        this.dados=new ArrayList<Dado>(5);
        for (int i=0; i<5; i++) {
            this.dados.add(new Dado());
        }
        this.setSeqJogada(0);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1C31A425-2673-F694-3850-37BD8A690661]
    // </editor-fold> 
    public void jogarDados () throws CloneNotSupportedException {
        Iterator<Dado> itr = this.dados.iterator();
        int totalNosDadostmp=0;
        while (itr.hasNext()) {
            Dado dadoAtual = itr.next();
            dadoAtual.jogar();
            totalNosDadostmp+=dadoAtual.getValor();
        }
        this.setTotalNosDados(totalNosDadostmp);
        this.setSeqJogada(this.seqJogada+1);
        this.ordenarDados();
        this.verificarCombinacoes();
    }
    
    private void ordenarDados () throws CloneNotSupportedException {
        Dado dadoTmp;
        for (int i=dados.size()-1; i>= 0; i--) {
            for (int j=1; j<=i; j++) {
                if (dados.get(j-1).getValor() > this.dados.get(j).getValor()) {
                   dadoTmp = (Dado)this.dados.get(j-1).clone();
                   this.dados.set(j-1, (Dado)this.dados.get(j).clone());
                   this.dados.set(j,   (Dado)dadoTmp.clone());
                }
            }
        }
    }
    
    private void verificarCombinacoes () {
        //limpa combinacoes
        setCombUm(false);
        setCombDois(false);
        setCombTres(false);
        setCombQuatro(false);
        setCombCinco(false);
        setCombSeis(false);
        setCombQuadra(false);
        setCombFull(false);
        setCombSeqMinima(false);
        setCombSeqMaxima(false);
        setCombYam(false);
        
        //verifica combUm a combSeis
        Iterator<Dado> itr = this.dados.iterator();
        while (itr.hasNext()) {
            Dado dadoAtual = itr.next();
            switch (dadoAtual.getValor()) {
                case 1:setCombUm(true); break;
                case 2:setCombDois(true); break;
                case 3:setCombTres(true); break;
                case 4:setCombQuatro(true); break;
                case 5:setCombCinco(true); break;
                case 6:setCombSeis(true); break;
            }
        }
        
        //verifica combQuadra
        if (
                this.dados.get(1).getValor() == this.dados.get(2).getValor() &
                this.dados.get(2).getValor() == this.dados.get(3).getValor() &
                (
                    this.dados.get(0).getValor() == this.dados.get(1).getValor() |
                    this.dados.get(3).getValor() == this.dados.get(4).getValor()
                )
           ) {
            setCombQuadra(true);
        }
        
        //verifica combFull
        if (
                (
                    this.dados.get(0).getValor() == this.dados.get(1).getValor() &
                    this.dados.get(1).getValor() == this.dados.get(2).getValor() &
                    this.dados.get(2).getValor() != this.dados.get(3).getValor() &
                    this.dados.get(3).getValor() == this.dados.get(4).getValor() 
                ) |
                (
                    this.dados.get(0).getValor() == this.dados.get(1).getValor() &
                    this.dados.get(1).getValor() != this.dados.get(2).getValor() &
                    this.dados.get(2).getValor() == this.dados.get(3).getValor() &
                    this.dados.get(3).getValor() == this.dados.get(4).getValor() 
                    
                ) 
           ) {
            setCombFull(true);
        }
        
        //verifica combSeqMinima
        if (
                    this.dados.get(0).getValor() == 1 &
                    this.dados.get(1).getValor() == 2 &
                    this.dados.get(2).getValor() == 3 &
                    this.dados.get(3).getValor() == 4 &
                    this.dados.get(4).getValor() == 5
           ) {
            setCombSeqMinima(true);
        }
        
        //verifica combSeqMaxima
        if (
                    this.dados.get(0).getValor() == 2 &
                    this.dados.get(1).getValor() == 3 &
                    this.dados.get(2).getValor() == 4 &
                    this.dados.get(3).getValor() == 5 &
                    this.dados.get(4).getValor() == 6
           )
        {
            setCombSeqMaxima(true);
        }
        
        //verifica combYam
        if (
                    this.dados.get(0).getValor() == this.dados.get(1).getValor() &
                    this.dados.get(1).getValor() == this.dados.get(2).getValor() &
                    this.dados.get(2).getValor() == this.dados.get(3).getValor() &
                    this.dados.get(3).getValor() == this.dados.get(4).getValor() 
           )
        {
            setCombYam(true);
        }
    }
    
    public void marcarDado(int posicao) {
        this.dados.get(posicao).marcar();
    }
    
    public void inicializarJogada () {
        this.setSeqJogada(0);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8418B64B-C163-6445-433C-FBC92996ED58]
    // </editor-fold> 
    public boolean getCombCinco () {
        return combCinco;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B17E1B4B-A381-97C5-92AB-0EE1E2CA3353]
    // </editor-fold> 
    private void setCombCinco (boolean val) {
        this.combCinco = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1E567EAE-FAA7-7FD2-0731-926943816E2B]
    // </editor-fold> 
    private void setCombDois (boolean val) {
        this.combDois = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CE70ACA5-7887-0FD3-ACC4-58B4FD7D047A]
    // </editor-fold> 
    public boolean getCombFull () {
        return combFull;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8B94AA8D-71B0-8434-3656-49406E131FA6]
    // </editor-fold> 
    public void setCombFull (boolean val) {
        this.combFull = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B4616226-5F4B-DF40-3A2E-C632699BDB7E]
    // </editor-fold> 
    public boolean getCombQuadra () {
        return combQuadra;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.F17B22DA-776F-11B2-162E-7342DDF4B444]
    // </editor-fold> 
    public void setCombQuadra (boolean val) {
        this.combQuadra = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.767203ED-802C-0EAC-9718-3A8ADFE76F04]
    // </editor-fold> 
    public boolean getCombQuatro () {
        return combQuatro;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9F1797CF-70D7-2547-9F8F-3AED760D07E9]
    // </editor-fold> 
    private void setCombQuatro (boolean val) {
        this.combQuatro = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4231AD1E-4B71-8496-8A99-970F63EC8025]
    // </editor-fold> 
    public boolean getCombSeis () {
        return combSeis;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.0DCAE09D-F175-F0F3-B548-B2E679637B83]
    // </editor-fold> 
    private void setCombSeis (boolean val) {
        this.combSeis = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6170D15F-6607-8F53-1D58-FE625BC98E0D]
    // </editor-fold> 
    public boolean getCombSeqMaxima () {
        return combSeqMaxima;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5AB4D58C-A8F1-3E04-C7B9-F8DD6A028FDC]
    // </editor-fold> 
    private void setCombSeqMaxima (boolean val) {
        this.combSeqMaxima = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.AF0D926E-2B11-6595-8033-5B96FBFDC897]
    // </editor-fold> 
    public boolean getCombSeqMinima () {
        return combSeqMinima;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.5AC7CBC1-E4B4-7D1B-C401-E19C22624B05]
    // </editor-fold> 
    private void setCombSeqMinima (boolean val) {
        this.combSeqMinima = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.7D46F02E-1EC7-EF11-50BF-ED1986B6A5B0]
    // </editor-fold> 
    public boolean getCombTres () {
        return combTres;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.52F3B3BF-F95C-5057-740E-35517CBF5F2A]
    // </editor-fold> 
    private void setCombTres (boolean val) {
        this.combTres = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.8CFBB2AD-4000-DBFE-E8A5-E645A1193F41]
    // </editor-fold> 
    public boolean getCombYam () {
        return combYam;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.683AFEDD-3B11-2E90-EBAA-6149DD12B9BA]
    // </editor-fold> 
    private void setCombYam (boolean val) {
        this.combYam = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.15BDE51C-0112-27DA-B221-4F0118292C39]
    // </editor-fold> 
    public boolean getCombDois () {
        return combDois;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1395738A-F648-9FA6-8098-F44A4378FFB2]
    // </editor-fold> 
    public boolean getCombUm () {
        return combUm;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CF329F77-A989-EB3C-7345-9BE2F101C694]
    // </editor-fold> 
    private void setCombUm (boolean val) {
        this.combUm = val;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1395738A-F648-9FA6-8098-F44A4378FFB2]
    // </editor-fold> 
    public int getTotalNosDados () {
        return totalNosDados;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CF329F77-A989-EB3C-7345-9BE2F101C694]
    // </editor-fold> 
    private void setTotalNosDados (int val) {
        this.totalNosDados = val;
    }
   
    public ArrayList<Dado> getDado() {
        return dados;
    }
    
    public int getSeqJogada() {
        return seqJogada;
    }

    public void setSeqJogada(int seqJogada) {
        this.seqJogada = seqJogada;
    }
    
}