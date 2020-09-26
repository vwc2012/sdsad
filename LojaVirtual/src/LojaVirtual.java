import java.util.ArrayList;
public class LojaVirtual {


    private int tamanhoEstoque;
    private float totalValorVendas;
    private ArrayList<Produto> produtosNoEstoque;

    public LojaVirtual(){
        this.produtosNoEstoque = new ArrayList<Produto>();
        this.tamanhoEstoque = 0;
    }

    public ArrayList<Produto> getProdutosNoEstoque(){
        return produtosNoEstoque;
    }

    public void setProdutosNoEstoque(ArrayList<Produto> produtosNoEstoque){
        this.produtosNoEstoque = produtosNoEstoque;
    }

    public int getTamanhoEstoque() {
        return this.tamanhoEstoque;
    }

    public void setTamanhoEstoque(int tamanhoEstoque){
        this.tamanhoEstoque = tamanhoEstoque;
    }

    private boolean receberPagamento(float valor){
        this.totalValorVendas += valor;
        return true;
    }

    public int getTamanhoEstoque(Produto produto) {
        return produto.getQuantidadeEmEstoque();
    }

    public float getTotalValorVendas() {
        return this.totalValorVendas;
    }

    public void setTotalValorVendas(float totalValorVendas){
        this.totalValorVendas = totalValorVendas;
    }

    public boolean verificarProdutoNoEstoque(Produto produto){
        for (Produto itemNoEstoque: this.produtosNoEstoque){
            if(itemNoEstoque.getId() == produto.getId()){
                return true;
            }
        }
        return false;
    }

    public void incluirProdutoNoEstoque(Produto produto, int quantidade) {
            if(!verificarProdutoNoEstoque(produto)){
                this.produtosNoEstoque.add(produto);
            }
           produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + quantidade);
           this.tamanhoEstoque += quantidade;
    }

    public String efetuarVenda(Produto produto, int quantidade) {
        if(verificarProdutoNoEstoque(produto) && produto.getQuantidadeEmEstoque() >= quantidade && this.receberPagamento(produto.getPreçoEmReais() * quantidade)){
            this.receberPagamento(produto.getPreçoEmReais()*quantidade);
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
            this.tamanhoEstoque -= quantidade;
            return "Recibo da Compra:" +
                    "\nID da compra: " + produto.getId() +
                    "\nCategoria: " + produto.getCategoria() +
                    "\nPreço do produto: " + produto.getPreçoEmReais() +
                    "\nQuantidade: " + quantidade +
                    "\nPreço total: " + produto.getPreçoEmReais()*quantidade;
        }

        return "";
    }

}