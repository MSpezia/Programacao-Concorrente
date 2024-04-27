package sistemaBancario;

public class Cliente extends Thread{
	private Conta contaCliente;
	private Banco banco;
	private Loja[] lojas;
	private int i = 0;
	
	public Cliente(Conta contaCliente, Banco banco, Loja[] lojas) {
		this.contaCliente = contaCliente;
		this.banco = banco;
		this.lojas = lojas;
	}
	
	public void run() {
        while (true) {
        	if (contaCliente.getSaldo() > 0) {
        		double valor = Math.random() < 0.5 ? 100 : 200;
        		if(valor > contaCliente.getSaldo()) valor =  contaCliente.getSaldo();
            	if(i == 0) {
            		banco.transferir(contaCliente, lojas[i].getContaLoja(), valor);
            		i++;
            	}else {
            		banco.transferir(contaCliente, lojas[i].getContaLoja(), valor);
            		i--;
            	}
        	}else break;
        }
    }

	public Conta getContaCliente() {
		return contaCliente;
	}

	public void setContaCliente(Conta contaCliente) {
		this.contaCliente = contaCliente;
	}
	
	
}
