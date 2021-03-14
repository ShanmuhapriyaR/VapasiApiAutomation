package Test;

import Request.AddBookRequest;
import Response.AddBookResponse;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class AllMethods {
        public AddBookResponse addBookResponse;
        //Add a book
        public AddBookResponse addBook(String name, String isbn, String aisle, String author)
        {
            AddBookRequest addBookRequest = new AddBookRequest();
            addBookRequest.setName(name);
            addBookRequest.setIsbn(isbn);
            addBookRequest.setAisle(aisle);
            addBookRequest.setAuthor(author);
            Response responseAddBook = given().header("Content-Type","application/json")
                    .body(addBookRequest)
                    .when().post("/Library/Addbook.php").then()
                    .statusCode(200).extract().response();
            AddBookResponse addBookResponse = responseAddBook.body().as(AddBookResponse.class);
            return  addBookResponse;
        }

        //Get book by id
        public Response getBookByID(String id)
        {
            Response getBookResponse = given().queryParam("ID",id)
                    .header("Content-Type","application/json")
                    .when().get("/Library/GetBook.php").then()
                    .statusCode(200).extract().response();
            return getBookResponse;
        }

        //Try retrieving the deleted book by id
         public Response getDeletedBookByID(String id)
        {
        Response getDeletedBookResponse = given().queryParam("ID",id)
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(404).extract().response();
        return getDeletedBookResponse;
        }

        //get book by author
        public Response getBookByAuthor(String author)
        {
        Response getBookResponse = given().queryParam("AuthorName",author)
                .header("Content-Type","application/json")
                .when().get("/Library/GetBook.php").then()
                .statusCode(200).extract().response();
        return getBookResponse;
        }

        //delete book by id
        public Response deleteBook(String id)
        {
            JSONObject Delete = new JSONObject();
            Delete.put("ID",id);

            Response deleteResponse = given().header("Content-Type","application/json")
                    .body(Delete.toJSONString())
                    .when().post("/Library/DeleteBook.php").then()
                    .statusCode(200).extract().response();
            return  deleteResponse;
        }

    }

