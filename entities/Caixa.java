package entities;

import java.util.Scanner;

public class Caixa {
    private Conta[] contas = new Conta[5];
    private int tamanhoContas = 0;
    private Conta contaCliente;
    private boolean logado = false;

    public Caixa() {
    }

    public void pix(){
        //todo
    }

    public void sacar() {
        if (!this.logado) {
            System.out.println("Você precisa estar logado! ");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual valor do saque: ");
        Double valor = sc.nextDouble();
        if (this.contaCliente.getSaldo() < valor) {
            System.out.println("Saldo insuficiente");
            return;
        }
        this.contaCliente.setSaldo(this.contaCliente.getSaldo() - valor);
        verSaldo();
    }

    public void verSaldo() {
        if (logado) {
            System.out.println("Seu saldo é de R$ " + contaCliente.getSaldo());
        } else {
            System.out.println("Você precisa estar logado");
        }
    }

    public void logar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Senha: ");
        String senha = sc.nextLine();
        autenticar(email, senha);
    }

    public void autenticar(String email, String senha) {
        for (int i = 0; i < this.contas.length; i++) {
            if (email.equals(contas[i].getEmail()) && senha.equals(contas[i].getSenha())) {
                this.contaCliente = contas[i];
                this.logado = true;
                System.out.println("Logado com sucesso!");
                return;
            }
        }
    }

    public void depositar() {
        if (!this.logado) {
            System.out.println("Você precisa estar logado! ");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual valor do depósito: ");
        Double valor = sc.nextDouble();
        if (valor <= 0) {
            System.out.println("Valor precisa ser positivo e maior que zero!");
            return;
        }
        contaCliente.setSaldo(contaCliente.getSaldo() + valor);
        System.out.println("Depósito feito com sucesso!");
        verSaldo();
    }

    public Conta criarConta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Email:");
        String email = sc.nextLine();
        System.out.println("Senha: ");
        String senha = sc.nextLine();
        return new Conta(email, senha, 0);
    }

    public void cadastrarConta() {
        if (this.contas.length == tamanhoContas) {
            aumentaCapacidade();
        }
        this.contas[tamanhoContas++] = criarConta();
    }

    private void aumentaCapacidade() {
        Conta[] temp = new Conta[this.contas.length + 1];
        for (int i = 0; i < this.contas.length; i++) {
            temp[i] = contas[i];
        }
        this.contas = temp;
    }

}
