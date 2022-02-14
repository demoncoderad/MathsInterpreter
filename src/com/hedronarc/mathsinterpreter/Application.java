package com.hedronarc.mathsinterpreter;

import java.util.Objects;
import java.util.Scanner;

public class Application {

    public static void main (String[] argv) {

        if (argv.length != 0) System.exit(1);
        else RunPrompt();

        System.exit(0);
    }

    private static void RunPrompt() {

        Scanner scanner = new Scanner(System.in);
        RunExpression runner = new RunExpression();

        for (;;) {
            System.out.print(">>> ");

            String expr_string = scanner.nextLine();
            if (Objects.equals(expr_string, "exit")) break;

            runner.Run(expr_string);
            ErrorHandler.ErrorStatus = false;
        }

        return;

    }

}
