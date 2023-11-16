package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;

public class HackerNews {
    public static long[] hackerNewsTopStories() {
        var request = HttpRequest.newBuilder(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json")).GET().build();

        try (var client = HttpClient.newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();
            return jsonToArray(result);
        } catch (InterruptedException | IOException e) {
            return new long[0];
        }
    }

    private static long[] jsonToArray(String json) {
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
        var request = HttpRequest.newBuilder(uri).GET().build();

        try (var client = HttpClient.newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return getArticleName(response.body());
        } catch (IOException | InterruptedException e) {
            return null;
        }
    }

    private static String getArticleName(String json) {
       try {
           var pattern = Pattern.compile("(\"title\":\"([^\"]*)\")");
           var matcher = pattern.matcher(json);
           matcher.find();
           return matcher.group(2);
       } catch (IllegalStateException e) {
           return null;
       }
    }

    private HackerNews() {}
}
