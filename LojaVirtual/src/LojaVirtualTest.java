import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LojaVirtualTest {
    private LojaVirtual Gamestop;
    private Livro livro;
    private Produto game;
    private Roupa roupa;
    private int pesoProduto;
    private float precoProduto;
    private String categoria;
    private int quantEmEstoqueProduto;
    private String titulo;
    private String autor;
    private int anoDePublicacao;
    private int numeroDePaginas;
    private char tamanho;
    private String corRoupa;
    private static final int FLOAT_DELTA = 0;



    @Before
    public void setUp(){

        Gamestop = new LojaVirtual();
        pesoProduto = 5;
        precoProduto = 10;
        categoria = "Video-Game";
        quantEmEstoqueProduto = 3;

        game = new Produto(pesoProduto, precoProduto, categoria, quantEmEstoqueProduto);

        pesoProduto = 300;
        precoProduto = 25;
        categoria = "Guia";
        quantEmEstoqueProduto = 20;
        titulo = "Guia de Detonados para PS2";
        autor = "Gabe Logan Newell";
        anoDePublicacao = 2004;
        numeroDePaginas = 45;


        livro = new Livro(titulo, autor, anoDePublicacao, numeroDePaginas, pesoProduto, precoProduto,  categoria, quantEmEstoqueProduto);



        pesoProduto = 100;
        precoProduto = 50;
        categoria = "Com estampa";
        quantEmEstoqueProduto = 15;
        tamanho = 'm';
        corRoupa = "preta";



        roupa = new Roupa(tamanho, pesoProduto, precoProduto, categoria, quantEmEstoqueProduto, corRoupa);
    }

    @Test
    public void TestGetTamanhoEstoque(){
        assertEquals(0, Gamestop.getTamanhoEstoque());
        assertEquals(15, Gamestop.getTamanhoEstoque(roupa));
        assertEquals(3, Gamestop.getTamanhoEstoque(game));
        assertEquals(20, Gamestop.getTamanhoEstoque(livro));
    }

    @Test
    public void TestIncluirProdutoNoEstoque(){
        assertEquals(0, Gamestop.getTamanhoEstoque());

        Gamestop.incluirProdutoNoEstoque(game, 3);
        Gamestop.incluirProdutoNoEstoque(livro, 15);

        assertEquals(18, Gamestop.getTamanhoEstoque());

    }

    @Test
    public void TestEfetuarVenda(){
        Gamestop.incluirProdutoNoEstoque(livro, 20);
        Gamestop.efetuarVenda(livro, 2);
        assertEquals(true, Gamestop.verificarProdutoNoEstoque(livro));
        assertEquals(100, Gamestop.getTotalValorVendas(), FLOAT_DELTA);
        assertEquals(18, Gamestop.getTamanhoEstoque());
        
        assertEquals("Recibo da Compra:" +
                "\nID da compra: " + livro.getId() +
                "\nCategoria: " + livro.getCategoria() +
                "\nPreço do produto: " + livro.getPreçoEmReais() +
                "\nQuantidade: " + 2 +
                "\nPreço total: " + livro.getPreçoEmReais()*2, Gamestop.efetuarVenda(livro, 2));
    }

    @Test
    public void TestVendaDeProdutoForaDoEstoque(){
        Gamestop.incluirProdutoNoEstoque(livro, 3);
        Gamestop.efetuarVenda(livro, 100);
        assertEquals(0, Gamestop.getTotalValorVendas(), FLOAT_DELTA);
    }



}
