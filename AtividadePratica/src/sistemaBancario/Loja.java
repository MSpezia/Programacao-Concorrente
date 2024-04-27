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
	
	
	public void pagarSalario() {
		if(contaLoja.getSaldo() >= 1400) {
			for(Funcionario funcionario : funcionarios) {
				if (!funcionario.isFoiPago()) {
	                banco.transferir(contaLoja, funcionario.getContaFuncionario(), contaLoja.getSaldo() >= 1400? 1400 : contaLoja.getSaldo());
	                funcionario.receberSalario();
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
