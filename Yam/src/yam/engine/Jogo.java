package yam.engine;


import java.util.ArrayList; 

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.C867E980-B145-08E2-09C3-AD45E7C8C364]
// </editor-fold> 
public class Jogo {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2A00FC93-DFE1-99D9-E3C3-8872E268BA78]
    // </editor-fold> 
    private ArrayList<Jogador> jogadores;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.98A5B37A-F1A8-A8C4-AD68-417204A9EE0A]
    // </editor-fold> 
    public Jogo (int qtdJogadores, String[] nomes) {
        for (int i=0; i<qtdJogadores; i++) {
            this.jogadores.add(new Jogador(nomes[i]));
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.9030280C-1DCD-21B5-A9AE-7DE2B42FB560]
    // </editor-fold> 
    public ArrayList<Jogador> getJogadores () {
        return jogadores;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.BC0E0D02-A9BB-6EFC-CA9F-32231846FB00]
    // </editor-fold> 
    public void setJogadores (ArrayList<Jogador> val) {
        this.jogadores = val;
    }

}

