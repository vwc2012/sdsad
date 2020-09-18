public class Figurinha {

    private int posicao;
    private String url;

    public Figurinha(int posicao, String urlImagem) {
        this.posicao = posicao;
        this.url = urlImagem;
    }

    /**
     * Indica a posição, no álbum, que esta figurinha deve ocupar.
     *
     * @return Um int dizendo a posição da figurinha
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     * Retorna a URL de onde a imagem associada a esta figurinha deverá ser baixada.
     *
     * @return uma String com o endereço desejado
     */
    public String getUrlImagem() {
        return url;
    }
}
