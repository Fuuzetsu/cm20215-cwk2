package uk.co.fuuzetsu.cm20215cwk2;

public class RecordException extends Exception {
    static final long serialVersionUID = -4692683232675590041L;
    String message;
    public RecordException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
