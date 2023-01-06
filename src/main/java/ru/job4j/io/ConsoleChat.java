package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private Random random = new Random();
    private List<String> ansers;

    public String getBotFraze() {
        return ansers.get(random.nextInt(ansers.size()));
    }

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> chatLog = new ArrayList<>();
        String botState = CONTINUE;

        ansers = readPhrases();
        System.out.println("Привет, я чат-бот.");
        while (!OUT.equals(botState)) {
            String userFraze = scanner.nextLine();
            chatLog.add(userFraze);

            if (STOP.equals(userFraze) || CONTINUE.equals(userFraze) || OUT.equals(userFraze)) {
                botState = userFraze;
            }

            if (CONTINUE.equals(botState)) {
                String botFraze = getBotFraze();
                System.out.println(botFraze);
                chatLog.add(botFraze);
            }
        }
        saveLog(chatLog);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatLog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
