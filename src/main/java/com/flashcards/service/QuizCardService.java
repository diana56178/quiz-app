package com.flashcards.service;

import com.flashcards.model.QuizCard;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface QuizCardService {
    void save(File file, List<QuizCard> cards) throws IOException;

    List<QuizCard> load(File file) throws IOException;
}
