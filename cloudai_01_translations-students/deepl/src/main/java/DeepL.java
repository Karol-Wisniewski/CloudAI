import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.text.translate.UnicodeEscaper;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

class DeepL {

    static final String DEEPL_HOST = "api-free.deepl.com";
    static final String DEEPL_KEY = Objects.requireNonNull(
            System.getenv("DEEPL_KEY"),
            "Set DEEPL_KEY environment variable"
    );
    HttpClient client = HttpClient.newHttpClient();

    String translate(String input, String targetLang) {

        URI url = buildUrl(targetLang);
        HttpRequest.BodyPublisher body = buildRequestBody(input, targetLang);
        HttpRequest request = buildRequest(body, url);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return formatResponse(response);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static URI buildUrl(String targetLang) {
        try {
            return new URIBuilder()
                    // TODO build URI
                    .setScheme("https")
                    .setHost("api-free.deepl.com")
                    .setPath("/v2/translate")
                    .build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Invalid URI", e);
        }
    }

    private HttpRequest.BodyPublisher buildRequestBody(String input, String targetLang) {
        return HttpRequest.BodyPublishers.ofString("""
                {
                    "text": [
                        "%s"
                    ],
                    "target_lang": "%s"
                }
                """.formatted( input, targetLang)  );
        // TODO build request body
        // TODO hint: if you encounter problems with unicode encoding, use new UnicodeEscaper().translate(String)
    }

    private static HttpRequest buildRequest(HttpRequest.BodyPublisher body, URI uri) {
        return HttpRequest.newBuilder()
                // TODO build HTTP request
                .uri(uri)
                .POST(body)
                .header("Authorization", "DeepL-Auth-Key " + DEEPL_KEY)
                .header("Content-Type", "application/json")
                .build();
    }

    private String formatResponse(HttpResponse<String> response) {
        String responseString = Objects.requireNonNull(response.body());
        if (response.headers().map().get("Content-Type").contains("json")) {
            return prettify(responseString);
        }
        return responseString;
    }

    static String prettify(String jsonText) {
        JsonElement json = JsonParser.parseString(jsonText);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
}
