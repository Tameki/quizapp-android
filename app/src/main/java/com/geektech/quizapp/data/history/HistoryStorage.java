package com.geektech.quizapp.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp.model.QuizResult;
import com.geektech.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    private HistoryDao dao;

    public HistoryStorage(HistoryDao historyDao) {
        dao = historyDao;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.get(id);
    }

    @Override
    public void deleteQuizResult(int id) {
        //TODO:
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<ShortQuizResult>> getAllShort() {
        return Transformations.map(dao.getAll(),  quizResults -> {
            ArrayList<ShortQuizResult> shortQuizResults = new ArrayList<>();

            for (QuizResult quizResult : quizResults) {
                shortQuizResults.add(new ShortQuizResult(
                        quizResult.getId(),
                        quizResult.getQuestions().size(),
                        quizResult.getCorrectAnswers(),
                        quizResult.getCreatedAt()
                ));
            }

            return shortQuizResults;
        });
    }

    @Override
    public void deleteAll() {
        //TODO:
    }
}
