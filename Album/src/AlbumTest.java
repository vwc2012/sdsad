import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlbumTest {

    private Random random = new Random();

    private Album album;
    private final int TOTAL_FIGURINHAS = 200;
    private final int QUANT_FIGURINHAS_POR_PACOTE = 3;

    @Before
    public void setUp() {
        album = new Album(TOTAL_FIGURINHAS, QUANT_FIGURINHAS_POR_PACOTE);
    }

    @Test
    public void testarRecebimentoPacotinhoQualquer() {
        Figurinha[] novoPacotinho = criarPacotinho(null);  // posições aleatórias

        album.receberNovoPacotinho(novoPacotinho);
        assertEquals(1, album.getTotalPacotinhosRecebidos());
        assertEquals(QUANT_FIGURINHAS_POR_PACOTE,
                album.getQuantFigurinhasColadas() + album.getQuantFigurinhasRepetidas());
        for (int i = 0; i < QUANT_FIGURINHAS_POR_PACOTE; i++) {
            assertTrue(album.possuiFigurinhaColada(novoPacotinho[i]));
        }
    }

    @Test
    public void testarRecebimentoFigurinhaRepetida() {

        int[] posicoes = new int[] {1, 1, 1};

        // equivalentemente...
//        int[] posicoes = new int[3];
//        posicoes[0] = 1;
//        posicoes[1] = 1;
//        posicoes[2] = 1;
        Figurinha[] primeiroPacotinho = criarPacotinho(posicoes);

        album.receberNovoPacotinho(primeiroPacotinho);
        assertEquals(1, album.getQuantFigurinhasColadas());
        assertEquals(QUANT_FIGURINHAS_POR_PACOTE - 1, album.getQuantFigurinhasRepetidas());
        assertTrue(album.possuiFigurinhaRepetida(1));
        assertTrue(album.possuiFigurinhaRepetida(primeiroPacotinho[0]));  // outra forma
        assertTrue(album.possuiFigurinhaRepetida(new Figurinha(1, "dskdfsk")));  // outra forma


        posicoes = new int[] {10, 23, 1};
        Figurinha[] segundoPacotinho = criarPacotinho(posicoes);
        album.receberNovoPacotinho(segundoPacotinho);
        assertEquals(3, album.getQuantFigurinhasColadas());
        assertEquals(QUANT_FIGURINHAS_POR_PACOTE, album.getQuantFigurinhasRepetidas());
        assertTrue(album.possuiFigurinhaColada(10));
        assertTrue(album.possuiFigurinhaColada(23));
        assertFalse(album.possuiFigurinhaRepetida(10));
        assertFalse(album.possuiFigurinhaRepetida(23));
        //assertFalse(album.possuiFigurinhaRepetida(1));

        assertEquals(2, album.getTotalPacotinhosRecebidos());
    }

    @Test
    public void testarPreenchimentoAutomaticoDasUltimasFigurinhas() {
        // aqui o álbum está ainda vazio
        album.encomendarFigurinhasRestantes();
        assertEquals("Não deve ser possível encomendar as figurinhas faltantes " +
                "antes de ter 90% ou mais do álbum já preenchido",
                0, album.getQuantFigurinhasColadas());

        // vamos agora preencher o álbum quase totalmente
        while (album.getQuantFigurinhasColadas() <
                TOTAL_FIGURINHAS * Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR) {
            Figurinha[] pacotinho = criarPacotinho(null);
            album.receberNovoPacotinho(pacotinho);
        }

        album.encomendarFigurinhasRestantes();
        assertEquals("Depois da encomenda, o álbum deve estar completo",
                TOTAL_FIGURINHAS, album.getQuantFigurinhasColadas());
    }

    private Figurinha[] criarPacotinho(int[] posicoesDesejadas) {
        Figurinha[] novoPacotinho = new Figurinha[QUANT_FIGURINHAS_POR_PACOTE];
        for (int i = 0; i < QUANT_FIGURINHAS_POR_PACOTE; i++) {
            int posicaoDaFigurinha = posicoesDesejadas == null ? escolherPosicaoAleatoria() :
                    posicoesDesejadas[i];
            Figurinha figurinha = new Figurinha(posicaoDaFigurinha,
                    String.format("http://urlFakeDaFigurinha%d.jpg", posicaoDaFigurinha));
            novoPacotinho[i] = figurinha;
        }
        return novoPacotinho;
    }

    private int escolherPosicaoAleatoria() {
        return random.nextInt(TOTAL_FIGURINHAS) + 1;
    }
}