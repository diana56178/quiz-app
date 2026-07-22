package com.flashcards.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilePractice {
    public static void main(String[] args) {
        File file = new File("expenses.txt");

        try {
            writeExpenses(file);
            appendExpense(file, "Coffee", 200);
            printExpenses(file);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    private static void writeExpenses(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Food;450");
            writer.newLine();
            writer.write("Transport;120");
            writer.newLine();
            writer.write("Books;900");
            writer.newLine();
        }
    }

    private static void printExpenses(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int total = 0;
            while ((line = reader.readLine()) != null) {
                total += parseString(line);
            }
            System.out.println("Total: " + total);
        }
    }

    private static int parseString(String line) {
        String[] parts = line.split(";", 2);
        String category = parts[0];
        int amount = Integer.parseInt(parts[1]);
        System.out.println("Category: " + category + ", amount: " + amount);
        return amount;
    }

    private static void appendExpense(
            File file,
            String category,
            int amount
    ) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(category + ";" + amount);
            writer.newLine();
        }
    }
}
