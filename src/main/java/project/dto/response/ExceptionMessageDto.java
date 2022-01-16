package project.dto.response;

public class ExceptionMessageDto {

    private String error;

    public ExceptionMessageDto(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (error != null) {
            return "{" +
                    "\"error\": \"" + error + "\"" +
                    "}";
        } else {
            return null;
        }
    }
}
