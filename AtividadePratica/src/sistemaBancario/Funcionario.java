package sistemaBancario;

public class Funcionario extends Thread{

	private Conta contaFuncionario, contaInvestimento;
	private Banco banco;
	private boolean foiPago = false;
	
	
	public Funcionario(Conta contaFuncionario, Conta contaInvestimento, Banco banco) {
		this.contaFuncionario = contaFuncionario;
		this.contaInvestimento = contaInvestimento;
		this.banco = banco;
	}
	
	public void receberSalario() {
		double valorInvestimento = contaFuncionario.getSaldo() * 0.2;
		banco.transferir(contaFuncionario, contaInvestimento, valorInvestimento);
		foiPago = true;
	}
	
	
	public boolean foiPago() {
		return foiPago;
	}

	public void setFoiPago(boolean foiPago) {
		this.foiPago = foiPago;
	}

	public Conta getContaFuncionario() {
		return contaFuncionario;
	}

	public void setContaFuncionario(Conta contaFuncionario) {
		this.contaFuncionario = contaFuncionario;
	}

}
