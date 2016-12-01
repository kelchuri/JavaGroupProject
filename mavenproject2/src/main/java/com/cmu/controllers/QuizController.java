package com.cmu.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cmu.handlers.StudentHandler;
import com.cmu.models.Questions;
import com.cmu.models.Quiz;
import com.cmu.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kavya
 */
public class QuizController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static Stage stage;

    private static List<Questions> ques;

    final CheckBox[] cbs = new CheckBox[20];

    @FXML
    private TextArea question;

    @FXML
    private CheckBox option1;

    @FXML
    private CheckBox option2;

    @FXML
    private CheckBox option3;

    @FXML
    private CheckBox option4;

    @FXML
    private VBox multipleAnswers;

    @FXML
    private RadioButton option1m;

    @FXML
    private RadioButton option2m;

    @FXML
    private RadioButton option3m;

    @FXML
    private RadioButton option4m;

    @FXML
    private VBox multipleChoiceAnswers;

    @FXML
    private RadioButton true1;

    @FXML
    private RadioButton false1;

    @FXML
    private VBox trueFalse;

    @FXML
    private TextField fib;

    @FXML
    private Label timer;

    @FXML
    private Button next;

    @FXML
    private Button submitQuizId;

    private static int i = 0;

    private static boolean timerOn;

    static int correct = 0;

    static int incorrect = 0;

    private int course_id;

    private static User user;

    private StudentHandler studentHandler = new StudentHandler();

    public int getIncorrect() {
        return incorrect;
    }

    private List<Quiz> quiz = new ArrayList<Quiz>();
    private Questions currentQuestion;

    private int iMinutes = 0,
            iSeconds = 10;

    public static void setStage(Stage takeQuizStage) {
        QuizController.stage = takeQuizStage;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void setQuestions(List questions) {
        QuizController.ques = questions;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void setTimer(boolean flag) {
        QuizController.timerOn = flag;
    }

    public static void setUser(User user) {
        QuizController.user = user;
    }

    private int getTime() {
        int time = 0;
        for (Questions que : ques) {
            time += que.getTime();
        }
        return time;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(ques.size());
        timer.setVisible(false);
        if (QuizController.timerOn) {
            timer.setVisible(true);
            int time = getTime();
            System.out.println("Time is" + time);

            if (time > 60) {
                iMinutes = time / 60;
                iSeconds = time - (iMinutes * 60);
            } else {
                iSeconds = time;
            }
            System.out.println("Time is" + iMinutes + " " + iSeconds);
            Timer timer = new Timer(true); //set it as a deamon
            timer.schedule(new MyTimer(), 0, 1000);
        }

        displayQuestion(i);

        // TODO
    }

    @FXML
    public void goToNext() throws Throwable {
        calculateAnswer();
        i = i + 1;
        fib.clear();
        submitQuizId.setDisable(true);
        if (i < QuizController.ques.size()) {
            displayQuestion(i);

        } else {
            submitQuizId.setDisable(false);
            System.out.println(QuizController.ques.size() + " " + correct + " " + incorrect);
            next.setDisable(true);
            printQuiz(quiz);
            studentHandler.addIntoQuiz(quiz, QuizController.user, course_id);
        }
    }

    public void clearSelectionMA() {
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
    }

    public void clearSelectionMC() {
        option1m.setSelected(false);
        option2m.setSelected(false);
        option3m.setSelected(false);
        option4m.setSelected(false);
    }

    @FXML
    public void selectTrue() {
        if (true1.isSelected()) {
            false1.setSelected(false);

        }
    }

    @FXML
    public void selectFalse() {
        if (false1.isSelected()) {
            true1.setSelected(false);

        }
    }

    @FXML
    public void selectOption1(ActionEvent event) {
        if (option1m.isSelected()) {
            option2m.setSelected(false);
            option3m.setSelected(false);
            option4m.setSelected(false);
        }
    }

    @FXML
    public void selectOption2(ActionEvent event) {
        if (option2m.isSelected()) {
            option1m.setSelected(false);
            option3m.setSelected(false);
            option4m.setSelected(false);
        }
    }

    @FXML
    public void selectOption3(ActionEvent event) {
        if (option3m.isSelected()) {
            option1m.setSelected(false);
            option2m.setSelected(false);
            option4m.setSelected(false);
        }
    }

    @FXML
    public void selectOption4(ActionEvent event) {
        if (option4m.isSelected()) {
            option1m.setSelected(false);
            option2m.setSelected(false);
            option3m.setSelected(false);
        }
    }

    public void getSelectedOptions(String iscorrect, List options, String option) {
        if (iscorrect.equalsIgnoreCase("correct")) {
            options.add(option);
        }

    }

    public void calculateAnswer() {
        List<String> answers = new ArrayList<String>();
        if (currentQuestion.getQues_type().equals("MA")) {
            getSelectedOptions(currentQuestion.getAnswer1(), answers, currentQuestion.getOption1());
            getSelectedOptions(currentQuestion.getAnswer2(), answers, currentQuestion.getOption2());
            getSelectedOptions(currentQuestion.getAnswer3(), answers, currentQuestion.getOption3());
            getSelectedOptions(currentQuestion.getAnswer4(), answers, currentQuestion.getOption4());
            int count = 0;
            for (String option : answers) {
                System.out.println(option);
                if (option1.getText().equalsIgnoreCase(option) && option1.isSelected()) {
                    count++;
                } else if (option2.getText().equalsIgnoreCase(option) && option2.isSelected()) {
                    count++;
                } else if (option3.getText().equalsIgnoreCase(option) && option3.isSelected()) {
                    count++;
                } else if (option4.getText().equalsIgnoreCase(option) && option4.isSelected()) {
                    count++;
                }
            }
            System.out.println(count + " " + answers.size());
            if (count == answers.size()) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else {
                incorrect++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), false));
            }

        } else if (currentQuestion.getQues_type().equals("MC")) {
            if (option1m.isSelected() && currentQuestion.getAnswer1().equalsIgnoreCase("correct")) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else if (option2m.isSelected() && currentQuestion.getAnswer2().equalsIgnoreCase("correct")) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else if (option3m.isSelected() && currentQuestion.getAnswer3().equalsIgnoreCase("correct")) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else if (option4m.isSelected() && currentQuestion.getAnswer4().equalsIgnoreCase("correct")) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else {
                incorrect++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), false));
            }
        } else if (currentQuestion.getQues_type().equals("FIB")) {
            if (fib.getText().equalsIgnoreCase(currentQuestion.getAnswer())) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else {
                incorrect++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), false));
            }
        } else if (currentQuestion.getQues_type().equals("TF")) {
            if (true1.isSelected() && currentQuestion.getAnswer().equalsIgnoreCase(true1.getText())) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else if (false1.isSelected() && currentQuestion.getAnswer().equalsIgnoreCase(false1.getText())) {
                correct++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), true));
            } else {
                incorrect++;
                quiz.add(new Quiz(currentQuestion.getQues_id(), currentQuestion.getDiff_lvl(), false));
            }
        }

    }

    public void displayQuestion(int i) {
        currentQuestion = QuizController.ques.get(i);
        course_id = currentQuestion.getCrs_id();
        question.setText(currentQuestion.getQues_desc());
        System.out.println(currentQuestion.getQues_desc());

        boolean isCorrect = false;
        if (currentQuestion.getQues_type().equals("MA")) {
            clearSelectionMA();
            System.out.println("In MA");
            multipleAnswers.setVisible(true);
            multipleChoiceAnswers.setVisible(false);
            fib.setVisible(false);
            trueFalse.setVisible(false);
            option1.setText(currentQuestion.getOption1());
            option2.setText(currentQuestion.getOption2());
            option3.setText(currentQuestion.getOption3());
            option4.setText(currentQuestion.getOption4());

        } else if (currentQuestion.getQues_type().equals("MC")) {
            clearSelectionMC();
            System.out.println("In MC");
            multipleAnswers.setVisible(false);
            multipleChoiceAnswers.setVisible(true);
            fib.setVisible(false);
            trueFalse.setVisible(false);
            option1m.setText(currentQuestion.getOption1());
            option2m.setText(currentQuestion.getOption2());
            option3m.setText(currentQuestion.getOption3());
            option4m.setText(currentQuestion.getOption4());

        } else if (currentQuestion.getQues_type().equals("FIB")) {
            multipleAnswers.setVisible(false);
            multipleChoiceAnswers.setVisible(false);
            fib.setVisible(true);
            trueFalse.setVisible(false);

            System.out.println("In FIB");
        } else if (currentQuestion.getQues_type().equals("TF")) {
            multipleAnswers.setVisible(false);
            multipleChoiceAnswers.setVisible(false);
            fib.setVisible(false);
            trueFalse.setVisible(true);
        }
    }

    public void printQuiz(List<Quiz> quiz) {
        for (Quiz que : quiz) {
            System.out.println(que.getIsCorrect());
            System.out.println(que.getQuestionNo());
        }
    }

    @FXML
    public void submitAndViewResults() throws IOException {
        System.out.println("Submit Result Called");
        QuizResultsController.setStage(QuizController.stage);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/QuizResults.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/quizresults.css");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        QuizController.stage.setTitle("JavaFX and Maven");
        QuizController.stage.setScene(scene);
        QuizController.stage.show();
    }

    public class MyTimer extends TimerTask {

        @Override
        public void run() {
            String time = iMinutes + ":" + iSeconds;

            Platform.runLater(() -> {
                timer.setText(time);
            });

            if (iSeconds < 1) {
                if (iMinutes < 1) {

                    this.cancel();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            submitQuizId.fire();

                        }
                    });
                } else {
                    iMinutes--;
                    iSeconds = 59;
                }
            } else {
                iSeconds--;

            }
        }
    }

}
