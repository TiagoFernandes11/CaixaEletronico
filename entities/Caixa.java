package entities;

import java.util.Scanner;

public class Caixa {
    private Conta[] contas = new Conta[10];
    private int tamanhoContas = 0;
    private Conta contaCliente;
    private boolean logado = false;

    public Caixa() {
        this.contas[0] = new Conta("exemp@exemp.com", "123", 1000);
        this.contas[1] = new Conta("exemp@exemp.com", "123", 1000);
        this.contas[2] = new Conta("exemp@exemp.com", "123", 1000);
        this.contas[3] = new Conta("exemp@exemp.com", "123", 1000);
        this.contas[4] = new Conta("exemp@exemp.com", "123", 1000);
    }

    public void main() {
        String menuConta = "1. Sacar\n2. Depositar\n3. Ver saldo\n4. PIX\n0. Sair";
        Scanner scanner = new Scanner(System.in);
        int resp = -1;
        while (resp != 0) {
            System.out.println(menuConta);
            scanner.nextInt();
            switch (resp) {
                case 1:
                    this.logar();
                    break;
                case 2:
                    System.out.println("depositar todo");
                    break;
                case 3:
                    System.out.println("ver saldo todo:");
                    break;
                case 4:
                    System.out.println("todo pix");
                    break;
                case 0:
                    System.out.println("break dos breaks todo");
                    break;
            }
        }
        scanner.close();
    }

    public void pix(String emailDestinatario, Double valorPix) {
        if (emailExiste(emailDestinatario) && this.logado && this.contaCliente.getSaldo() < valorPix) {
            for (int i = 0; i < this.contas.length; i++) {
                if (emailDestinatario.equals(this.contas[i].getEmail())) {
                    this.contaCliente.setSaldo(this.contaCliente.getSaldo() - valorPix);
                    this.contas[i].setSaldo(this.contas[i].getSaldo() + valorPix);
                }
            }
        }
    }

    public boolean emailExiste(String email) {
        for (int i = 0; i < this.contas.length; i++) {
            if (email.equals(this.contas[i].getEmail())) {
                return true;
            }
        }
        return false;
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

    private void autenticar(String email, String senha) {
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
        Conta[] temp = new Conta[this.contas.length + 5];
        for (int i = 0; i < this.contas.length; i++) {
            temp[i] = contas[i];
        }
        this.contas = temp;
    }

}
