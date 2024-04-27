package sistemaBancario;

public class Main {
	public static void main(String[] args) {
		
		Banco banco = new Banco();
		
		Funcionario[] funcionariosLoja1 = new Funcionario[2];
		Funcionario[] funcionariosLoja2 = new Funcionario[2];
		
		for(int i = 0; i < 2; i++) {
			funcionariosLoja1[i] = new Funcionario(new Conta("Funcionario " + (i+1), 0), new Conta("Investimento " + (i+1), 0), banco);
			funcionariosLoja1[i].start();
		}
		
		for(int i = 0; i < 2; i++) {
			funcionariosLoja2[i] = new Funcionario(new Conta("Funcionario " + (i+3), 0), new Conta("Investimento " + (i+3), 0), banco);
			funcionariosLoja2[i].start();
		}
		
		
		Loja[] lojas = {
				new Loja(new Conta("Loja 1", 0),banco, funcionariosLoja1),
				new Loja(new Conta("Loja 2", 0),banco, funcionariosLoja2)
		};
		
		Cliente[] clientes = new Cliente[5];
		
		for(int i = 0; i < 5; i++) {
			clientes[i] = new Cliente(new Conta("Cliente " + (i+1), 1000),banco ,lojas);
			System.out.println(clientes[i].getContaCliente().getSaldo());
			clientes[i].start();
		}
		
		for(Cliente cliente: clientes) {
			try {
				cliente.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(Loja loja: lojas) {
			System.out.println(loja.getContaLoja().getSaldo());
			loja.pagarSalario();
		}
		

		
		
		
	}
	
	
}
