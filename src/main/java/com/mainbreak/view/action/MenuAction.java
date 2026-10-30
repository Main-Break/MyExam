package com.mainbreak.view.action;

import java.util.Scanner;

/**
 * Contrato de uma ação executável a partir de uma opção do menu.
 *
 * @note Papel no Padrão: Command (Command) e Component (Decorator)
 */
public interface MenuAction {
    void execute(Scanner scanner);
}