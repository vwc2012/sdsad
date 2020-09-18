import java.lang.reflect.Array;

public class Album {

    public static final float PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR = 0.9f;  // 90%

    public int totalPacotinhosRecebidos, figurinhasColadas, figurinhasRepetidas, figurinhasFaltantes, totalFigurinhas;


    public Figurinha figurinhas[] = new Figurinha[200];

    public boolean ArrayRepetidas[] = new boolean[200];


    public Album(int totalFigurinhas, int quantFigurinhasPorPacotinho) {
        this.totalFigurinhas = totalFigurinhas;
    }

    public void receberNovoPacotinho(Figurinha[] pacotinho) {
        for (int i = 0; i < pacotinho.length; i++) {
            Figurinha figurinha = pacotinho[i];
            if (this.possuiFigurinhaColada(figurinha.getPosicao())) {
                this.figurinhasRepetidas += 1;
                ArrayRepetidas[figurinha.getPosicao()-1] = true;
            } else {
                this.figurinhas[figurinha.getPosicao()-1] = figurinha;
                this.figurinhasColadas += 1;
            }
        }

        // equivalentemente, usar um "for each"
        this.totalPacotinhosRecebidos += 1;
    }

    public int getTotalPacotinhosRecebidos() {
        return totalPacotinhosRecebidos;
    }

    /**
     * Termina de preencher o álbum, desde que ele já esteja preenchido além de certa fração
     * mínima definida em Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR.
     *
     * Se o álbum não estiver ainda completo o suficiente para isso, este método simplesmente
     * não faz nada.
     */
    public void encomendarFigurinhasRestantes() {
        if (this.figurinhasColadas >= PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR*this.totalFigurinhas){
            for (int i = 0; i<200; i++) {
                this.figurinhas[i] = new Figurinha(i, String.format("http://urlFakeDaFigurinha%d.jpg", i));
            }
            this.figurinhasColadas = 200;
        }
    }

    public boolean possuiFigurinhaColada(int posicao) {
        return this.figurinhas[posicao-1] != null;
    }

    public boolean possuiFigurinhaColada(Figurinha figurinha) {  // overload
        return possuiFigurinhaColada(figurinha.getPosicao());
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.ArrayRepetidas[posicao-1];
    }

    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {  // overload
        return possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    public int getQuantFigurinhasColadas() {
        return figurinhasColadas;
    }

    public int getQuantFigurinhasRepetidas() {
        return figurinhasRepetidas;
    }

    public int getQuantFigurinhasFaltando() {
        return figurinhasFaltantes;
    }

}
