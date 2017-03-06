package pl.sda.postman;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import pl.sda.request.CreateUserRequest;
import pl.sda.request.CreateUserResponse;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> userId = new ArrayList<>();
//        Map<String, CreateUserResponse> mapUser = new HashMap<>();
//        String testID = null;
        while (true) {
            Scanner user = new Scanner(System.in);
            System.out.println("1. Dodaj użytkownika");
            System.out.println("2. Wyswietl id urzytkownika");

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

                    String createUserResponse = Sender.createUser("http://localhost:8081/sda-json/json", request);
                    CreateUserResponse response = mapper.readValue(createUserResponse, CreateUserResponse.class);
                    userId.add(response.getId());
//                    mapUser.put(response.getId(),response);
//                    testID=response.getId();
                    break;

                case 2:
                    System.out.println(userId.toString());
//                    System.out.println(mapUser);
//                    System.out.println(mapUser.get(testID));
                    break;
                default:
                    break;

            }
        }
    }

}
