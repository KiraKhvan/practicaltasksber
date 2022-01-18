package project.dto.response;

public class ResponseDto {

    private Object data;

    private Boolean isSuccess;

    private String resultMessage;

    public ResponseDto(Object data) {
        this.data = data;
    }

    public ResponseDto(Boolean result, String resultMessage) {
        this.isSuccess = result;
        this.resultMessage = resultMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Override
    public String toString() {
        if (this.isSuccess == null) {
            return "{" +
                    "\"data\":" + data.toString() +
                    "}";
        } else if (this.isSuccess) {
            return "{" +
                    "\"result\": \"" + this.resultMessage + "\"" +
                    "}";
        } else {
            return "{" +
                    "\"error\": \"" + this.resultMessage + "\"" +
                    "}";
        }
    }
}
