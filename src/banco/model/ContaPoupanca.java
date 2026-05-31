package banco.model;
import javax.swing.JOptionPane;

public class ContaPoupanca extends ContaBancaria {
	private double taxaMensal;

	public ContaPoupanca(String numeroConta, Cliente titular, double saldoInicial, double rendimento) {
	    super(numeroConta, titular, saldoInicial);
	    this.taxaMensal = rendimento;
	}
	
	public double calcularRendimento() {
		return getSaldo() * taxaMensal;
	}
	
	public void aplicarRendimento() {
		 double rendimento = calcularRendimento();
	     setSaldo(getSaldo() + rendimento);
	     registrarTransacao("Rendimento aplicado: R$ " + String.format("%.2f", rendimento) + " (taxa: " + String.format("%.2f", taxaMensal * 100) + "% ao mês)");
	     JOptionPane.showMessageDialog(null,"Novo Saldo: R$ " + String.format("%.2f", getSaldo()), "Rendimento", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void gerarExtrato() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("--- Extrato Conta Poupança ---\n");
	    sb.append("Titular: ").append(getTitular().getNome()).append("\n");
	    sb.append("Número da Conta: ").append(getNumeroConta()).append("\n");
	    sb.append("Saldo: R$ ").append(String.format("%.2f", getSaldo())).append("\n");
	    sb.append("Taxa de Rendimento Mensal: ").append(String.format("%.2f", getTaxaMensal() * 100)).append("%\n");
	    sb.append("Rendimento Estimado Próximo Mês: R$ ").append(String.format("%.2f", calcularRendimento())).append("\n\n");
	    
	    sb.append("--- Histórico de Transações ---\n");
	    if (getHistorico().isEmpty()) {
	        sb.append("Nenhuma transação realizada.\n");
	    } else {
	        for (String t : getHistorico()) {
	            sb.append(t).append("\n");
	        }
	    }
	    
	    JOptionPane.showMessageDialog(null, sb.toString());
	}

	
	public double getTaxaMensal() {
		return taxaMensal;
	}

	public void setTaxaMensal(double taxaMensal) {
		this.taxaMensal = taxaMensal;
	}
	

}
