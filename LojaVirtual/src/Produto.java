public class Produto {

    private final long id;

    private int pesoEmGramas, quantidadeEmEstoque;

    private float preçoEmReais;

    private String categoria;

    private static long nextSeqNum = 1;


    public Produto(int pesoEmGramas, float precoEmReais, String categoria, int quantidadeEmEstoque){
        this.id = nextSeqNum++;
        this.pesoEmGramas = pesoEmGramas;
        this.preçoEmReais = precoEmReais;
        this.categoria = categoria;
        this.quantidadeEmEstoque = quantidadeEmEstoque;

    }

    public long getId(){
        return this.id;
    }

    public int getPesoEmGramas(){
        return this.pesoEmGramas;
    }

    public void setPesoEmGramas(int pesoEmGramas){
        this.pesoEmGramas = pesoEmGramas;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public String getCategoria(){
        return this.categoria;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque){
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getQuantidadeEmEstoque(){
        return this.quantidadeEmEstoque;
    }

    public void setPreçoEmReais(float precoEmReais){
        this.preçoEmReais = precoEmReais;
    }

    public float getPreçoEmReais(){
        return preçoEmReais;
    }

}
