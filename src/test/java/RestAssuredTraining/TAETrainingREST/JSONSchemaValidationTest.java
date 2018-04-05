package RestAssuredTraining.TAETrainingREST;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JSONSchemaValidationTest {
	
	@DataProvider(name = "SchemasValidationTest")
	public Object[][] createSchemaValidationData() {
		return new Object[][] {
			{ "posts", "SchemasPostsFile.json" },
			{ "comments", "SchemasCommentsFile.json" },
			{ "albums", "SchemasAlbumsFile.json" },
			{ "photos", "SchemasPhotosFile.json" },
			{ "todos", "SchemasTodosFile.json" },
			{ "users", "SchemasUsersFile.json" },
		};
	}
		
	@Test(dataProvider = "SchemasValidationTest")
	public void testSchemasValidation(String resourcePath, String schemaFilePath) {
		when().get("http://jsonplaceholder.typicode.com/" + resourcePath).then().assertThat().
		body(matchesJsonSchema(Paths.get("src/test/resources/" + schemaFilePath).toUri()));
	}
	
	@DataProvider(name = "PostValidationTest")
	public Object[][] createPostValidationData() {
		return new Object[][] {
			{ "posts/{id}", "id", "20", "title", "doloribus ad provident suscipit at"},
			{ "posts/{id}", "id", "50", "title", "repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem"},
			{ "posts/{id}", "id", "100", "title", "at nam consequatur ea labore ea harum"},
			
		};
	}
	
	@Test(dataProvider = "PostValidationTest")
	public void testPostsValidation(String resource, String idParameter, String idValue, String fieldParameter, String fieldValueExpected) {
		Assert.assertEquals(get("http://jsonplaceholder.typicode.com/" + resource, idParameter, idValue, fieldParameter), fieldValueExpected);
	}
	
	@Test
	public void testExamplesValidation() {
		String URL_GET_COMMENTS = "https://jsonplaceholder.typicode.com/comments?postId=1";
		String URL_GET_POSTS = "https://jsonplaceholder.typicode.com/posts?userId=1";
		
		given().get(URL_GET_COMMENTS).then().log().all();
		given().get(URL_GET_POSTS).then().log().all();
	}
}