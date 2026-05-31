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
		sb.append("--- Extrato Conta Poupanca ---\n");
		sb.append("Titular: ").append(getTitular().getNome()).append("\n");
		sb.append("Numero da conta: ").append(getNumeroConta()).append("\n");
		sb.append("Saldo: ").append(getSaldo()).append("\n");
		sb.append("Taxa de rendimento mensal: ").append(getTaxaMensal()).append("\n");
		sb.append("Rendimento estimado do próximo mês: ").append(String.format("%.2f", calcularRendimento())).append("\n");
		sb.append("Historico: ").append(getHistorico()).append("\n");
		JOptionPane.showMessageDialog(null, sb.toString());
	}

	public double getTaxaMensal() {
		return taxaMensal;
	}

	public void setTaxaMensal(double taxaMensal) {
		this.taxaMensal = taxaMensal;
	}
	

}
