package Response;

import Request.AddBookRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBookTest {
@Test
    public void addBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setName("parthiv");
        addBookRequest.setIsbn("1987");
        addBookRequest.setAisle("9876");
        addBookRequest.setAuthor("sundar");
        Response addBookResponse =given().header("Content-Type","application/json")
                .body(addBookRequest)
                .when()
                .post("/Library/Addbook.php")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        AddBookResponse addBookRes = addBookResponse.body().as(AddBookResponse.class);
        addBookRes.getMsg();
    Assert.assertEquals(addBookRes.getMsg(),"successfully added");
}
@Test
public void addSameBook(){
    RestAssured.baseURI = "http://216.10.245.166";
    AddBookRequest addBookRequest = new AddBookRequest();
    addBookRequest.setName("parthiv");
    addBookRequest.setIsbn("1987");
    addBookRequest.setAisle("9876");
    addBookRequest.setAuthor("sundar");
    Response addSameBookResponse =given().header("Content-Type","application/json")
            .body(addBookRequest)
            .when()
            .post("/Library/Addbook.php")
            .then().log().all()
            .assertThat()
            .statusCode(404)
            .extract()
            .response();
    AddDuplicatebookResponse addSameBookRes = addSameBookResponse.body().as(AddDuplicatebookResponse.class);
    addSameBookRes.getMsg();
    Assert.assertEquals(addSameBookRes.getMsg(),"Add Book operation failed, looks like the book already exists");
}

}
