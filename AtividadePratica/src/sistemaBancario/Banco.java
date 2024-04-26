package sistemaBancario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	private Lock lock = new ReentrantLock();
	
	public void transferir(Conta origem, Conta destino, double valor) {
		lock.lock();
		try {
			origem.sacar(valor);
			destino.depositar(valor);
			
		} finally {
			lock.unlock();
		}
	}
	
	
}
