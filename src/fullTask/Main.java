package fullTask;

import java.io.IOException;


public class Main {

    private static final String USERS = "https://jsonplaceholder.typicode.com/users";
    private static final String FifthUser = "https://jsonplaceholder.typicode.com/users/5";
    private static final String KARMEN = "https://jsonplaceholder.typicode.com/users?username=Kamren";
    private static final String TASKS = "https://jsonplaceholder.typicode.com/users/1/todos";


    public static void main(String[] args) throws IOException, InterruptedException {

        Methods user = new Methods();

        System.out.println("Post user:\n" + user.post(USERS) + "\n");
        System.out.println("Put user:\n" + user.put(FifthUser) + "\n");
        System.out.println("Delete response " + user.delete(FifthUser) + "\n");
        System.out.println("List of users:\n" + user.get(USERS) + "\nEnd of List");
        System.out.println("Fifth user by ID:\n" + user.get(FifthUser) + "\n");
        System.out.println("Fifth user by username:\n" + user.get(KARMEN) + "\n");
        user.tasks("https://jsonplaceholder.typicode.com/users/1/todos");
        user.getComments(1L, "https://jsonplaceholder.typicode.com/users/1/posts");
    }
}
