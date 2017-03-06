package pl.sda.postman;

import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.messages.CreateUserRequest;
import pl.sda.messages.CreateUserResponse;
import pl.sda.messages.GetUserResponse;
import pl.sda.utils.HttpUtils;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8081/sda-json/json";

        List<String> userId = new ArrayList<>();
        while (true) {
            Scanner user = new Scanner(System.in);
            System.out.println("1. Dodaj użytkownika");
            System.out.println("2. Wyswietl id urzytkownika");
            System.out.println("3 Wyświelt dane uzytkowników");

            String choiceRaw = user.nextLine();
            Integer choice = Integer.parseInt(choiceRaw);

            switch (choice) {
                case 1:
                    CreateUserRequest createUserRequest = new CreateUserRequest();

                    System.out.print("Podaj swoje imie: ");
                    createUserRequest.setName(user.nextLine());
                    System.out.print("Podaj swoj login: ");
                    createUserRequest.setLogin(user.nextLine());
                    System.out.print("Podaj swoj mail: ");
                    createUserRequest.setMail(user.nextLine());

                    ObjectMapper mapper = new ObjectMapper();
                    String request = mapper.writeValueAsString(createUserRequest);

                    String createUserResponse = Sender.createUser(url, request);
                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
                    userId.add(response.getId());
                    break;

                case 2:
                    System.out.println(userId.toString());
                    break;
                case 3:
                    List<String> getUserResponses = new ArrayList<>();
                    for (String id : userId) {
                        getUserResponses.add(Sender.getUser(url, id));
                    }
                    System.out.println(getUserResponses.toString());
                default:
                    break;
            }
        }
    }
}
