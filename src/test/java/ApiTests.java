import io.restassured.RestAssured;
import org.example.model.Pet;
import org.example.requests.PetRequest;
import org.example.spec.RequestSpec;
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

    @Test(priority = 1)
    public void testCreatePet() {

        PetRequest petRequest = new PetRequest(null);

        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());

        int id = 1;
        String name = "name";
        String status = "available";

        Pet newPet = new Pet(1, "name", "available");

        CreatedPet createPetResponse = petRequest.create(newPet)
//        CreatedPet createPetResponse = given()
//                .body(pet)
//                .when()
//                .post("/pet")
                .then().log().all()
                .extract()
                .as(CreatedPet.class);

        Assert.assertEquals(createPetResponse.getId(), id);
        Assert.assertEquals(createPetResponse.getName(), name);
        Assert.assertEquals(createPetResponse.getStatus(), status);
    }

    @Test(priority = 2)
    public void testGetPet() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecOK200());
        PetRequest petRequest = new PetRequest(null);


        int id = 1;
        String status = "available";

        CreatedPet getPetResponse = petRequest.read(id)
//        CreatedPet getPetResponse = given()
//                .when()
//                .get("/pet/" + id)
                .then().log().all()
                .extract()
                .as(CreatedPet.class);

        Assert.assertEquals(getPetResponse.getId(), id);
        Assert.assertEquals(getPetResponse.getStatus(), status);
    }

    @Test(priority = 3)
    public void testDeletePet() {

        int id = 1;

        given()
                .when()
                .delete("/pet/" + id)
                .then().log().all()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(id)));
    }

    @Test(priority = 4)
    public void testCheckThatPetIsDeleted() {
        Specifications.installSpecification(Specifications.requestSpec(BASE_URL), Specifications.responseSpecError404());
        int id = 1;

        given()
                .when()
                .get("/pet/" + id)
                .then().log().all()
                .body("message", equalTo(String.valueOf("Pet not found")));

    }
}
