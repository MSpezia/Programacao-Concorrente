package sistemaBancario;

public class Funcionario extends Thread{

	private Conta contaFuncionario;
	
	public Funcionario(Conta contaFuncionario) {
		this.contaFuncionario = contaFuncionario;
	}

	public Conta getContaFuncionario() {
		return contaFuncionario;
	}

	public void setContaFuncionario(Conta contaFuncionario) {
		this.contaFuncionario = contaFuncionario;
	}

}
