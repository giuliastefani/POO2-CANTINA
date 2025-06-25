package dao.exceptions;

public class EstoqueInsuficienteException extends Exception {
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}