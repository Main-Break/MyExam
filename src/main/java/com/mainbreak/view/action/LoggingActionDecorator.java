package com.mainbreak.view.action;

import java.util.Scanner;

//A LoggingActionDecorator vai registrar o início e o fim de ação utilizando o design patern de padrões estruturais chamado "Decorator" .
//O Decorator vai adicionar novas responsabilidades ou funcionalidades a um objeto sem precisar modificar a sua classe original. 
public class LoggingActionDecorator implements MenuAction {
    //O 'implements MenuAction' vai garantir que o Decorator possa ser utilizado como se fosse qualquer outra ação do menu.
    
    private final MenuAction avisoAcao;
    //'private final MenuAction avisoAcao' vai guardar a acao original que vai ser executada, como cadastrar um médico.

    public LoggingActionDecorator(MenuAction avisoAcao) {
        //'LoggingActionDecorator(MenuAction avisoAcao)' vai receber a ação original pelo construtor.
        this.avisoAcao = avisoAcao;
    }

    @Override
    public void execute(Scanner scanner) {
        //O 'execute(Scanner scanner)' vai imprimir uma mensagem, executar a ação original e imprimir uma outra mensagem ao terminar.
        System.out.println("[LOG] Iniciando a ação.");

        try {
            avisoAcao.execute(scanner);
            System.out.println("[LOG] A ação terminou.");
        } catch (RuntimeException e) {
            System.out.println("[LOG] Erro durante a ação.");
            throw e;
        }
        //O 'try/catch' vai registrar uma mensagem se ocorrer uma exceção do tipo RuntimeException e depois vai repassá-la
    }
}

