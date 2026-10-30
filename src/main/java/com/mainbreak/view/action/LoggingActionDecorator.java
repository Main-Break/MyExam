package com.mainbreak.view.action;

import java.util.Scanner;

/**
 * Classe responsável por adicionar registros de execução (logs)
 * às ações do menu, utilizando o padrão estrutural Decorator.
 *
 * O Decorator permite adicionar novas responsabilidades a um
 * objeto sem precisar modificar sua classe original.
 *
 * Nesta implementação, são exibidas mensagens no terminal
 * indicando o início, o término ou a ocorrência de erros
 * durante a execução de uma ação.
 *
 * A classe implementa a interface MenuAction para que possa
 * ser utilizada como qualquer outra ação do menu.
 *
 * @author João
 * @see MenuAction
 */

public class LoggingActionDecorator implements MenuAction {
    
    /**
     * Armazena a ação original que será executada pelo Decorator.
     *
     * O atributo é do tipo MenuAction para permitir que diferentes
     * ações do menu sejam utilizadas.
     */
    private final MenuAction avisoAcao;

    /**
     * Construtor responsável por receber a ação que será decorada.
     *
     * @param avisoAcao ação original que receberá os registros de execução.
     */
    public LoggingActionDecorator(MenuAction avisoAcao) {
        this.avisoAcao = avisoAcao;
    }

     /**
     * Executa a ação original adicionando mensagens de log
     * antes e depois da sua execução.
     *
     * Caso ocorra uma RuntimeException, uma mensagem de erro
     * será exibida e a exceção será repassada.
     *
     * @param scanner objeto responsável pela leitura dos dados
     *                digitados pelo usuário.
     * @throws RuntimeException caso ocorra um erro durante
     *         a execução da ação original.
     */
    @Override
    public void execute(Scanner scanner) {
        System.out.println("[LOG] Iniciando a ação.");

        try {
            avisoAcao.execute(scanner);
            System.out.println("[LOG] A ação terminou.");
        } catch (RuntimeException e) {
            System.out.println("[LOG] Erro durante a ação.");
            throw e;
        }
    }
}

