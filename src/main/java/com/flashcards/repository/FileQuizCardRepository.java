package com.flashcards.repository;

import com.flashcards.model.QuizCard;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileQuizCardRepository implements QuizCardRepository {
    @Override
    public void save(File file, List<QuizCard> cards) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (QuizCard card : cards) {
                writer.write(card.getQuestion());
                writer.write("/");
                writer.write(card.getAnswer());
                writer.newLine();
            }
        }
    }

    @Override
    public List<QuizCard> load(File file) throws IOException {
        List<QuizCard> cards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                QuizCard card = parseCard(line);
                cards.add(card);
            }
        }

        return cards;
    }

    private QuizCard parseCard(String line) {
        String[] parts = line.split("/", 2);

        String question = parts[0];
        String answer = parts[1];

        return new QuizCard(question, answer);
    }
}
