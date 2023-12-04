package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Pattern;

public class HackerNews {
    private static final Pattern PATTERN = Pattern.compile("(\"title\":\"([^\"]*)\")");

    public static long[] hackerNewsTopStories() {
        var uri = URI.create("https://hacker-news.firebaseio.com/v0/topstories.json");

        return jsonToArray(requestToHackers(uri));
    }

    private static long[] jsonToArray(String json) {
        if (json == null) {
            return new long[0];
        }

        var tmp = json.substring(1, json.length() - 1);
        var split = tmp.split(",");
        long[] result = new long[split.length];

        for (int i = 0; i < result.length; ++i) {
            result[i] = Long.parseLong(split[i]);
        }

        return result;
    }

    public static String news(long id) {
        var uri = URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");

        return getArticleName(requestToHackers(uri));
    }

    private static String getArticleName(String json) {
       try {
           var matcher = PATTERN.matcher(json);
           matcher.find();
           return matcher.group(2);
       } catch (IllegalStateException e) {
           return null;
       }
    }

    private static String requestToHackers(URI uri) {
        var request = HttpRequest.newBuilder(uri).GET().build();

        try (var client = HttpClient.newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    private HackerNews() {}
}
