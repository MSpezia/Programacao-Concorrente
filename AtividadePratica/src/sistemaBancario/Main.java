package sistemaBancario;

public class Main {
	public static void main(String[] args) {
		
		Banco banco = new Banco();
		
		Funcionario[] funcionarios = new Funcionario[4];
		
		for(int i = 0; i < 4; i++) {
			funcionarios[i] = new Funcionario(new Conta("Funcionario " + (i+1), 0), new Conta("Funcionario " + (i+1), 0), banco);
			funcionarios[i].start();
		}
		
		
		Loja[] lojas = {
				new Loja(new Conta("Loja 1", 0),banco, funcionarios),
				new Loja(new Conta("Loja 2", 0),banco, funcionarios)
		};
		
		Cliente[] clientes = new Cliente[5];
		
		for(int i = 0; i < 5; i++) {
			clientes[i] = new Cliente(new Conta("Cliente " + (i+1), 1000),banco ,lojas);
			clientes[i].start();
		}
		

		

		
		
		
	}
	
	
}
