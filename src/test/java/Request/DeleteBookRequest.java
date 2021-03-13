package Request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookRequest {
     @JsonProperty("ID")
        private String id;
        public String getID() {
            return id;
        }
        public void setID(String id)
        {
            this.id = id;
        }
    }

