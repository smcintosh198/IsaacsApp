package cs410.baseapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Random;


public class PlayGameActivity extends BlaugranaActivity {

    private static final String GAME_PREFERENCES = null;
    private static final int MAX_QUESTIONS = 20;

    private Question[] Questions = new Question[MAX_QUESTIONS];
    int setQuestionIndex = 0;

    TextView question;
    TextView counter;

    ImageButton backButton;
    ImageButton rateButton;
    ImageButton checkAnswerButton;

    ImageButton randomizeButton;
    RadioGroup answersRadioGroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    RadioButton answer4;

    private int numQuestions = 0;
    private int numCorrect = 0;
    private int numIncorrect = 0;
    private int totalAnswered = 0;
    private int questionIndex = 0;
    private int lastQuestionIndex = 0;

    final Context mContext = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        getAppPreferences();
        initGui();
    }

    private void initGui() {
        question = (TextView) findViewById(R.id.gameQuestions);
        counter = (TextView) findViewById(R.id.Counter);
        initCounterView();

        initButtons();
        initRadioButtons();

        try{
            getQuestionsfromXML();
        } catch (Exception e){

        }
        questionIndex = lastQuestionIndex;
        initQuestion();
    }

    private void initCounterView(){
        if (totalAnswered != 0){
            counter.setText("Correctly answered: " + numCorrect + "/" + totalAnswered + " = " + calcRatio()  + "%");
        } else {
            counter.setText("Good Luck!");
        }
    }

    private float calcRatio(){
        float ratio = ((float)numCorrect/totalAnswered) * 100;
        return ratio;
    }

    private void initButtons(){
        backButton = (ImageButton) findViewById(R.id.gameBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rateButton = (ImageButton) findViewById(R.id.gameRateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRateDialog(mContext);
            }
        });

        checkAnswerButton = (ImageButton) findViewById(R.id.gameCheckAnswerButton);
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSelectedRadioButton() == null){
                    CharSequence text = "Please select an answer!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else if (Questions[questionIndex].CorrectAnswer == getSelectedRadioButton()){
                    String correctMsg = "You got it!\n" + Questions[questionIndex].CorrectAnswer + " is the correct answer!";
                    createDialog(correctMsg);
                    numCorrect++;
                    nextQuestion();
                } else {
                    String wrongMsg = "Sorry, that is incorrect. You answered \n" + getSelectedRadioButton() + ". \nWe maintain the answer as: " + Questions[questionIndex].CorrectAnswer;
                    createDialog(wrongMsg);
                    numIncorrect++;
                    nextQuestion();
                }
            }
        });

        randomizeButton = (ImageButton) findViewById(R.id.gameRandomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomIndex = new Random();
                questionIndex = randomIndex.nextInt(MAX_QUESTIONS - 0);
                initQuestion();
                lastQuestionIndex = questionIndex;
                saveAppPreferences();
            }
        });

    }

    private void nextQuestion (){
        answersRadioGroup.clearCheck();
        totalAnswered++;
        if (questionIndex == Questions.length - 1){
            questionIndex = 0;
            lastQuestionIndex = questionIndex;
        } else {
            questionIndex++;
            lastQuestionIndex = questionIndex;
        }
        saveAppPreferences();
        initCounterView();
        initQuestion();
    }
    private void initRadioButtons(){
        answersRadioGroup = (RadioGroup) findViewById(R.id.gameRadioGroup);
        answer1 = (RadioButton) findViewById(R.id.gameRadioAnswer1);
        answer2 = (RadioButton) findViewById(R.id.gameRadioAnswer2);
        answer3 = (RadioButton) findViewById(R.id.gameRadioAnswer3);
        answer4 = (RadioButton) findViewById(R.id.gameRadioAnswer4);

    }

    private void initQuestion(){
        question.setText(Questions[questionIndex].getQuestion());
        answer1.setText(Questions[questionIndex].getAnswer1());
        answer2.setText(Questions[questionIndex].getAnswer2());
        answer3.setText(Questions[questionIndex].getAnswer3());
        answer4.setText(Questions[questionIndex].getAnswer4());
    }

    private String getSelectedRadioButton(){
        final int RB1 = answer1.getId();
        final int RB2 = answer2.getId();
        final int RB3 = answer3.getId();
        final int RB4 = answer4.getId();

        int selectedIndex = answersRadioGroup.getCheckedRadioButtonId();
        if (selectedIndex == RB1) {
            return answer1.getText().toString();
        } else if (selectedIndex == RB2) {
            return answer2.getText().toString();
        } else if (selectedIndex == RB3) {
            return answer3.getText().toString();
        } else if (selectedIndex == RB4) {
            return answer4.getText().toString();
        } else {
            return null;
        }
    }

    public void getAppPreferences() {
        settings = getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE);
        prefEditor = settings.edit();
        prefEditor.putString("UserName", "Shayne McIntosh");
        if (settings.contains("NumQuestions")) {
            numCorrect = settings.getInt("NumQuestions", numQuestions);
        } else {
            prefEditor.putInt("NumQuestions", numQuestions);
            prefEditor.commit();
        }
        if (settings.contains("NumCorrect")) {
            numCorrect = settings.getInt("NumCorrect", numCorrect);
        } else {
            prefEditor.putInt("NumCorrect", numCorrect);
            prefEditor.commit();
        }
        if (settings.contains("NumInCorrect")) {
            numIncorrect = settings.getInt("NumInCorrect",
                    numIncorrect);
        } else {
            prefEditor.putInt("NumInCorrect", numIncorrect);
            prefEditor.commit();
        }
        if (settings.contains("TotalAnswered")) {
            totalAnswered = settings.getInt("TotalAnswered",
                    totalAnswered);
        } else {
            prefEditor.putInt("TotalAnswered", totalAnswered);
            prefEditor.commit();
        }
        if (settings.contains("lastQuestion")) {
            lastQuestionIndex = settings.getInt("lastQuestion", lastQuestionIndex);
        } else {
            prefEditor.putInt("lastQuestion", 0);
            prefEditor.commit();
        }
    }

    public void saveAppPreferences(){
        settings = getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE);
        prefEditor = settings.edit();

        prefEditor.putInt("NumQuestions", numQuestions);
        prefEditor.putInt("TotalAnswered", totalAnswered);
        prefEditor.putInt("NumCorrect", numCorrect);
        prefEditor.putInt("NumInCorrect", numIncorrect);
        prefEditor.putInt("lastQuestion", lastQuestionIndex);
        prefEditor.commit();

    }

    public void getQuestionsfromXML() throws XmlPullParserException,
            IOException {
        String tagname = " ";
        XmlResourceParser xpp = getResources().getXml(R.xml.trivia);
        xpp.next();
        if (xpp == null) {

        } else {
            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagname = xpp.getName();
                } else if (eventType == XmlPullParser.TEXT) {
                    setQuestion(tagname, xpp.getText());

                }
                eventType = xpp.next();

            }
        }
    }


    public void setQuestion(String elementTag, String textIn) {

        if (elementTag.equals("question")) {
            Questions[setQuestionIndex] = new Question();
            Questions[setQuestionIndex].Question = textIn;

        }else if (elementTag.equals("answer1")) {
            Questions[setQuestionIndex].Answer1 = textIn;

        }else if (elementTag.equals("answer2")) {
            Questions[setQuestionIndex].Answer2 = textIn;

        }else if (elementTag.equals("answer3")) {
            Questions[setQuestionIndex].Answer3 = textIn;

        }else if (elementTag.equals("answer4")) {
            Questions[setQuestionIndex].Answer4 = textIn;

        }else if (elementTag.equals("correctanswer")) {
            Questions[setQuestionIndex].CorrectAnswer = textIn;

        }else if (elementTag.equals("comment")) {
            Questions[setQuestionIndex].Comment = textIn;

            numQuestions++;
            setQuestionIndex++;
        }

    }

    public void createDialog(String msg) {
        AlertDialog.Builder adb;
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        adb = new AlertDialog.Builder(this);
        String s = (String) getResources().getString(R.string.app_name);
        adb.setTitle(s);
        TextView mytext = new TextView(this);
        mytext.append(msg);
        linear.addView(mytext);
        adb.setView(linear);
        adb.setPositiveButton("Ok", null);
        adb.show();
    }

    public boolean isSdPresent() {

        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
