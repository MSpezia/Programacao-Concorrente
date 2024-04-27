package sistemaBancario;

public class Main {
	public static void main(String[] args) {
		
		Banco banco = new Banco();
		
		Loja[] lojas = {
				new Loja(new Conta("Loja 1", 0)),
				new Loja(new Conta("Loja 2", 0))
		};
		
		Cliente[] clientes = new Cliente[5];
		
		for(int i = 0; i < 5; i++) {
			clientes[i] = new Cliente(new Conta("Cliente " + (i+1), 1000),banco ,lojas);
			clientes[i].start();
		}
		
		Funcionario[] funcionarios = new Funcionario[4];
		
		for(int i = 0; i < 4; i++) {
			funcionarios[i] = new Funcionario(new Conta("Funcionario " + i, 0));
			funcionarios[i].start();
		}
		
		
		
		
	}
	
	
}
