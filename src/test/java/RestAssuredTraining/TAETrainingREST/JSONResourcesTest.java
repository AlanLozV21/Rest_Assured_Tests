package RestAssuredTraining.TAETrainingREST;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.github.fge.jsonschema.main.JsonSchema;

public class JSONResourcesTest {
	private String POSTS_URL = "http://jsonplaceholder.typicode.com/posts";
	private String COMMENTS_URL = "http://jsonplaceholder.typicode.com/comments";
	private String ALBUMS_URL = "http://jsonplaceholder.typicode.com/albums";
	private String PHOTOS_URL = "http://jsonplaceholder.typicode.com/photos";
	private String TODOS_URL = "http://jsonplaceholder.typicode.com/todos";
	private String USERS_URL = "http://jsonplaceholder.typicode.com/users";
	
	@Test
	public void testPostsResources() {
		given().get(POSTS_URL).then().
		statusCode(200).log().all();
	}
	
	@Test
	public void testCommentsResources() {
		given().get(COMMENTS_URL).then().
		statusCode(200).log().all();
	}
	
	@Test
	public void testAlbumsResources() {
		given().get(ALBUMS_URL).then().
		statusCode(200).log().all();
	}
	
	@Test
	public void testPhotosResources() {
		given().get(PHOTOS_URL).then().
		statusCode(200).log().all();
	}
	
	@Test
	public void testTodosResources() {
		given().get(TODOS_URL).then().
		statusCode(200).log().all();
	}
	
	@Test
	public void testUsersResources() {
		given().get(USERS_URL).then().
		statusCode(200).log().all();
	}
}