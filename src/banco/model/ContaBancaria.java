package banco.model;
import banco.interfaces.Operavel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ContaBancaria implements Operavel{
	private String numeroConta;
	private Cliente titular;
	private double saldo;
	List<String> historico;
	
	private static final DateTimeFormatter FORMATTER =
		    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


		public void registrarTransacao(String descricao) {
		    String timestamp = LocalDateTime.now().format(FORMATTER);
		    historico.add(timestamp + " - " + descricao);
		}
		
	public ContaBancaria(String numeroConta, Cliente titular, double saldo) {
		this.numeroConta = numeroConta;
		this.titular = titular;
		this.saldo = saldo;
		this.historico = new ArrayList<>();
	}
	
	@Override
	public void depositar(double valor) {
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null,
                "Inválido!o valor deve ser maior que zero",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        saldo += valor;
        registrarTransacao("Deposito R$ "+ String.format("%.2f", valor));
	}
	
	 @Override
	 public boolean sacar(double valor) {
		    if(valor <= 0) {
		        JOptionPane.showMessageDialog(null,"Valor invalido!","Erro", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
		    saldo -= valor;
		    registrarTransacao("Saque: R$ " + String.format("%.2f", valor));
		    return true;
		}
	 
	 public void exibirSaldo() {
		 JOptionPane.showMessageDialog(null,"Conta: " + numeroConta + "\nTitular: " + titular.getNome() + "\nSaldo: R$ " + String.format("%.2f", saldo), "Saldo", JOptionPane.INFORMATION_MESSAGE);
	 }
	
    public void exibirHistorico() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Histórico de Transações ---\n");
        sb.append("Conta: ").append(numeroConta).append("\n");
        sb.append("Titular: ").append(titular.getNome()).append("\n\n");
        for (String transacao : historico) {
            sb.append(transacao).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(),"Histórico", JOptionPane.INFORMATION_MESSAGE);
    }

	
	public String getNumeroConta() {
		return numeroConta;
	}


	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}


	public Cliente getTitular() {
		return titular;
	}


	public void setTitular(Cliente titular) {
		this.titular = titular;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public List<String> getHistorico() {
		return historico;
	}


	public void setHistorico(List<String> historico) {
		this.historico = historico;
	}


	public abstract void gerarExtrato();


}
