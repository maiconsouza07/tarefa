package br.edu.univas;

import java.util.ArrayList;

public class ContaBancaria {
    private String titular;
    private String cpf;
    private double saldo;
    private double limiteChequeEspecialTotal;
    private double limiteChequeEspecialAtual;
    private ArrayList<String> extrato;
    private double valorEmJuizo;

    public ContaBancaria(String titular, String cpf, double limiteChequeEspecialTotal) {
        this.titular = titular;
        this.cpf = cpf;
        this.limiteChequeEspecialTotal = limiteChequeEspecialTotal;
        this.limiteChequeEspecialAtual = limiteChequeEspecialTotal;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
        this.valorEmJuizo = 0.0;
    }

    public double getLimiteChequeEspecialTotal() {
        return limiteChequeEspecialTotal;
    }

    public double getLimiteChequeEspecialAtual() {
        return limiteChequeEspecialAtual;
    }

    public String toString() {
        return "Titular: " + titular + " [" + cpf + "]\nSaldo: R$" + saldo + "\nLimite Cheque Especial: R$" + limiteChequeEspecialAtual;
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.saldo += valor;
            extrato.add("Transferência - Enviado R$" + valor + " para " + destino.titular);
        }
    }

    public void transferirPix(ContaBancaria destino, double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.saldo += valor;
            extrato.add("Transferência PIX - Enviado R$" + valor + " para " + destino.titular);
        }
    }

    public String getTitular() {
        return titular + " [" + cpf + "]";
    }

    public void depositar(double valor) {
        if (valorEmJuizo > 0) {
            valor -= valorEmJuizo;
            if (valor < 0)
                valor = 0;
        }

        saldo += valor;
        extrato.add("Depósito - Recebido R$" + valor);
    }

    public ArrayList<String> getExtrato() {
        return extrato;
    }

    public double getValorEmJuizo() {
        return valorEmJuizo;
    }

    public void setValorEmJuizo(double valorEmJuizo) {
        this.valorEmJuizo = valorEmJuizo;
    }

    public void imprimirExtrato() {
        System.out.println("Extrato da Conta Bancária:");
        for (String operacao : extrato) {
            System.out.println(operacao);
        }
    }

    public static void main(String[] args) {
        ContaBancaria conta1 = new ContaBancaria("Marcos", "123.123.123-23", 500.0);
        ContaBancaria conta2 = new ContaBancaria("Maria", "456.456.456-45", 1000.0);

        conta1.depositar(200.0);
        conta1.transferir(conta2, 150.0);

        System.out.println(conta1);
        System.out.println(conta2);

        System.out.println("Extrato da Conta 1:");
        conta1.imprimirExtrato();

        System.out.println("Extrato da Conta 2:");
        conta2.imprimirExtrato();
    }
}
