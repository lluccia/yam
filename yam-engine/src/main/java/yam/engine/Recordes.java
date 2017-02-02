package yam.engine;

import java.io.*;

public class Recordes implements Serializable {

    private static final long serialVersionUID = 6743888923854069111L;

    public class Recorde implements Serializable {

        private static final long serialVersionUID = 1263586825674045967L;

        public String nome;
        public int pontos;

        public Recorde() {
            this.nome = new String();
            this.pontos = 0;
        }

        public Recorde(String nome, int pontos) {
            this.nome = nome;
            this.pontos = pontos;
        }

        @Override
        public String toString() {
            return nome + ": " + pontos;
        }
    }

    private static int MAX_RECORDES = 10;
    private Recorde[] recordes;

    public Recordes() {
        recordes = new Recorde[MAX_RECORDES];
        for (int i = 0; i < MAX_RECORDES; i++) {
            recordes[i] = new Recorde("A", 10 - i);
        }
    }

    public String getStringRecordes() {
        String tmp = "";

        for (int i = 0; i < MAX_RECORDES; i++) {
            tmp = tmp.concat(recordes[i].toString());
            tmp = tmp.concat(System.getProperty("line.separator"));
        }
        return tmp;
    }

    public boolean verificarRecorde(String nome, int pontos) {
        for (int i = 0; i < MAX_RECORDES; i++) {
            if (pontos > recordes[i].pontos) {
                incluirRecorde(new Recorde(nome, pontos), i);
                return true;
            }
        }
        return false;
    }

    public void incluirRecorde(Recorde r, int pos) {
        // avança os recordes a partir da posição de inclusão
        for (int i = MAX_RECORDES - 1; i > pos; i--) {
            recordes[i].nome = recordes[i - 1].nome;
            recordes[i].pontos = recordes[i - 1].pontos;
        }
        recordes[pos].nome = r.nome;
        recordes[pos].pontos = r.pontos;
    }
}
