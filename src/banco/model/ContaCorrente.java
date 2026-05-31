package banco.model;
import javax.swing.JOptionPane;

public class ContaCorrente extends ContaBancaria{
	private double limiteChequEspecial;

	
	public ContaCorrente(String numeroConta, Cliente titular, double saldo, double limiteChequeEspecial) {
	super(numeroConta, titular, saldo);
	this.limiteChequEspecial = limiteChequEspecial;
	}


	public void usarChequeEspecial(double valor) {
	    setSaldo(getSaldo() - valor);
	    registrarTransacao("Saque com cheque especial: R$ " + String.format("%.2f", valor));
	}

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            JOptionPane.showMessageDialog(null,"Inválido! O valor deve ser maior que zero","Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            registrarTransacao("Saque: R$ " + String.format("%.2f", valor));
            return true;
        } else if (valor <= getSaldo() + limiteChequEspecial) {
            usarChequeEspecial(valor);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!\nSaldo: R$ " + String.format("%.2f", getSaldo()) + "\nLimite Cheque Especial: R$ " + String.format("%.2f", limiteChequEspecial) +"\nLimite total disponível: R$ " + String.format("%.2f", getSaldo() + limiteChequEspecial),"Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

	
	@Override
	public void gerarExtrato() {
		StringBuilder sb = new StringBuilder();
		sb.append("--- Extrato Conta Corrente ---\n");
		sb.append("Titular: ").append(getTitular().getNome()).append("\n");
		sb.append("Numero da conta: ").append(getNumeroConta()).append("\n");
		sb.append("Saldo: ").append(getSaldo()).append("\n");
		sb.append("Limte do Cheque Especial: ").append(getLimiteChequEspecial()).append("\n");
		sb.append("Historico: ").append(getHistorico()).append("\n");
	    JOptionPane.showMessageDialog(null, sb.toString());
	    
	    sb.append("Saldo: R$ ").append(String.format("%.2f", getSaldo())).append("\n");
	    for (String t : getHistorico()) {
	        sb.append(t).append("\n");
	    }
	}


	public double getLimiteChequEspecial() {
		return limiteChequEspecial;
	}


	public void setLimiteChequEspecial(double limiteChequEspecial) {
		this.limiteChequEspecial = limiteChequEspecial;
	}

	
	
}
