package Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookResponse {
    @JsonProperty("Msg")
    private String msg;

    public String getMsg(){
        return msg;
    }
    public void setMsg(){
        this.msg = msg;
    }
}
