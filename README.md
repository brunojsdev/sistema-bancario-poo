# 🏦 Sistema Bancário Orientado a Objetos (POO)

Este projeto simula um sistema bancário simples em Java, construído para aplicar e consolidar os quatro pilares da Programação Orientada a Objetos (POO): **Abstração**, **Encapsulamento**, **Herança** e **Polimorfismo**.

Ele demonstra como as classes se relacionam para criar diferentes tipos de conta (`ContaCorrente` e `ContaPoupanca`) que compartilham funcionalidades, mas se comportam de maneira única.

---

## 💡 Conceitos de POO em Ação

Esta é a parte mais importante do seu `README`, pois documenta seu aprendizado:

| Conceito | Aplicação no Projeto |
| :--- | :--- |
| **Abstração** | A classe **`Conta`** é uma classe abstrata (`abstract class Conta`). Ela define o comportamento básico (como `depositar` e `transferir`) e os métodos obrigatórios (`sacar` e `investir`) que as classes filhas *devem* implementar. |
| **Herança** | As classes **`ContaCorrente`** e **`ContaPoupanca`** estendem a classe `Conta`. Elas herdam atributos como `titular` e `numero`, e reutilizam métodos como `depositar()` e `transferir()`. |
| **Polimorfismo** | Os métodos **`sacar()`** e **`investir()`** são implementados de forma diferente em cada subclasse. A **Conta Corrente** permite saque com cheque especial, e a **Conta Poupança** tem uma regra de saque estrita (sem saldo negativo) e simula rendimento no investimento. |
| **Encapsulamento** | Atributos internos (como `saldo`, `numero` e `historico`) são protegidos (declarados como `private` ou `protected`) e manipulados apenas por meio de métodos públicos (`getters` e `depositar/sacar`). |

---

## ⚙️ Funcionalidades Implementadas

O sistema permite as seguintes operações via terminal:

* **Criação de Contas:** Criação de `ContaCorrente` ou `ContaPoupanca` (ambas derivadas da `Conta` abstrata).
* **Depósito e Saque:** Operações bancárias básicas com validação de saldo.
* **Transferência (PIX):** Reutiliza o método `sacar()` da conta de origem e `depositar()` na conta de destino.
* **Investimento:** Demonstração do polimorfismo, com diferentes resultados para cada tipo de conta.
* **Extrato:** Exibe o histórico de todas as transações realizadas.

---
## 👤 Autor

| Nome | GitHub |
| :--- | :--- |
| Bruno JS | [@brunojsdev](https://github.com/brunojsdev) |
