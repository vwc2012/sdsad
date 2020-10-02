import java.util.Objects;

public class Hashtag {
    private int quant = 0;
    private final String  nome;


    public Hashtag(String nome){
        this.nome = nome;

    }

    public int getQuant() {
        return this.quant;
    }

    public String getNome() {
        return this.nome;
    }

    public int addQuantidade(){
        return this.quant += 1;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(nome, hashtag.nome);
    }

}
