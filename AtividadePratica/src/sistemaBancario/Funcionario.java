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
	
	//Sistema para investir o dinheiro ganho
	public void run() {
		if(contaFuncionario.getSaldo() > 0) {
			double valorInvestimento = contaFuncionario.getSaldo() * 0.2;
			System.out.println(contaFuncionario.getSaldo());
			banco.transferir(contaFuncionario, contaInvestimento, valorInvestimento);
			foiPago = true;
		}
	}
	
	
	public Conta getContaInvestimento() {
		return contaInvestimento;
	}

	public void setContaInvestimento(Conta contaInvestimento) {
		this.contaInvestimento = contaInvestimento;
	}

	public boolean isFoiPago() {
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
