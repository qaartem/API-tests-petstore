import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {
    private final static String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreatePet() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());

        Integer id = 1;
        String name = "name";
        String status = "status";

        CreatePet pet = new CreatePet(1, "name", "status");

        CreatedPet createPetResponse = given().
                body(pet)
                .when()
                .post("/pet")
                .then().log().all()
                .extract()
                .as(CreatedPet.class);

        Assert.assertEquals(id, createPetResponse.getId());
        Assert.assertEquals(name, createPetResponse.getName());
        Assert.assertEquals(status, createPetResponse.getStatus());
    }

    @Test
    public void TestGetPet() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());

        Integer id = 1;
        String status = "status";

        CreatedPet getPetResponse = given()
                .when()
                .get("/pet/" + id)
                .then().log().all()
                .extract()
                .as(CreatedPet.class);

        Assert.assertEquals(id, getPetResponse.getId());
        Assert.assertEquals(status, getPetResponse.getStatus());
    }

    @Test
    public void TestDeletePet() {

        Integer id = 1;

        given()
                .when()
                .delete("/pet/" + id)
                .then().log().all()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(id)));
    }

    @Test
    public void checkThatPetIsDeleted() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecError404());
        Integer id = 1;

        given()
                .when()
                .get("/pet/" + id)
                .then().log().all()
                .body("message", equalTo(String.valueOf("Pet not found")));

    }
}
