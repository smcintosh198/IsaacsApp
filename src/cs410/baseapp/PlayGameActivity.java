package cs410.baseapp;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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
    TextView question;

    ImageButton backButton;
    ImageButton rateButton;
    ImageButton checkAnswerButton;
    ImageButton randomizeButton;

    RadioGroup answersRadioGroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    RadioButton answer4;

    boolean haveSDcard = false;

    private int totalNumCorrect;
    private int totalNumIncorrect;
    private int questionIndex;
    private int lastQuestionIndex;
    private int totalNumQuestions;
    private Question[] Questions = new Question[MAX_QUESTIONS];

    final Context mContext = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        getAppPreferences();
        initGui();
    }

    private void initGui() {
        question = (TextView) findViewById(R.id.gameQuestion);
        initButtons();
        initRadioButtons();
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
                finish();
            }
        });

        randomizeButton = (ImageButton) findViewById(R.id.gameRandomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomIndex = new Random();
                int randomQuestionIndex = randomIndex.nextInt(MAX_QUESTIONS - 1) + 1;
                question = Questions[randomQuestionIndex].Question;
            }
        });

    }

    private void initRadioButtons(){
        answersRadioGroup = (RadioGroup) findViewById(R.id.gameRadioGroup);
        answer1 = (RadioButton) findViewById(R.id.gameRadioAnswer1);
        answer2 = (RadioButton) findViewById(R.id.gameRadioAnswer2);
        answer3 = (RadioButton) findViewById(R.id.gameRadioAnswer3);
        answer4 = (RadioButton) findViewById(R.id.gameRadioAnswer4);

    }

    public void getAppPreferences() {
        settings = getSharedPreferences(GAME_PREFERENCES, MODE_PRIVATE);
        prefEditor = settings.edit();
        prefEditor.putString("UserName", "Shayne McIntosh");
        if (settings.contains("NumCorrect")) {
            totalNumCorrect = settings.getInt("NumCorrect", totalNumCorrect);
        } else {
            prefEditor.putInt("NumCorrect", totalNumCorrect);
            prefEditor.commit();
        }
        if (settings.contains("NumInCorrect")) {
            totalNumIncorrect = settings.getInt("NumInCorrect",
                    totalNumIncorrect);
        } else {
            prefEditor.putInt("NumInCorrect", totalNumIncorrect);
            prefEditor.commit();
        }
        if (settings.contains("Qno")) {
            lastQuestionIndex = settings.getInt("Qno", lastQuestionIndex);
        } else {
            prefEditor.putInt("Qno", 0);
            prefEditor.commit();
        }
        // prefEditor.putInt("Qno", 0);
        // myLastQsindex=0;
        // prefEditor.commit();
    }

    public void getQuestionsfromXML() throws XmlPullParserException,
            IOException {
        String tagname = " ";
        XmlResourceParser xpp = getResources().getXml(R.xml.trivia);

        if (xpp == null) {

        } else {
            xpp.next();
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagname = xpp.getName();
                } else if (eventType == XmlPullParser.TEXT) {
                    setNextQuestion(tagname, xpp.getText());

                }
                eventType = xpp.next();

            }
        }
    }

    public void setNextQuestion(String tagname, String textin) {

        if (tagname.equals("question")) {
            Questions[questionIndex] = new Question();
            Questions[questionIndex].Question = textin;

        }
        if (tagname.equals("answer1")) {
            Questions[questionIndex].Answer1 = textin;

        }
        if (tagname.equals("answer2")) {
            Questions[questionIndex].Answer2 = textin;

        }
        if (tagname.equals("answer3")) {
            Questions[questionIndex].Answer3 = textin;

        }
        if (tagname.equals("answer4")) {
            Questions[questionIndex].Answer4 = textin;

        }
        if (tagname.equals("correctanswer")) {
            Questions[questionIndex].correctAnswer = textin;

        }

        if (tagname.equals("comment")) {
            Questions[questionIndex].comment = textin;
            //createRWWDialog(myQs[myQsindex].Question);
            msg = "question-> " + Questions[questionIndex].Question + "\n" + "Answer: " + Questions[questionIndex].correctAnswer;
            Toast t;
            // t = Toast.makeText(networkAccessXMLFIleActivity.this,
            //         msg, Toast.LENGTH_SHORT);
            // t.show();
            Log.d("RWW", "totalQs is " + totalNumQuestions + " max " + MAX_QUESTIONS);

        }

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
