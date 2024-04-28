package sistemaBancario;

public class Conta {
	private String titular;
    private double saldo;

    public Conta(String titular, double saldo) {
    	this.titular = titular;
        this.saldo = saldo;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
    }

    public synchronized void sacar(double valor) {
    	saldo -= valor;
    }

    public double getSaldo() {
        return saldo;
    }

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
    
    
}
