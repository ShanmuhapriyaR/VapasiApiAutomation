package Response;

import Request.DeleteBookRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookTest {
    @Test
    public void deleteBook(){
        DeleteBookRequest delBookReq = new DeleteBookRequest();
        delBookReq.setID("1234123312313244");
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
        DeleteBookResponse deleteBookResponse = delResponse.body().as(DeleteBookResponse.class);
        System.out.println(deleteBookResponse.getMsg());
        Assert.assertEquals(deleteBookResponse.getMsg(),"book is successfully deleted");
    }
}

