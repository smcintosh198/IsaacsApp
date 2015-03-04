package cs410.baseapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class PlayGameActivity extends BlaugranaActivity {

    TextView question;

    Button backButton;
    Button rateButton;
    Button checkAnswerButton;
    Button randomizeButton;

    RadioGroup answersRadioGroup;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    RadioButton answer4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        initGui();
    }

    private void initGui() {
        question = (TextView) findViewById(R.id.gameQuestion);




        initButtons();
        initRadioButtons();
    }
    private void initButtons(){
        backButton = (Button) findViewById(R.id.gameBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rateButton = (Button) findViewById(R.id.gameRateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkAnswerButton = (Button) findViewById(R.id.gameCheckAnswerButton);
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        randomizeButton = (Button) findViewById(R.id.gameRandomizeButton);
        randomizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
