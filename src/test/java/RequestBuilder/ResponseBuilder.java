package RequestBuilder;

import PayloadsBuilder.PayloadBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import static PayloadsBuilder.PayloadBuilder.RegisterNewUserPayload;
import static common.BaseURIs.baseURL;

public class ResponseBuilder {

    static String authToken;
    static String userId;
    //static String groupId="1deae17a-c67a-4bb0-bdeb-df0fc9e2e526";

    public static Response loginUserResponse(String email, String password){

        String apiPath = "/APIDEV/login";
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .body(PayloadBuilder.loginUserPayload(email, password).toJSONString())
//                .when()
                .post()
                .then().extract().response();
        authToken = response.jsonPath().getString("data.token");
        return response;
    }
    public static Response registerUserResponse(String firstName, String lastName, String email, String password, String groupId) {

        String apiPath = "/APIDEV/register";
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath(apiPath)
                .header("Content-Type", "application/json")
                .body(RegisterNewUserPayload(firstName, lastName, email, password, groupId))
                .log().all()
                .post()
                .then().extract().response();
        userId = response.jsonPath().getString("data.id");
        return response;
    }
    public static Response approveUserResponse(){
        String apiPath ="/APIDEV/admin/users/"+userId+"/approve";
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath(apiPath)
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + authToken)
                .log().all()
                .put()
                .then().extract().response();
//
        return response;
    }
    public static Response approveAdminUserResponse(String admin){
        String apiPath ="/APIDEV/admin/users/userId/role";
        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath(apiPath)
                .basePath(apiPath.replace("userId",userId))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + authToken)
                .body(PayloadBuilder.ApproveAdminUserPayload(admin).toJSONString())
                .when()
                .put().prettyPeek();

        String Message = response.jsonPath().getString("data.role");
        Assert.assertEquals(Message,admin,"Role was updated successfully");
        System.out.println(Message);

        return response;
    }

        public static Response deleteAdminUserResponse(){
            String apiPath ="/APIDEV/admin/users/"+userId;
            Response response = RestAssured.given()
                    .baseUri(baseURL)
                    .basePath(apiPath)
                    .header("Content-Type","application/json")
                   .header("Authorization","Bearer " + authToken)
                    .log().all()
                    .delete();

            return response;
        }
}
