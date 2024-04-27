package sistemaBancario;

public class Loja {
	
	private Conta contaLoja;
	
	public Loja(Conta contaLoja) {
		this.contaLoja = contaLoja;
	}
	
	
	public void pagarSalario() {
		if(contaLoja.getSaldo() >= 1400) {
			
			
			
		}
	}


	public Conta getContaLoja() {
		return contaLoja;
	}


	public void setContaLoja(Conta contaLoja) {
		this.contaLoja = contaLoja;
	}

	
	
}
