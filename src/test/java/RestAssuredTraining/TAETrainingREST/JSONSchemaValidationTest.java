package RestAssuredTraining.TAETrainingREST;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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
			{ "20", 20, 2, "doloribus ad provident suscipit at", "qui consequuntur ducimus possimus quisquam amet similique\nsuscipit porro ipsam amet\neos veritatis officiis exercitationem vel fugit aut necessitatibus totam\nomnis rerum consequatur expedita quidem cumque explicabo"},
			{ "50", 50, 5, "repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem", "error suscipit maxime adipisci consequuntur recusandae\nvoluptas eligendi et est et voluptates\nquia distinctio ab amet quaerat molestiae et vitae\nadipisci impedit sequi nesciunt quis consectetur"},
			{ "100", 100, 10, "at nam consequatur ea labore ea harum", "cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut"},
		};
	}
	
	@Test(dataProvider = "PostValidationTest")
	public void testPostsValidation(String resourceParam, int idValue, int userIdValue, String title, String body) {
		Response response = given().baseUri("http://jsonplaceholder.typicode.com").pathParam("id", resourceParam).when().get("posts/{id}");
		response.then().log().all();		
		
		JsonPath jsonPath = response.jsonPath();
		
		Assert.assertEquals(jsonPath.getInt("id"), idValue);
		Assert.assertEquals(jsonPath.getInt("userId"), userIdValue);
		Assert.assertEquals(jsonPath.getString("title"), title);
		Assert.assertEquals(jsonPath.getString("body"), body);
	}
	
	@Test
	public void testExamplesValidation() {
		String URL_GET_COMMENTS = "https://jsonplaceholder.typicode.com/comments?postId=1";
		String URL_GET_POSTS = "https://jsonplaceholder.typicode.com/posts?userId=1";
		
		given().get(URL_GET_COMMENTS).then().log().all();
		given().get(URL_GET_POSTS).then().log().all();
	}
}