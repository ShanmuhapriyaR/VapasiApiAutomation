package Response;

import Request.AddBookRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBookTest {
    @Test
//Added a book and validated with get by id
    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("aaradhya");
        addBookRequest.setIsbn("112");
        addBookRequest.setAisle("98");
        addBookRequest.setAuthor("Beverly");

        Response addBookResponse = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();
        //System.out.println(addBookResponse.prettyPrint());

        AddBookResponse addBookRes = addBookResponse.body().as(AddBookResponse.class);
        addBookRes.getMsg();
        Assert.assertEquals(addBookRes.getMsg(), "successfully added");

        Response GetBookRes = given().log().all()
                .queryParam("ID", "11298")
                .header("Content-Type", "application/json")
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        System.out.println("----Get book response by id--- :" + GetBookRes.prettyPrint());
        GetBookResponse[] getBook = GetBookRes.as(GetBookResponse[].class);
        Assert.assertEquals(getBook[0].getAuthor(), "Beverly", "Invalid");

    }


    @Test
    //Added the same book and validated with get by id
    public void addSameBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("aaradhya");
        addBookRequest.setIsbn("112");
        addBookRequest.setAisle("98");
        addBookRequest.setAuthor("Beverly");
        Response addSameBookResponse = given().header("Content-Type", "application/json")
                .body(addBookRequest)
                .when().post("/Library/Addbook.php")
                .then().log().all()
                .assertThat().statusCode(404)
                .extract().response();
        AddDuplicatebookResponse addSameBookRes = addSameBookResponse.body().as(AddDuplicatebookResponse.class);
        addSameBookRes.getMsg();
        Assert.assertEquals(addSameBookRes.getMsg(), "Add Book operation failed, looks like the book already exists");

        Response GetBookRes = given().log().all()
                .queryParam("ID", "11298")
                .header("Content-Type", "application/json")
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200)
                .extract().response();

        System.out.println("----Get book response by id--- :" + GetBookRes.asString());
        GetBookResponse[] getBook = GetBookRes.as(GetBookResponse[].class);
        Assert.assertEquals(getBook[0].getAuthor(), "Beverly", "Invalid");



    }
}










