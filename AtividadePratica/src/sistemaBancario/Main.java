package sistemaBancario;

public class Main {
	public static void main(String[] args) {
		
		//Inicializando as classes
		Banco banco = new Banco();
		Funcionario[] funcionariosLoja1 = new Funcionario[2];
		Funcionario[] funcionariosLoja2 = new Funcionario[2];
		
		for(int i = 0; i < 2; i++) {
			funcionariosLoja1[i] = new Funcionario(new Conta("Funcionario " + (i+1), 0), new Conta("Investimento " + (i+1), 0), banco);
			funcionariosLoja2[i] = new Funcionario(new Conta("Funcionario " + (i+3), 0), new Conta("Investimento " + (i+3), 0), banco);
		}
		
		Loja[] lojas = {
				new Loja(new Conta("Loja 1", 0),banco, funcionariosLoja1),
				new Loja(new Conta("Loja 2", 0),banco, funcionariosLoja2)
		};
		
		Cliente[] clientes = new Cliente[5];
		
		for(int i = 0; i < 5; i++) {
			clientes[i] = new Cliente(new Conta("Cliente " + (i+1), 1000),banco ,lojas);
			clientes[i].start();
		}
		
        for (Cliente cliente : clientes) {
            try {
            	cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
        for (Loja loja : lojas) {
            new Thread(loja::pagarSalario).start();
        }
		
        for(int i = 0; i < 2; i++) {
        	funcionariosLoja1[i].start();
			funcionariosLoja2[i].start();
        }
       

        //c
		//Imprimindo as informações finais
        System.out.println("\nSaldo das Lojas");
        System.out.println("Loja 1: R$ " + lojas[0].getContaLoja().getSaldo());
		System.out.println("Loja 2: R$ " + lojas[1].getContaLoja().getSaldo());
		
		System.out.println("\nSaldo dos Clientes");
		System.out.println("Cliente 1: R$ " + clientes[0].getContaCliente().getSaldo());
		System.out.println("Cliente 2: R$ " + clientes[1].getContaCliente().getSaldo());
		System.out.println("Cliente 3: R$ " + clientes[2].getContaCliente().getSaldo());
		System.out.println("Cliente 4: R$ " + clientes[3].getContaCliente().getSaldo());
		System.out.println("Cliente 5: R$ " + clientes[4].getContaCliente().getSaldo());
		
		System.out.println("\nSALDO dos Funcionários");
		System.out.println("Funcionário 1: R$ " + funcionariosLoja1[0].getContaFuncionario().getSaldo());
		System.out.println("Investimento Funcionário 1: R$ " + funcionariosLoja1[0].getContaInvestimento().getSaldo());
		System.out.println("\nFuncionário 2: R$ " + funcionariosLoja1[1].getContaFuncionario().getSaldo());
		System.out.println("Investimento Funcionário 2: R$ " + funcionariosLoja1[1].getContaInvestimento().getSaldo());
		System.out.println("\nFuncionário 3: R$ " + funcionariosLoja2[0].getContaFuncionario().getSaldo());
		System.out.println("Investimento Funcionário 3: R$ " + funcionariosLoja2[0].getContaInvestimento().getSaldo());
		System.out.println("\nFuncionário 4: R$ " + funcionariosLoja2[1].getContaFuncionario().getSaldo());
		System.out.println("Investimento Funcionário 4: R$ " + funcionariosLoja2[1].getContaInvestimento().getSaldo());
		
		
	}
	
	
}
