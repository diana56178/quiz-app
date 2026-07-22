package com.flashcards.service;

import com.flashcards.model.QuizCard;
import com.flashcards.repository.FileQuizCardRepository;
import com.flashcards.repository.QuizCardRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileQuizCardService implements QuizCardService {
    private final QuizCardRepository repository = new FileQuizCardRepository();

    @Override
    public void save(File file, List<QuizCard> cards) throws IOException {
        repository.save(file, cards);
    }

    @Override
    public List<QuizCard> load(File file) throws IOException {
        return repository.load(file);
    }
}
