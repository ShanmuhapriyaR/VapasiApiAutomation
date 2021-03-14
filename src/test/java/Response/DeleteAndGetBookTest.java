package Response;

import Request.DeleteBookRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteAndGetBookTest {
    @Test
    //Deleted the book and validated with get by id
    public void deleteBookById() {

        DeleteBookRequest delBookReq = new DeleteBookRequest();
        delBookReq.setID("98");
        RestAssured.baseURI = "http://216.10.245.166";
        Response deleteResponse = given().header("Content-Type", "application/json")
                .body(delBookReq)
                .when().post("/Library/DeleteBook.php").then()
                .statusCode(200).extract().response();
        System.out.println(deleteResponse.asString());

        DeleteBookResponse deleteBookResponse = deleteResponse.as(DeleteBookResponse.class);
        System.out.println(deleteBookResponse.getMsg());
        Assert.assertEquals(deleteBookResponse.getMsg(),"book is successfully deleted","Incorrect");

    }
    @Test
    public void getDeletedBookById(){
        RestAssured.baseURI = "http://216.10.245.166";
        Response GetBookRes = given().log().all()
                .queryParam("ID", "98")
                .header("Content-Type", "application/json")
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(404)
                .extract().response();

        System.out.println("----Get book response by id--- :" +GetBookRes.prettyPrint());
        String result = GetBookRes.prettyPrint().substring(14, 73);
        Assert.assertEquals(result, "The book by requested bookid / author name does not exists!");
    }
}
