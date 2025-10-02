package models;

public class ContaCorrente extends Conta {
    private static final double LIMITE_CHEQUE_ESPECIAL = 500.0;

    public ContaCorrente(String titular) {
        super(titular);
        adicionarTransacao("Conta Corrente criada. Limite: R$ " + String.format("%.2f", LIMITE_CHEQUE_ESPECIAL));
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        // Permite saldo negativo até o limite
        if (this.saldo + LIMITE_CHEQUE_ESPECIAL < valor) {
            throw new IllegalArgumentException("Saldo insuficiente, mesmo com o cheque especial. Limite disponível: R$ " + String.format("%.2f", (this.saldo + LIMITE_CHEQUE_ESPECIAL)));
        }

        this.saldo -= valor;
        adicionarTransacao("Saque de R$ " + String.format("%.2f", valor));
    }
    
    @Override
    public void investir(double valor) {
        this.sacar(valor); 
        adicionarTransacao("Investimento de R$ " + String.format("%.2f", valor) + " (CC: Sem rendimento simulado)");
        System.out.println("Investimento de R$ " + String.format("%.2f", valor) + " realizado da Conta Corrente. (Simulação simples sem rendimento).");
    }
}