package files;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	// Here we are going to mention the details how to Add Book, how to Get Book
	// Add Book
	@Test(priority = 1, dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().header("Content-Type", "application/json")
				.body(payload.Addbook(isbn, "14")).post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("The add book response value is : " + response);
		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);

		//Get Book
		String getBookResponse = given().queryParam("ID", id)
				.when().get("/Library/GetBook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("The get book response value is : " + getBookResponse);
		JsonPath js2 = ReUsableMethods.rawToJson(getBookResponse);
		String bookName = js2.getString("book_name");
		System.out.println("***************** The Id value is : " + bookName);

		
		String deleteBookResponse = given().header("Content-Type", "application/json").body(payload.Deletebook(id))
				.when().post("/Library/DeleteBook.php")
				.then().assertThat().statusCode(200).extract().response()
				.asString();
		System.out.println("The response value is : " + deleteBookResponse);
	}

	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {{"oafh","1752"}, {"regh","4985"}, {"twio", "5669"}};
	}

}
