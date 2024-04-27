package sistemaBancario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private final Lock lock = new ReentrantLock();

    public Banco() {}


    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            origem.depositar(valor);
            destino.sacar(valor);
            System.out.println("Transferência de R$ " + valor + " de " + origem.getTitular() + " para " + destino.getTitular());
        } finally {
            lock.unlock();
        }
    }
}
