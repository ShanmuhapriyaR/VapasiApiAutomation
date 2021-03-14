package Test;

import Response.AddBookResponse;
import Response.GetBookResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIMethods extends AllMethods {
@Test
    public void APIRestScenarioFive() {
    RestAssured.baseURI = "http://216.10.245.166";

    //---------------------Added three books with same author name--------------------------------------
    AddBookResponse addBookResponse = addBook("There's a Bear on My Chair", "110", "111", "priya");
    Assert.assertEquals(addBookResponse.getMsg(), "successfully added", "Add Book message incorrect");

    addBookResponse = addBook("Harry Potter", "120", "222", "priya");
    Assert.assertEquals(addBookResponse.getMsg(), "successfully added", "Add Book message incorrect");

    addBookResponse = addBook("The Rainbow Fish Swim & Share", "130", "333", "priya");
    Assert.assertEquals(addBookResponse.getMsg(), "successfully added", "Add Book message incorrect");

    //----------------------get book by author name-----------------------------------------------------
    Response getBookResponse = getBookByAuthor("priya");
    GetBookResponse[] book = getBookResponse.as(GetBookResponse[].class);
    Assert.assertEquals(book[0].getName(), "There's a Bear on My Chair", "Incorrect");
    Assert.assertEquals(book[1].getName(), "Harry Potter", "Incorrect");
    Assert.assertEquals(book[2].getName(), "The Rainbow Fish Swim & Share", "Incorrect");
    for (int i = 0; i < book.length; i++) {
        System.out.println("Name of the book :" + book[i + 1].getName());
    }
    System.out.println("No of books by author Priya are :" + book.length);
}
    @Test
    public void APIRestScenarioSix(){

    //------------Added three books with same author name and deleted a book-------------------------

    AddBookResponse addBookRes = addBook("Baby Rhymes", "1", "10", "Rita Williams");
    Assert.assertEquals(addBookRes.getMsg(), "successfully added", "Add Book message incorrect");

    addBookRes = addBook("Baby Sleep Stories", "21", "21", "Rita Williams");
    Assert.assertEquals(addBookRes.getMsg(), "successfully added", "Add Book message incorrect");

    addBookRes = addBook("Alphabets", "33", "33", "Rita Williams");
    Assert.assertEquals(addBookRes.getMsg(), "successfully added", "Add Book message incorrect");

    Response getBookRes = getBookByAuthor("Rita Williams");
    GetBookResponse[] getBook = getBookRes.as(GetBookResponse[].class);
    Assert.assertEquals(getBook[0].getName(), "Baby Rhymes", "Incorrect");
    Assert.assertEquals(getBook[1].getName(), "Baby Sleep Stories", "Incorrect");
    Assert.assertEquals(getBook[2].getName(), "Alphabets", "Incorrect");
    for (int i = 0; i < getBook.length; i++) {
        System.out.println("Name of the book :" + getBook[i + 1].getName());
    }
    System.out.println("No of books by author Rita Williams are :" + getBook.length);

    String id = getBook[0].getIsbn().concat(getBook[0].getAisle());
    Response deleteResponse = deleteBook(id);
    String result = deleteResponse.asString().substring(8, 36);
    Assert.assertEquals(result, "book is successfully deleted", "Incorrect");
    System.out.println("Book with "+id+" is successfully deleted");

    getBookRes = getBookByAuthor("Rita");
    getBook = getBookRes.as (GetBookResponse[].class);
    for(int i=0;i<getBook.length;i++) {
        System.out.println("Name of the book" + getBook[i+1].getName());
    }
    System.out.println("No of books by author Rita are :"+ getBook.length);
    }
}


