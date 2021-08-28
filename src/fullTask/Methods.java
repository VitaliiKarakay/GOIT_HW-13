package fullTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

public class Methods {

    public String post(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest
                        .BodyPublishers.ofFile(Paths.get("user.json")))
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String put(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest
                        .BodyPublishers
                        .ofFile(Paths.get("C:\\Users\\Пользователь\\IdeaProjects\\GOIT_HW-13\\src\\task1\\user.json")))
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public int delete(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response =
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode();
    }

    public String get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public void tasks(String url) throws IOException, InterruptedException {
        String task = get(url);
        Gson gson = new Gson();
        Tasks tasks = gson.fromJson(task, Tasks.class);
        for (Task n: tasks) {
            if (!n.isCompleted())
            System.out.println(n);
        }
    }

    public void getComments(Long id, String userId) throws IOException, InterruptedException {
        Long maxId = 1L;
        String post = get(userId);
        Gson gson = new Gson();
        Posts posts = gson.fromJson(post, Posts.class);
        for (Post n: posts) {
            if (n.getId() > maxId) {
                maxId = n.getId();
            }
        }
        String listOfComments = get("https://jsonplaceholder.typicode.com/posts/"+maxId+"/comments");
        Comments comments = gson.fromJson(listOfComments, Comments.class);

        Gson gsonWrite = new GsonBuilder().setPrettyPrinting().create();
        File commentsList = new File("user-"+id+"-post-"+maxId+"-comments.json");
        String json = gsonWrite.toJson(comments);

        try (FileWriter fileWriter = new FileWriter(commentsList)) {
            fileWriter.write(json);
        }
        System.out.println("\nFile created");
    }

}
