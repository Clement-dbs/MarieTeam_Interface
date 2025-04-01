package marieteam.marieteam_v2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class APIController {

    public JSONArray getAllBoats() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/bateau/all")
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String responseBodyString = response.body().string();
            JSONParser parser = new JSONParser();

            // Convertir la réponse (String ) en tableau (JSON)
            JSONArray boatsArray = (JSONArray) parser.parse(responseBodyString);
            return boatsArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getAllSecteurs() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/secteur/all")
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String responseBodyString = response.body().string();
            JSONParser parser = new JSONParser();

            // Convertir la réponse (String ) en tableau (JSON)
            JSONArray sectorsArray = (JSONArray) parser.parse(responseBodyString);
            return sectorsArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}