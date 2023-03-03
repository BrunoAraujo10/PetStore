package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class Pet {
    String  uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
    @Test(priority = 1)
    public void incluirPet() throws IOException {
    String jsonBody = lerJson("db/pet1.json");

     given().contentType("application/json").log().all().body(jsonBody)
    .when().post(uri)
    .then().log().all().statusCode(200)
    .body("name", is("helloBob"))
    .body("status", is("available"))
    .body("category.name", is("XPTO2023"))
    .body("tags.name",contains("api"))

     // post
     ;

    }
    @Test(priority = 2)
    public void consultarPet(){
        String petId = "19882023";
        String token =
        given().contentType("application/json").log().all()
        .when().get(uri + "/" + petId).then().log().all().statusCode(200)
        .body("name", is("helloBob"))
        .body("category.name", is("XPTO2023"))
        .body("status", is("available"))
        .extract() .path("category.name");
        System.out.println("O token é " + token);

        // get

     ;
    }


}
