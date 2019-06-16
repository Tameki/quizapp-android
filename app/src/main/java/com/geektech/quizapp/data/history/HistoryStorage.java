package com.geektech.quizapp.data.history;

import com.geektech.quizapp.model.QuizResult;

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
    public void getAll() {
        //TODO:
    }

    @Override
    public void deleteAll() {
        //TODO:
    }
}
