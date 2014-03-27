package uk.co.fuuzetsu.cm20215cwk2;

public class UnparsableRecordFile extends Exception {
    static final long serialVersionUID = -3300178900752492585L;

    private String fileName;
    private Integer lineNumber;
    private String lineContent;

    public UnparsableRecordFile(String fileName, Integer lineNumber, String lineContent) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
    }

    public String getMessage() {
        return String.format("Failed to parse %s:%d:%s",
                             fileName, lineNumber, lineContent);
    }
}
