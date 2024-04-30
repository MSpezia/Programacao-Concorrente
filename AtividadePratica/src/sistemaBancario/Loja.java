package sistemaBancario;

public class Loja {
	
	private Conta contaLoja;
	private Banco banco;
	private Funcionario[] funcionarios;
	
	public Loja(Conta contaLoja, Banco banco, Funcionario[] funcionarios) {
		this.contaLoja = contaLoja;
		this.banco = banco;
		this.funcionarios = funcionarios;
	}
	
	//Sistema para pagar os funcionários
	public synchronized void pagarSalario() {
		for(Funcionario funcionario : funcionarios) {
			if(contaLoja.getSaldo() >= 1400) {
				if (!funcionario.isFoiPago()) {
					banco.transferir(contaLoja, funcionario.getContaFuncionario(), 1400);
	            }
			}
			
		}
	}


	public Conta getContaLoja() {
		return contaLoja;
	}


	public void setContaLoja(Conta contaLoja) {
		this.contaLoja = contaLoja;
	}

	
	
}
