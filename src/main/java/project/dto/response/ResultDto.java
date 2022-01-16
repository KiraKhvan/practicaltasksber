package project.dto.response;

public class ResultDto {

    private Object data;

    private String result;

    public ResultDto(Object data) {
        this.data = data;
    }

    public ResultDto(boolean result) {
        if (result) {
            this.result = "success";
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        if (result != null) {
            return "{" +
                    "\"result\": \"" + result + "\"" +
                    "}";
        } else {
            return "{" +
                    "\"data\":" + data.toString() +
                    "}";
        }
    }
}
