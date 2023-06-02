package entities;

public class Conta {
    private String email;
    private String senha;
    private double saldo;
    
    public Conta() {
    }

    public Conta(String email, String senha, double saldo) {
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
