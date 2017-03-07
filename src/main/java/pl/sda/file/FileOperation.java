package pl.sda.file;

import com.sun.javafx.UnmodifiableArrayList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperation {
    public static void main(String[] args) throws IOException {

//        String message = "Hello World";
        File file = new File("C:\\Users\\RENT\\Desktop\\test.txt");
//        exampleReadFromFile(file);
//        exampleWriteToFile(message, file);
//        example(file);
//        readLinesFromFile(file);
        List<User> users = readUsersFromFile(file);
        users.forEach(e -> System.out.println(e));
        System.out.println();
        //addUserToFile
        User user = new User();
        user.setFirstName("Arek");
        user.setLastName("Ppppp");
        user.setAge(32);
        addUserToFile(user, file);
        users = readUsersFromFile(file);
        users.forEach(e -> System.out.println(e));

    }

    public static void addUserToFile(User user, File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.toString());
        }
    }

    private static List<User> readUsersFromFile(File file) throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] split = nextLine.split(" ");
                User user = new User();
                user.setFirstName(split[0]);
                user.setLastName(split[1]);
                user.setAge(new Integer(split[2]));
                users.add(user);
            }
        }
        return users;
    }

    private static void readLinesFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    private static void readNameInOneLine(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String nextLine = scanner.nextLine();
            String[] split = nextLine.split(" ");
            for (int i = 0; i < split.length; i++) {
                System.out.println(split[i]);
            }
        }
    }

    private static void exampleReadFromFile(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            String s = scanner.nextLine();
            System.out.println(s);
        }
    }

    public static void exampleWriteToFile(String message, File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println();
        }
    }
}

