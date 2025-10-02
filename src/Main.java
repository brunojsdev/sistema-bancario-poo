import services.Banco;
import models.Conta;
import models.ContaCorrente;
import models.ContaPoupanca;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Banco banco = new Banco();

    public static void main(String[] args) {
        System.out.println("--- Bem-vindo ao Sistema Bancário POO ---");
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 9);

        System.out.println("Sistema encerrado. Obrigado!");
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===== Menu Principal =====");
        System.out.println("1. Criar Nova Conta");
        System.out.println("2. Acessar Conta");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next(); // limpa o buffer
        }
        return scanner.nextInt();
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                criarConta();
                break;
            case 2:
                acessarConta();
                break;
            case 9:
                // Sair
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void criarConta() {
        System.out.println("\n--- Criar Nova Conta ---");
        System.out.println("Qual tipo de conta deseja criar?");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.print("Opção: ");

        int tipo = lerOpcao();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o nome do titular: ");
        String titular = scanner.nextLine();

        Conta novaConta = null;
        if (tipo == 1) {
            novaConta = new ContaCorrente(titular);
        } else if (tipo == 2) {
            novaConta = new ContaPoupanca(titular);
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        banco.adicionarConta(novaConta);
        System.out.println("Conta criada com sucesso! Número: " + novaConta.getNumero());
    }

    private static void acessarConta() {
        System.out.print("\nDigite o número da conta: ");
        int numero = lerOpcao();
        scanner.nextLine(); // Consumir a nova linha

        Conta conta = banco.buscarConta(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        menuConta(conta);
    }

    private static void menuConta(Conta conta) {
        int opcao;
        do {
            System.out.println("\n===== Conta #" + conta.getNumero() + " - Titular: " + conta.getTitular() + " =====");
            System.out.println("Saldo Atual: R$ " + String.format("%.2f", conta.getSaldo()));
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Transferir (PIX)");
            System.out.println("4. Investir (Simulação)");
            System.out.println("5. Extrato (Histórico)");
            System.out.println("9. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();
            scanner.nextLine();

            try {
                processarMenuConta(opcao, conta);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 9);
    }

    private static void processarMenuConta(int opcao, Conta conta) {
        double valor;
        switch (opcao) {
            case 1: // Depositar
                System.out.print("Valor do depósito: R$ ");
                valor = scanner.nextDouble();
                conta.depositar(valor);
                System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado.");
                break;
            case 2: // Sacar
                System.out.print("Valor do saque: R$ ");
                valor = scanner.nextDouble();
                conta.sacar(valor);
                System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado.");
                break;
            case 3: // Transferir
                System.out.print("Número da conta destino: ");
                int numDestino = lerOpcao();
                Conta destino = banco.buscarConta(numDestino);
                if (destino == null) {
                    System.out.println("Conta destino não encontrada.");
                    break;
                }
                System.out.print("Valor da transferência (PIX): R$ ");
                valor = scanner.nextDouble();
                conta.transferir(valor, destino);
                System.out.println("Transferência de R$ " + String.format("%.2f", valor) + " para conta #" + numDestino + " (PIX) realizada.");
                break;
            case 4: // Investir (Simulação de aplicação simples)
                System.out.print("Valor para investimento: R$ ");
                valor = scanner.nextDouble();
                conta.investir(valor); // Polimorfismo: o tipo de conta pode influenciar
                break;
            case 5: // Extrato
                System.out.println("\n--- Histórico de Transações ---");
                for (String transacao : conta.getHistorico()) {
                    System.out.println(transacao);
                }
                break;
            case 9:
                // Voltar
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}