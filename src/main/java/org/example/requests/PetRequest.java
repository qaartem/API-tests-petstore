package org.example.requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.model.Pet;

import static io.restassured.RestAssured.given;

public class PetRequest extends Request implements CrudInterface<Pet>{
    public PetRequest(RequestSpecification reqSpec) {
        super(reqSpec);
    }

    private static final String PET_ENDPOINT = "/pet";

    @Override
    public Response create(Pet entity) {
        return given()
                .body(entity)
                .when()
                .post(PET_ENDPOINT);
    }

    @Override
    public Response read(int id) {
        return given()
                .when()
                .get(PET_ENDPOINT + "/" + id);
    }

    @Override
    public Object update(int id, Pet entity) {
        return null;
    }

    @Override
    public Object delete(int id) {
        return null;
    }
}
