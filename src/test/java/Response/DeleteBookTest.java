package Response;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookTest {
    @Test
    public void deleteBook(){
        /*DeleteBookRequest delBookReq = new DeleteBookRequest();
        delBookReq.setID("1244");
        RestAssured.baseURI = "http://216.10.245.166";
        Response delResponse;
        delResponse = given()
                .log().all()
                .header("Content-Type","application/json")
                .body(delBookReq)
                .when()
                .post("/Library/DeleteBook.php")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        System.out.println("delete response :" +delResponse.asString());
        DeleteBookResponse deleteBookResponse = delResponse.as(DeleteBookResponse.class);
        System.out.println(deleteBookResponse.getMsg());
        Assert.assertEquals(deleteBookResponse.getMsg(),"book is successfully deleted");*/

        /*DeleteBookRequest delBookReq = new DeleteBookRequest();
        delBookReq.setID("1244");
        RestAssured.baseURI = "http://216.10.245.166";
        Response deleteResponse = given().header("Content-Type","application/json")
                .body(delBookReq.getID())
                .when().post("/Library/DeleteBook.php").then()
                .statusCode(200).extract().response();
        System.out.println(deleteResponse.asString());*/



            JSONObject id = new JSONObject();
            id.put("ID","1244");
            RestAssured.baseURI = "http://216.10.245.166";
            Response deleteResponse = given().header("Content-Type","application/json")
                    .body(id.toJSONString())
                    .when().post("/Library/DeleteBook.php").then()
                    .statusCode(200).extract().response();
            System.out.println(deleteResponse.asString());
        //Response deleteResponse1 = deleteBook(deleteResponse.getId());
        String deleteOutput = deleteResponse.asString().substring(8,36);
        Assert.assertEquals(deleteOutput, "book is successfully deleted", "Delete Book message incorrect");


    }
}

