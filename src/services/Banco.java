package services;

import models.Conta;
import java.util.HashMap;
import java.util.Map;

public class Banco {
    private final Map<Integer, Conta> contas;

    public Banco() {
        this.contas = new HashMap<>();
    }

    public void adicionarConta(Conta conta) {
        contas.put(conta.getNumero(), conta);
    }

    public Conta buscarConta(int numero) {
        return contas.get(numero);
    }
}