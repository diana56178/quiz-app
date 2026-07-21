package com.flashcards.repository;

import com.flashcards.model.QuizCard;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface QuizCardRepository {
    void save(File file, List<QuizCard> cards) throws IOException;

    List<QuizCard> load(File file) throws IOException;
}
