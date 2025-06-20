package com.exercise;

import java.math.*;
import java.util.*;

public class SalaryIncrease1048 {
    static BigDecimal increasedAmount;
    static BigDecimal increasedRatePercent;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rawSalary = scanner.nextLine();

        BigDecimal salary = validateAndParserSalary(rawSalary);
        BigDecimal newSalary = getIncreasedSalary(salary);

        System.out.println("Novo salario: " + newSalary.toString());
        System.out.println("Reajuste ganho: " + increasedAmount.toString());
        System.out.println("Em percentual: " + increasedRatePercent.toString() + " %");
    }

    private static BigDecimal validateAndParserSalary(String rawSalary) {
        BigDecimal salary = new BigDecimal(rawSalary);

        if (salary.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Salario menor que zero nao permitido!");

        if (salary.scale() < 2) System.out.println("Salario com MENOS de duas casas decimais. Preenchendo casas decimais faltantes...");
        if (salary.scale() > 2) System.out.println("Salario com MAIS de duas casas decimais. Truncando casa decimais excedentes...");

        return salary.setScale(2, RoundingMode.DOWN);
    }

    private static BigDecimal getIncreasedSalary(BigDecimal salary) {
        /*
            Escolha LinkedHashMap, pois é a única implementação da interface Map que garante a ordem de inserção
            fazendo dela melhor escolha para iterações na ordem de inserção

            Criar map dessa forma (instanciar vazio com "new ..." e inserir elementos com .put) resulta em:
            mais performance e leveza, só instancia um Map e não tem overhead de classes extras
        */
        Map<BigDecimal, BigDecimal> salaryRanges = new LinkedHashMap<>();
        salaryRanges.put(new BigDecimal("0.15"), new BigDecimal("400.00"));
        salaryRanges.put(new BigDecimal("0.12"), new BigDecimal("800.00"));
        salaryRanges.put(new BigDecimal("0.10"), new BigDecimal("1200.00"));
        salaryRanges.put(new BigDecimal("0.07"), new BigDecimal("2000.00"));

        for (Map.Entry<BigDecimal, BigDecimal> entry : salaryRanges.entrySet()) {
            if (salary.compareTo(entry.getValue()) > 0) {
                continue;
            }

            setValues(entry.getKey(), salary);
            return salary.add(increasedAmount).setScale(2, RoundingMode.DOWN);
        }

        setValues(new BigDecimal("0.04"), salary);
        return salary.add(increasedAmount).setScale(2, RoundingMode.DOWN);
    }

    private static void setValues(BigDecimal increasedRate, BigDecimal salary) {
        increasedRatePercent = increasedRate.multiply(BigDecimal.valueOf(100)).setScale(0);
        increasedAmount = salary.multiply(increasedRate).setScale(2, RoundingMode.DOWN);
    }
}