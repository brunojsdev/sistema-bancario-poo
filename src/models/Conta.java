package models;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private static int PROXIMO_NUMERO = 1001;

    private int numero;
    private String titular;
    protected double saldo;
    private final List<String> historico;

    // Construtor
    public Conta(String titular) {
        this.numero = PROXIMO_NUMERO++; 
        this.titular = titular;
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
        adicionarTransacao("Criação de conta");
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public List<String> getHistorico() {
        return historico;
    }

    protected void adicionarTransacao(String descricao) {
        this.historico.add(descricao + " | Saldo atual: R$ " + String.format("%.2f", this.saldo));
    }

    // Métodos Comuns - Reuso de Código
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        this.saldo += valor;
        adicionarTransacao("Depósito de R$ " + String.format("%.2f", valor));
    }

    // Método Abstrato: Deve ser implementado nas classes filhas
    public abstract void sacar(double valor);

    // Reuso de Código
    public void transferir(double valor, Conta destino) {
        this.sacar(valor); // Reutiliza a lógica de saque (com validação de saldo)
        destino.depositar(valor);
        adicionarTransacao("Transferência (PIX) de R$ " + String.format("%.2f", valor) + " para conta #" + destino.getNumero());
    }
    
    // Método Abstrato: Deve ser implementado nas classes filhas
    public abstract void investir(double valor);
}