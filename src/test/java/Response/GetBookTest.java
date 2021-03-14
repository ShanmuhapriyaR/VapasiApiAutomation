package Response;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetBookTest {
    @Test
    public void getBookById() {
        RestAssured.baseURI = "http://216.10.245.166";
        Response GetBookRes = given().log().all()
                .queryParam("ID", "88")
                .header("Content-Type", "application/json")
                .when().get("Library/GetBook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        System.out.println("----Get book response by id--- :" + GetBookRes.asString());
        GetBookResponse[] getBook = GetBookRes.as(GetBookResponse[].class);
        Assert.assertEquals(getBook[0].getAuthor(), "Priya", "Invalid");

    }

    @Test
    public void getBookByAuthorName() {

        RestAssured.baseURI = "http://216.10.245.166";
        Response GetBookRes = given().log().all()
                .queryParam("AuthorName", "priya")
                .header("Content-Type", "application/json")
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        System.out.println("----Get book response by author name--- :" + GetBookRes.asString());
        GetBookResponse[] getBook = GetBookRes.as(GetBookResponse[].class);
        Assert.assertEquals(getBook[0].getName(), "JAVA Book1", "Book not found");
        Assert.assertEquals(getBook[1].getName(), "JAVA Book2", "Book not found");
        Assert.assertEquals(getBook[2].getName(), "JAVA Book3", "Book not found");

       }
    }

