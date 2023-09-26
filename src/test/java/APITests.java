import io.restassured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.Console;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class APITests {

	
	@BeforeClass
    public void setup() {
        // Set the base URI of the API
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testGetRequest() {
        RestAssured
            .when()
            .get("/api/users/2")
            .then()
            .statusCode(200) // Expected status code
            .contentType("application/json"); // Expected content type
       
    }
    
    

    @Test
    public void PostRequest() {
    	
    	/*the response returned by this post API is {
    "name": "morpheus",
    "job": "leader"
}
and the response code is 201
*/
    	
    	String requestBody="{\r\n"
    			+ "    \"name\": \"morpheus\",\r\n"
    			+ "    \"job\": \"leader\",\r\n"
    			+ "    \"id\": \"758\",\r\n"
    			+ "    \"createdAt\": \"2023-09-26T06:00:38.639Z\"\r\n"
    			+ "}";
        Response response =RestAssured.given()
.contentType(ContentType.JSON).body(requestBody)            .when()
            .post("/api/users/2");
            
        // Extract the response body as a string
        String responseBody = response.getBody().asString();
System.out.println(responseBody);
     // Get JSON Representation from Response Body 
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document 
        String name = jsonPathEvaluator.get("name");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertTrue(name.equalsIgnoreCase("morpheus"));
        
        String job = jsonPathEvaluator.get("job");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertTrue(job.equalsIgnoreCase("leader"));
        
       
    
    }
    
    @Test
    public void PutRequest()
    {
    	String requestBody="{\r\n"
    			+ "    \"name\": \"morpheus\",\r\n"
    			+ "    \"job\": \"zion resident\"\r\n"
    			+ "}";
    	Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .put("/api/users/2");
                
    	Assert.assertEquals(200,response.statusCode());
    	// Get JSON Representation from Response Body 
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document 
        String name = jsonPathEvaluator.get("name");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertTrue(name.equalsIgnoreCase("morpheus"));
        
        String job = jsonPathEvaluator.get("job");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertTrue(job.equalsIgnoreCase("zion resident"));
        System.out.println(response);
    }
    @Test
    public void patch()
    {
    	String requestBody="{\r\n"
    			+ "    \"name\": \"morpheus\",\r\n"
    			+ "    \"job\": \"zion resident\"\r\n"
    			+ "}";
    	Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .patch("/api/users/2");
                
    	Assert.assertEquals(200,response.statusCode());
    	// Get JSON Representation from Response Body 
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document 
        String name = jsonPathEvaluator.get("name");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertTrue(name.equalsIgnoreCase("morpheus"));
        
        String job = jsonPathEvaluator.get("job");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertTrue(job.equalsIgnoreCase("zion resident"));
        System.out.println(response);
    }
    
    @Test
    public void LoginPost()
    {
    	String requestBody="{\r\n"
    			+ "    \"email\": \"eve.holt@reqres.in\",\r\n"
    			+ "    \"password\": \"cityslicka\"\r\n"
    			+ "}";
    	Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("/api/login");
                
    	Assert.assertEquals(200,response.statusCode());
    	// Get JSON Representation from Response Body 
        JsonPath jsonPathEvaluator = response.jsonPath();
        // Get specific element from JSON document 
   
        
        String Token = jsonPathEvaluator.get("token");
        // Validate if the specific JSON element is equal to expected value
        Assert.assertNotNull(Token);
        System.out.println(Token);
    }
    
    
}



