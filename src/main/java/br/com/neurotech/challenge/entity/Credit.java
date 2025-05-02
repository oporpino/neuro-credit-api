package br.com.neurotech.challenge.entity;

public interface Credit {
    boolean isEligible(NeurotechClient client);

    double calculateLoan(double amount, int months);
}