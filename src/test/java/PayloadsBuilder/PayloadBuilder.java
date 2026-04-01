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

    public static JSONObject RegisterNewUserPayload(String firstName, String lastName, String email, String password, String groupId) {
        JSONObject registerUser = new JSONObject();
        registerUser.put("firstName", firstName);
        registerUser.put("lastName", lastName);
        registerUser.put("email", email);
        registerUser.put("password", password);
        registerUser.put("confirmPassword", password);
        registerUser.put("groupId", groupId);

        return registerUser;
    }

    public static JSONObject ApproveAdminUserPayload(String admin) {
        JSONObject approveAdminUser = new JSONObject();
        approveAdminUser.put("role", admin);

        return approveAdminUser;
    }

    public static JSONObject deleteAdminUserPayload(String userId) {
        JSONObject deleteAdminUser = new JSONObject();
        deleteAdminUser.put("userId", userId);

        return deleteAdminUser;
    }
}
