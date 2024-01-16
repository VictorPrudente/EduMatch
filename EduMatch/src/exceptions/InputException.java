package exceptions;

public class InputException extends RuntimeException{

    public InputException(String message) {
        super("\n\u001B[31mOpção Inválida. Retornando ao menu principal.\u001B[0m\n");
    }
}
