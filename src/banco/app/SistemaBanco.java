package banco.app;
import banco.model.*;
import banco.service.BancoService;
import javax.swing.JOptionPane;

public class SistemaBanco {
	private static BancoService banco;
	public static void main(String[] args) {
	

		banco = new BancoService();
	
		
		int opcao;
		
		do {
			String entrada = JOptionPane.showInputDialog(null, banco.menu(), "Menu Principal",JOptionPane.QUESTION_MESSAGE);

		     if (entrada == null) {
		    	 opcao = 0;
		            
		     } else {
		    	 try {
		    		 opcao = Integer.parseInt(entrada.trim());
		    		 } catch (NumberFormatException e) {
		                    JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro",
		                        JOptionPane.ERROR_MESSAGE);
		                    opcao = -1;
		                }
		            }
		     switch(opcao) {
		     case 1:
		    	 cadastrarContaCorrente();
		    	 break;
		     case 2:
		    	 cadastrarContaPoupanca();
		    	 break;
		     case 3: 
		    	 depositar();
		    	 break;
		     case 4:
		    	 sacar(); 
		    	 break;
		     case 5:
		    	 consultarSaldo(); 
		    	 break;
		     case 6:
		    	 exibirExtrato();
		    	 break;
		     case 7:
		    	 exibirHistorico();
		    	 break;
		     case 8:
		    	 banco.listarTodasAsContas(); 
		    	 break;
		     case 9:
		    	 banco.exibirRelatorioGeral();
		    	 break;
		     case 0:
	                JOptionPane.showMessageDialog(null, "Saindo!");
	                break;
	            default:
	                JOptionPane.showMessageDialog(null, "Opcao invalida!");
         }
		}while (opcao != 0);
	}
	
	
	private static void cadastrarContaCorrente() {
	    String numero = JOptionPane.showInputDialog("Numero da conta:");
	    if (numero == null) return;

	    String nome = JOptionPane.showInputDialog("Nome do titular:");
	    if (nome == null) return;

	    String cpf = JOptionPane.showInputDialog("CPF do titular:");
	    if (cpf == null) return;

	    String telefone = JOptionPane.showInputDialog("Telefone do titular:");
	    if (telefone == null) telefone = "";

	    double saldo = pedirValor("Saldo inicial:");
	    if (saldo < 0) return;

	    double limite = pedirValor("Limite do cheque especial:");
	    if (limite < 0) return;

	    Cliente cliente = new Cliente(nome, cpf, telefone);
	    ContaCorrente cc = new ContaCorrente(numero, cliente, saldo, limite);
	    banco.cadastrarContaCorrente(cc);
	}
	

	private static void cadastrarContaPoupanca() {
	    String numero = JOptionPane.showInputDialog("Numero da conta:");
	    if (numero == null) return;

	    String nome = JOptionPane.showInputDialog("Nome do titular:");
	    if (nome == null) return;

	    String cpf = JOptionPane.showInputDialog("CPF do titular:");
	    if (cpf == null) return;

	    String telefone = JOptionPane.showInputDialog("Telefone do titular:");
	    if (telefone == null) telefone = "";

	    double saldo = pedirValor("Saldo inicial:");
	    if (saldo < 0) return;

	    double taxa = pedirValor("Taxa rendimento mensal:");
	    if (taxa < 0) return;

	    Cliente cliente = new Cliente(nome, cpf, telefone);
	    ContaPoupanca cp = new ContaPoupanca(numero, cliente, saldo, taxa);
	    banco.cadastrarContaPoupanca(cp);
	}
    
    
	private static void depositar() {
        ContaBancaria conta = buscarConta();
        if (conta == null) return;
        double valor = pedirValor("Valor para depositar:");
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null, "Valor invalido!");
            return;
        }
        conta.depositar(valor);
        JOptionPane.showMessageDialog(null, "Deposito de R$ " + valor + " realizado!");
    }
    
    
	private static void sacar() {
	    ContaBancaria conta = buscarConta();
	    if (conta == null) return;

	    double valor = pedirValor("Valor para sacar:");
	    if (valor <= 0) {
	        JOptionPane.showMessageDialog(null, "Valor invalido!");
	        return;
	    }

	    boolean ok = conta.sacar(valor);
	    if (ok) {
	        JOptionPane.showMessageDialog(null, "Saque de R$ " + String.format("%.2f", valor) + " realizado!");
	    }

	}
	
	private static void consultarSaldo() {
        ContaBancaria conta = buscarConta();
        if (conta == null) return;
        conta.exibirSaldo();
    }
    
	private static ContaBancaria buscarConta() {
        String numero = JOptionPane.showInputDialog("Digite o numero da conta:");
        if (numero == null) return null;
        ContaBancaria conta = banco.buscarConta(numero);
        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Conta nao encontrada!");
        }
        return conta;
    }
    

    private static double pedirValor(String mensagem) {
        String entrada = JOptionPane.showInputDialog(mensagem);
        if (entrada == null) return -1;
        try {
            return Double.parseDouble(entrada.replace(",", ".").trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }
    

    private static void exibirExtrato() {
        ContaBancaria conta = buscarConta();
        if (conta == null) return;
        conta.gerarExtrato();
    }

    private static void exibirHistorico() {
        ContaBancaria conta = buscarConta();
        if (conta == null) return;
        conta.exibirHistorico();
    }
}
