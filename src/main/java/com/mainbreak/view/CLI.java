package com.mainbreak.view;

import com.mainbreak.view.action.MenuAction;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI extends Menu {

    private ArrayList<Option> listOptions = new ArrayList<Option>();

    private Scanner scanner = new Scanner(System.in);

    public CLI() {}

    public void startCLI() {

        while (true) {
            this.menuOptions("Menu Principal", this.listOptions);

            System.out.print("==>");
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input == 0) {
                System.out.println("Bye :)");
                break;
            }

            Option selected = this.findOptionByNum(input);
            if (selected == null || selected.action == null) {
                System.out.println("Opção inválida.");
                continue;
            }

            selected.action.execute(this.scanner);
        }
    }

    private Option findOptionByNum(int num) {
        for (Option option : this.listOptions) {
            if (option.num == num) {
                return option;
            }
        }
        return null;
    }

    public void addOption(int num, String description, MenuAction action) {
        this.listOptions.add(new Option(num, description, action));
    }
}
