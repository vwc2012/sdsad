public class Roupa extends Produto {

    private final char tamanho;

    private String cor;

    public Roupa(char tamanho, int pesoEmGramas, float precoEmReais, String categoria, int quantidadeEmEstoque, String cor){
        super(pesoEmGramas, precoEmReais, "Vestuário", quantidadeEmEstoque);
        this.tamanho = tamanho;
        this.cor = cor;


    }

    public char getTamanho(){
        return this.tamanho;
    }

    public void setCor(String cor){
        this.cor = cor;
    }

    public String getColor(){
        return this.cor;
    }

    @Override
    public String toString(){
        return "A Roupa tem a cor" + this.getColor() + "de tamanho" + this.getTamanho() + "e custa";
    }
}
