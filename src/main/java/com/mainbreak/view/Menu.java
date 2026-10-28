package com.mainbreak.view;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    // Usa a entrada padrão para que o menu possa ser executado em um terminal.
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    // Exibe uma pergunta e devolve a resposta digitada para o controller.
    public String readText(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("A entrada foi encerrada durante a leitura.");
        }
        return scanner.nextLine().trim();
    }

    // Lê uma senha sem exibi-la quando há terminal interativo e oferece fallback para IDEs.
    public String readPassword(String prompt) {
        Console console = System.console();
        if (console != null) {
            char[] password = console.readPassword("%s", prompt);
            if (password == null) {
                throw new IllegalStateException("A entrada foi encerrada durante a leitura da senha.");
            }
            String result = new String(password);
            java.util.Arrays.fill(password, '\0');
            return result;
        }
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new IllegalStateException("A entrada foi encerrada durante a leitura da senha.");
        }
        return scanner.nextLine();
    }

    // Lê uma opção numérica e repete a pergunta enquanto a entrada não estiver no intervalo aceito.
    public int readOption(String prompt, int minimum, int maximum) {
        while (true) {
            String value = readText(prompt);
            try {
                int option = Integer.parseInt(value);
                if (option >= minimum && option <= maximum) {
                    return option;
                }
            } catch (NumberFormatException ignored) {
                // A mensagem abaixo informa como corrigir uma opção inválida.
            }
            showMessage("Digite um número entre " + minimum + " e " + maximum + ".");
        }
    }

    // Lê uma data ISO e solicita novamente até receber um valor válido.
    public LocalDate readDate(String prompt) {
        while (true) {
            String value = readText(prompt);
            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException exception) {
                showMessage("Data inválida. Use o formato AAAA-MM-DD.");
            }
        }
    }

    // Exibe mensagens de resultado e validação para a pessoa usuária.
    public void showMessage(String message) {
        System.out.println(message);
    }
}
