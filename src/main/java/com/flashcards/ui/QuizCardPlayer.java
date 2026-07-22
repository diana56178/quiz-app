package com.flashcards.ui;

import com.flashcards.model.QuizCard;
import com.flashcards.service.FileQuizCardService;
import com.flashcards.service.QuizCardService;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class QuizCardPlayer {
    private List<QuizCard> cardList;
    private int currentCardIndex;
    private QuizCard currentCard;
    private JTextArea display;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;
    private final QuizCardService service = new FileQuizCardService();

    public static void main(String[] args) {
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }


    public void go() {
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);

        display = new JTextArea(10, 20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEnabled(false);

        JScrollPane scroller = new JScrollPane(display);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scroller);

        nextButton = new JButton("Show Question");
        nextButton.addActionListener(e -> nextCard());
        mainPanel.add(nextButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(e -> open());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    private void nextCard() {
        if (isShowAnswer) {
          display.setText(currentCard.getAnswer());
          nextButton.setText("Next Card");
          isShowAnswer = false;
        } else {
            if (currentCardIndex < cardList.size()) {
                showNextCard();
            } else {
                display.setText("That was last card");
                nextButton.setEnabled(false);
            }
        }
    }

    private void open() {
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.showOpenDialog(frame);

        File file = fileOpen.getSelectedFile();

        if (file == null) {
            return;
        }

        loadFile(file);
    }

    private void loadFile(File file) {
        currentCardIndex = 0;

        try {
            cardList = service.load(file);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Couldn't load file.",
                    "Load error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        showNextCard();
    }

    private void showNextCard() {
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("ShowAnswer");
        isShowAnswer = true;
    }
}
