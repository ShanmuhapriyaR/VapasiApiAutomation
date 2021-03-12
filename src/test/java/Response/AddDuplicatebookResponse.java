package Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddDuplicatebookResponse {

    @JsonProperty("Msg")
    private String msg;

    public String getMsg(){
        return msg;
    }
    public void setMsg(){
        this.msg = msg;
    }

}
