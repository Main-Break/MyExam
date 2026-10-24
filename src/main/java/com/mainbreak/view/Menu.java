package com.mainbreak.view;

import java.util.ArrayList;

public class Menu {

    private int width = 30;
    private char corner = '+';
    private char line = '-';

    public Menu () {}

    public Menu(int width, char corner, char line) {
        this.width = width;
        this.corner = corner;
        this.line = line;
    }

    public void printLine() {

        // Cria o primeiro caractere de canto.
        System.out.print("+");

        // Cria a linha com o tamanho definido pela função.
        for(int i = 0; i < this.width; i++) {
            System.out.print("-");
        }
        // Adiciona o final da linha.
        System.out.println("+");
    }

    public void printHeader(String header) {
        // Imprime o cabeçalho no CLI
        this.printLine();
        System.out.println(header);
        this.printLine();
    }

    public void menuOptions(String title, ArrayList<Option> list) {
        // Mostra o menu de opções.
        this.printHeader(title);

        for(int i = 0; i < list.size(); i++) {

            System.out.printf("[%d] %s \n", list.get(i).num, list.get(i).description );
        }
        this.printLine();
    }
}
