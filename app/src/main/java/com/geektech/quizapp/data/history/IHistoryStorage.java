package com.geektech.quizapp.data.history;

import com.geektech.quizapp.model.QuizResult;

public interface IHistoryStorage {

    int saveQuizResult(QuizResult quizResult);

    QuizResult getQuizResult(int id);

    void deleteQuizResult(int id);

    void getAll();

    void deleteAll();

}
