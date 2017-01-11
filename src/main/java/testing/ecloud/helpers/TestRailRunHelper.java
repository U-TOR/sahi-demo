package testing.ecloud.helpers;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

public class TestRailRunHelper {

    // TODO: Add TestRail creds

    private final static String LOGIN = "";
    private final static String PASSWORD = "";
    private final static String CREDENTIALS = Credentials.basic(LOGIN, PASSWORD);
    private final static String TESTRAIL_URL = "";
    private final static int PROJECT_ID = 2;

    private final static MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json");
    private final static OkHttpClient client = new OkHttpClient();

    private static int testRunId;

    private static TestRailRunHelper ourInstance = null;

    public static TestRailRunHelper getInstance() {
        if(ourInstance == null) {
            ourInstance = new TestRailRunHelper();
        }
        return ourInstance;
    }

    private TestRailRunHelper() {
    }

    public void startRun(String title) throws JSONException, IOException {
        JSONObject data = new JSONObject();
        data.put("name", title);
        data.put("include_all", true);

        String trailSuite = System.getProperty("trail.suite");
        if (trailSuite != null && !trailSuite.isEmpty()) {
            data.put("suite_id", Integer.parseInt(trailSuite));
        }

        StringWriter out = new StringWriter();
        data.write(out);

        Request request = new Request.Builder()
                .url(TESTRAIL_URL + "add_run/" + PROJECT_ID)
                .header("Authorization", CREDENTIALS)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, out.toString()))
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        JSONObject resp = new JSONObject(response.body().string());
        testRunId = resp.getInt("id");
    }

    public void setTestResult(int caseId, TestRailStatus status, String comment, String defects) throws JSONException, IOException {
        JSONObject data = new JSONObject();
        data.put("status_id", status.statusId);
        data.put("comment", comment);
        data.put("defects", defects);

        StringWriter out = new StringWriter();
        data.write(out);

        Request request = new Request.Builder()
                .url(TESTRAIL_URL + "/add_result_for_case/" + testRunId + "/" + caseId)
                .header("Authorization", CREDENTIALS)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, out.toString()))
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
    }
}
