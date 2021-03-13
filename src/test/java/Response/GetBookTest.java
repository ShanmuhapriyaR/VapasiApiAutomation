package Response;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookTest {
    @Test
    public void getBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        Response GetBookRes = given()
                .log().all()
                .queryParam("ID","19879876")
                .header("Content-Type","application/json")
                .when()
                .get("Library/GetBook.php")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        System.out.println("----Get book response :" +GetBookRes.asString());
        GetBookResponse[] getBook = GetBookRes.as(GetBookResponse[].class);
        Assert.assertEquals(getBook[0].getAuthor(),"sundar","Invalid");


    }

}
