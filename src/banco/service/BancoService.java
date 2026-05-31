package banco.service;

import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class BancoService {

	private List<ContaCorrente> contasCorrente;
	private List<ContaPoupanca> contasPoupanca;
	
	 public BancoService() {
		 this.contasCorrente = new ArrayList<>();
	     this.contasPoupanca = new ArrayList<>();
	    }
	
	 public ContaBancaria buscarConta(String numeroConta) {
		    for (ContaCorrente cc : contasCorrente) {
		        if (cc.getNumeroConta().trim().equals(numeroConta.trim())) return cc;
		    }
		    for (ContaPoupanca cp : contasPoupanca) {
		        if (cp.getNumeroConta().trim().equals(numeroConta.trim())) return cp;
		    }
		    return null;
		}

		public boolean numeroContaExiste(String numeroConta) {
		    for (ContaCorrente cc : contasCorrente) {
		        if (cc.getNumeroConta().trim().equals(numeroConta.trim())) return true;
		    }
		    for (ContaPoupanca cp : contasPoupanca) {
		        if (cp.getNumeroConta().trim().equals(numeroConta.trim())) return true;
		    }
		    return false;
		}
		public void cadastrarContaCorrente(ContaCorrente cc) {
		    if (numeroContaExiste(cc.getNumeroConta())) {
		        JOptionPane.showMessageDialog(null,
		            "Já existe uma conta com o número: " + cc.getNumeroConta(),
		            "Erro", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    contasCorrente.add(cc);
		    JOptionPane.showMessageDialog(null,
		        "Conta Corrente cadastrada com sucesso!\nNúmero: " + cc.getNumeroConta(),
		        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}



		public void cadastrarContaPoupanca(ContaPoupanca cp) {
		    if (numeroContaExiste(cp.getNumeroConta())) {
		        JOptionPane.showMessageDialog(null,
		            "Já existe uma conta com o número: " + cp.getNumeroConta(),
		            "Erro", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		    contasPoupanca.add(cp);
		    JOptionPane.showMessageDialog(null,
		        "Conta Poupança cadastrada com sucesso!\nNúmero: " + cp.getNumeroConta(),
		        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	
	public void listarTodasAsContas() {
		StringBuilder sb = new StringBuilder();
		sb.append("=== Lista de Contas ===\n\n");
	    for (ContaCorrente cc : contasCorrente) {
	        sb.append(cc.getNumeroConta()).append("\n");
	        sb.append(cc.getTitular().getNome()).append(" R$ ");
	        sb.append(cc.getSaldo()).append("\n");
	    }
	    for (ContaPoupanca cp : contasPoupanca) {
	        sb.append(cp.getNumeroConta()).append("\n");
	        sb.append(cp.getTitular().getNome()).append(" R$ ");
	        sb.append(cp.getSaldo()).append("\n");
	    }
	    JOptionPane.showMessageDialog(null, sb.toString());
	}

	public double calcularPatrimonioTotal() {
		double total = 0;
	    for (ContaCorrente cc : contasCorrente) total += cc.getSaldo();
	    for (ContaPoupanca cp : contasPoupanca) total += cp.getSaldo();
	    return total;
	}
	
	public void exibirRelatorioGeral() {
	    List<ContaBancaria> todas = new ArrayList<>();
	    todas.addAll(contasCorrente);
	    todas.addAll(contasPoupanca);

	    if (todas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada.");
	        return;
	    }

	    ContaBancaria maior = todas.get(0);
	    for (ContaBancaria c : todas)
	        if (c.getSaldo() > maior.getSaldo()) maior = c;

	    ContaBancaria menor = todas.get(0);
	    for (ContaBancaria c : todas)
	        if (c.getSaldo() < menor.getSaldo()) menor = c;
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("========== RELATÓRIO GERAL DO BANCO ==========\n\n");

	    sb.append("--- Total de contas por tipo ---\n");
	    sb.append("Contas Correntes: ").append(contasCorrente.size()).append("\n");
	    sb.append("Contas Poupança:  ").append(contasPoupanca.size()).append("\n");
	    sb.append("Total geral:      ").append(todas.size()).append("\n\n");

	    sb.append("--- Patrimônio total ---\n");
	    sb.append("R$ ").append(String.format("%.2f", calcularPatrimonioTotal())).append("\n\n");

	    sb.append("--- Conta com maior saldo ---\n");
	    sb.append("Titular: ").append(maior.getTitular().getNome()).append("\n");
	    sb.append("Conta:   ").append(maior.getNumeroConta()).append("\n");
	    sb.append("Saldo:   R$ ").append(String.format("%.2f", maior.getSaldo())).append("\n\n");

	    sb.append("--- Conta com menor saldo ---\n");
	    sb.append("Titular: ").append(menor.getTitular().getNome()).append("\n");
	    sb.append("Conta:   ").append(menor.getNumeroConta()).append("\n");
	    sb.append("Saldo:   R$ ").append(String.format("%.2f", menor.getSaldo()));

	    JOptionPane.showMessageDialog(null, sb.toString());
	    
	}
	

	public String menu() {
		return "===== SISTEMA BANCÁRIO =====\n" +
                "1 - Cadastrar Conta Corrente\n" +
                "2 - Cadastrar Conta Poupança\n" +
                "3 - Depositar\n" +
                "4 - Sacar\n" +
                "5 - Consultar Saldo\n" +
                "6 - Exibir Extrato da Conta\n" +
                "7 - Exibir Histórico de Transações\n" +
                "8 - Listar Todas as Contas\n" +
                "9 - Relatório Geral do Banco\n" +
                "0 - Encerrar Sistema";
	}

	public List<ContaCorrente> getContasCorrente() {
		return contasCorrente;
	}

	public void setContasCorrente(List<ContaCorrente> contasCorrente) {
		this.contasCorrente = contasCorrente;
	}

	public List<ContaPoupanca> getContasPoupanca() {
		return contasPoupanca;
	}

	public void setContasPoupanca(List<ContaPoupanca> contasPoupanca) {
		this.contasPoupanca = contasPoupanca;
	}


	
		
	}


