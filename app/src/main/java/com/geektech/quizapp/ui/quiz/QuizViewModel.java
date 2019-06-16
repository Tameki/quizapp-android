package com.geektech.quizapp.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import com.geektech.core.SingleLiveEvent;
import com.geektech.quizapp.App;
import com.geektech.quizapp.data.IQuizRepository;
import com.geektech.quizapp.model.Question;
import com.geektech.quizapp.model.QuizResult;

import java.util.Date;
import java.util.List;

class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    //TODO: Add resultEvent and closeEvent (SingleLiveEvent)

    private int getCorrectAnswersAmount() {
        //TODO: Calculate correct answers
        return 1;
    }

    private void finishQuiz() {
        QuizResult result = new QuizResult(
                0,
                questions.getValue(),
                getCorrectAnswersAmount(),
                new Date()
        );
        int id = App.historyStorage.saveQuizResult(result);


        Log.d("ololo", "Added id = " + id);

        QuizResult addedQuiz = App.historyStorage.getQuizResult(id);
        Log.d("ololo", "" + addedQuiz.getCorrectAnswers() + " " + addedQuiz.getCreatedAt());

        finishEvent.call();

        //TODO: Save result and open result activity
    }

    void loadQuestions(int amount) {
        loading.setValue(true);
        App.quizRepository.getQuestions(amount, new IQuizRepository.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                loading.setValue(false);
                QuizViewModel.this.questions.setValue(questions);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(String message) {
                loading.setValue(false);
                Log.d("ololo", message);
            }
        });
    }

    void onNextClick() {
        int currentPosition = currentQuestionPosition.getValue() + 1;
        if (currentPosition == questions.getValue().size()) {
            finishQuiz();
        } else {
            currentQuestionPosition.setValue(currentPosition);
        }
    }

    void onBackPressed() {
        int currentPosition = currentQuestionPosition.getValue() - 1;
        if (currentPosition >= 0) {
            currentQuestionPosition.setValue(currentPosition);
        } else {
            finishEvent.call();
        }
    }

    void onAnswerSelected(int position, String answer) {
        Log.d("ololo", "On answer selected " + position + " " + answer);
//        questions.getValue().get(position).setSelectedAnswer(answer);
    }

    String getTitle() {
        if (questions.getValue() != null && currentQuestionPosition.getValue() != null) {
            return questions.getValue()
                    .get(currentQuestionPosition.getValue())
                    .getCategory();
        } else {
            return "";
        }
    }

}
