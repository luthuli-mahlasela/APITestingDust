package Tests;

import RequestBuilder.ResponseBuilder;
import Utilities.DatabaseConnection;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;

public class ApiTests {

    static String registeredEmail ;

    @BeforeClass
    public void setup() throws SQLException {
        DatabaseConnection.dbConnection();
    }

    @Test(priority = 0)
    public void registerNewUserTest(){
        registeredEmail = Faker.instance().internet().emailAddress();
        ResponseBuilder.registerUserResponse("Dust","Buster",registeredEmail,"Secure@12345678","1deae17a-c67a-4bb0-bdeb-df0fc9e2e526")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("success",equalTo(true));
    }
    @Test(priority = 1)
    public void adminloginTest(){
        ResponseBuilder.loginUserResponse("admin@gmail.com","@12345678")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));


    }

    @Test(dependsOnMethods = "adminloginTest")
    public void approveUserTest(){
        ResponseBuilder.approveUserResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
    @Test(dependsOnMethods = "approveUserTest")
    public void approveAdminUserTest() {
        ResponseBuilder.approveAdminUserResponse("admin")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }
    @Test(dependsOnMethods = "approveAdminUserTest")
    public void loginNewAdminUserTest(){
        ResponseBuilder.loginUserResponse(registeredEmail,"Secure@12345678")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
    @Test(dependsOnMethods = "loginNewAdminUserTest")
    public void adminloginTest2(){

        this.adminloginTest();

    }
    @Test(dependsOnMethods = "adminloginTest2")
    public void deleteNewAdminUserTest(){
        ResponseBuilder.deleteAdminUserResponse()
                .then()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));

    }
}
