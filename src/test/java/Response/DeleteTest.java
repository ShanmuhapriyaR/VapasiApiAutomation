package Response;
//import org.json.simple.JSONObject;

import Request.DeleteBookRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteTest {
    @Test
    public void deleteBook(String id)
    {
        //JSONObject requestParamsDelete = new JSONObject();
        //requestParamsDelete.put("ID",id);
        DeleteBookRequest delBookReq = new DeleteBookRequest();
        delBookReq.setID("1244");
        RestAssured.baseURI = "http://216.10.245.166";
        Response deleteResponse = given().header("Content-Type","application/json")
                .body(delBookReq.getID())
                .when().post("/Library/DeleteBook.php").then()
                .statusCode(200).extract().response();
        System.out.println(deleteResponse.asString());

    }
}
