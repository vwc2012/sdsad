public class Livro extends Produto {

    private final String titulo, autor;
    private final int anoDePublicacao, numeroDePaginas;

    public Livro(String titulo, String autor, int anoDePublicacao, int numeroDePaginas, int pesoEmGramas, float precoEmReais, String categoria, int quantidadeEmEstoque){

        super(pesoEmGramas, precoEmReais, "Publicações", quantidadeEmEstoque);

        this.anoDePublicacao = anoDePublicacao;
        this.numeroDePaginas = numeroDePaginas;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    @Override
    public String toString(){
        return "Este livro tem título" + this.titulo + "de autor" + this.autor + "publicado no dia" + this.anoDePublicacao + "e possui" + this.numeroDePaginas + ".";
    }



}
