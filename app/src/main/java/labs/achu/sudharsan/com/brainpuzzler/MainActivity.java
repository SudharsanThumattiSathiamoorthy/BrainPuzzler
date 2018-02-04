package labs.achu.sudharsan.com.brainpuzzler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static labs.achu.sudharsan.com.brainpuzzler.utils.BrainPuzzlerConstants.BRAIN_PUZZLER;
import static labs.achu.sudharsan.com.brainpuzzler.utils.BrainPuzzlerConstants.MAIN_ACTIVITY;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    private List<Integer> answerList = new ArrayList<>();

    private int correctAnswerLocation;

    public void changeToGameLayout(final View view) {
        this.startButton.setVisibility(View.INVISIBLE);

        setContentView(R.layout.game_layout);

        setNextQuestion();
    }

    private void setNextQuestion() {
        // Clear the existing entries in answer list.
        this.answerList.clear();


        final Random random = new Random();
        final int firstNo = random.nextInt(49);
        final int secondNo = random.nextInt(49);

        final TextView questionTextView = findViewById(R.id.questionTextView);

        questionTextView.setText(Integer.toString(firstNo) + " + " + Integer.toString(secondNo));

        final Button resultButton1 = findViewById(R.id.result_button_1);
        final Button resultButton2 = findViewById(R.id.result_button_2);
        final Button resultButton3 = findViewById(R.id.result_button_3);
        final Button resultButton4 = findViewById(R.id.result_button_4);

        correctAnswerLocation = random.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerLocation) {
                answerList.add(firstNo + secondNo);
            } else {

                int incorrectAnswer = random.nextInt(98);

                while (incorrectAnswer == firstNo + secondNo) {
                    incorrectAnswer = random.nextInt(98);
                }

                answerList.add(incorrectAnswer);
            }
        }

        resultButton1.setText(Integer.toString(answerList.get(0)));
        resultButton2.setText(Integer.toString(answerList.get(1)));
        resultButton3.setText(Integer.toString(answerList.get(2)));
        resultButton4.setText(Integer.toString(answerList.get(3)));
    }

    public void verifyAnswer(final View view) {
        Log.i(BRAIN_PUZZLER, (String) view.getTag());

        final String selectedTag = (String) view.getTag();
        final TextView resulTextView = findViewById(R.id.resultTextView);

        if (selectedTag.equals(String.valueOf(correctAnswerLocation))) {
            resulTextView.setText("Correct!!");
            resulTextView.setBackgroundResource(R.color.correctAnswer);

            setNextQuestion();
        } else {
            resulTextView.setText("Wrong!!");
            resulTextView.setBackgroundResource(R.color.wrongAnswer);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        startButton.setVisibility(View.VISIBLE);
    }

    @SuppressLint("LongLogTag")
    public void startGame(final View view) {
        Log.i(MAIN_ACTIVITY, "Game is launched");

        changeToGameLayout(view);
    }
}
