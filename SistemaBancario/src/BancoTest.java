import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BancoTest {

    private Banco banco;
    private Agencia agencia;
    private Correntista joao;
    private Correntista maria;
    private Gerente alice;
    private Gerente carlos;

    Conta conta1;
    Conta conta2;


    @Before
    public void setUp() {
        // crio o banco
        banco = new Banco();

        // adiciono gerentes
        alice = banco.adicionarGerente("Alice");
        carlos = banco.adicionarGerente("Carlos");

        // adiciono uma agência
        agencia = banco.adicionarAgencia(11111, "Agência Teste 1");
        agencia.setGerenteGeral(alice);

        // adiciono correntistas
        joao = banco.adicionarCorrentista("João", 1234);
        maria = banco.adicionarCorrentista("Maria", 5678);

        // adiciono contas
        conta1 = banco.criarConta(agencia, joao);
        conta2 = banco.criarConta(agencia, maria);
    }

    @Test
    public void testarCriacaoAgencia() {
        assertEquals(1, banco.getQuantAgencias());  // a agência criada no setUp
        Agencia novaAgencia = banco.adicionarAgencia(44444, "Nova Agência");
        assertNotNull("O banco deve retornar a agência recém-criada", novaAgencia);
        assertEquals(2, banco.getQuantAgencias());  // a agência criada no setUp
    }

    @Test
    public void testarCriacaoConta() {
        assertEquals("O saldo inicial de uma conta deve ser zero",
                0, conta1.getSaldo(), 0);
        assertEquals("O gerente default de uma conta nova é o gerente geral de sua agência",
                agencia.getGerenteGeral(), conta1.getGerente());
        assertEquals("A conta deve retornar corretamente seu dono", joao, conta1.getCorrentista());
    }

    @Test
    public void testarOperacoesBancarias() {
        conta1.receberDepositoEmDinheiro(300);
        assertEquals("O saldo deve refletir depósitos recebidos",
                300, conta1.getSaldo(), 0);

        conta1.sacar(10);
        assertEquals("O saldo da conta de origem deve diminuir após um saque",
                290, conta1.getSaldo(), 0);

        conta1.sacar(1000);
        assertEquals("Não deve ser possível sacar se não houver fundos suficientes (considerando o limite)",
                290, conta1.getSaldo(), 0);

        conta1.efetuarTransferencia(conta2, 250);
        assertEquals("O saldo da conta de origem deve diminuir após uma transferência",
                40, conta1.getSaldo(), 0);
        assertEquals("O saldo da conta de destino deve aumentar após uma transferência",
                250, conta2.getSaldo(), 0);

        conta1.sacar(130);
        assertEquals("Deve ser possível sacar até o limite de " + Conta.LIMITE, -90, conta1.getSaldo(), 0);
    }



}