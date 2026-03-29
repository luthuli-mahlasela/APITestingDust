package PayloadsBuilder;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

public class PayloadBuilder {




    public static JSONObject loginUserPayload(String email, String password) {
        JSONObject loginUser = new JSONObject();
        loginUser.put("email", email);
        loginUser.put("password", password);

        return loginUser;
    }

    public static JSONObject RegisterNewUserPayload(String firstName, String lastName, String email, String password, String confirmPassword, String groupId) {
        JSONObject registerNewUser = new JSONObject();
        registerNewUser.put("firstName", firstName);
        registerNewUser.put("lastName", lastName);
        registerNewUser.put("email", email);
        registerNewUser.put("password", password);
        registerNewUser.put("confirmedPassword", password);
        registerNewUser.put("groupId", groupId);

        return registerNewUser;

    }

    public static JSONObject ApproveAdminUserPayload(String role) {
        JSONObject approveAdminUser = new JSONObject();
        approveAdminUser.put("admin", role);

        return approveAdminUser;
    }

    public static JSONObject deleteAdminUserPayload(String userId) {
        JSONObject deleteAdminUser = new JSONObject();
        deleteAdminUser.put("userId", userId);

        return deleteAdminUser;
    }
}
