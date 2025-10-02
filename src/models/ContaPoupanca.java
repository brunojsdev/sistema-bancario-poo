package models;

public class ContaPoupanca extends Conta {
    private static final double TAXA_RENDIMENTO = 0.005; // 0.5% ao mês

    public ContaPoupanca(String titular) {
        super(titular);
        adicionarTransacao("Conta Poupança criada.");
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        // Não permite saldo negativo
        if (this.saldo < valor) {
            throw new IllegalArgumentException("Saldo insuficiente. O saque excede o saldo atual.");
        }

        this.saldo -= valor;
        adicionarTransacao("Saque de R$ " + String.format("%.2f", valor));
    }
    
    @Override
    public void investir(double valor) {
        this.sacar(valor); 
        double rendimentoSimulado = valor * TAXA_RENDIMENTO;
        this.saldo += rendimentoSimulado; 
        adicionarTransacao("Investimento de R$ " + String.format("%.2f", valor) + " (CP: Rendimento R$ " + String.format("%.2f", rendimentoSimulado) + ")");
        System.out.println("Investimento de R$ " + String.format("%.2f", valor) + " realizado da Conta Poupança, rendimento simulado: R$ " + String.format("%.2f", rendimentoSimulado));
    }
}