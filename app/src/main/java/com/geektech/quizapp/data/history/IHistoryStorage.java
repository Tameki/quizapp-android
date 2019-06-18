package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.List;

public interface IHistoryStorage {

    int saveQuizResult(QuizResult quizResult);

    QuizResult getQuizResult(int id);

    void deleteQuizResult(int id);

    LiveData<List<QuizResult>> getAll();

    LiveData<List<ShortQuizResult>> getAllShort();

    void deleteAll();

}
