package cs410.baseapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;


public class AddaQuestionActivity extends Activity {
    String correctAnswer;
    EditText Question;
    EditText Answer1Text;
    EditText Answer2Text;
    EditText Answer3Text;
    EditText Answer4Text;
    EditText CommentText;
    EditText Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        initGui();
        Button backButton = (Button) findViewById(R.id.AddaQuestion);


    }

    private void initGui(){
        Question = (EditText) findViewById(R.id.add_a_question_question);
        Answer1Text = (EditText) findViewById(R.id.add_a_question_choice1);
        Answer2Text = (EditText) findViewById(R.id.add_a_question_choice2);
        Answer3Text = (EditText) findViewById(R.id.add_a_question_choice3);
        Answer4Text = (EditText) findViewById(R.id.add_a_question_choice4);
        Name = (EditText) findViewById(R.id.add_a_question_name);

    }

    private void spinner(){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adda_question, menu);
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

    /**
     * Method to send email
     */
    protected void sendEmail() {
        // Setup the recipient in a String array
        String[] mailto = {"sdmcinto1@millersville.edu"};
        // Create a new Intent to send messages
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        // Add attributes to the intent
        sendIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        // sendIntent.putExtra(Intent.EXTRA_CC, ccto);
        String s = (String) getResources().getString(R.string.app_name);

        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Barcelona Trivia Game App Create Your Own Question");
        String msg = "<item> \n" + "<question>"
                + Question.getText().toString() + "</question> " + "\n"
                + "<answer1>" + Answer1Text.getText().toString() + "</answer1>"
                + "\n" + "<answer2>" + Answer2Text.getText().toString()
                + "</answer2>" + "\n" + "<answer3>"
                + Answer3Text.getText().toString() + "</answer3>" + "\n"
                + "<answer4>" + Answer4Text.getText().toString() + "</answer4>"
                + "\n" + "<correctanswer>" + correctAnswer + "</correctanswer>"
                + "\n" + "<comment>" + "Sent in by: name. " + CommentText.getText().toString() + "</comment>" + "</item> \n"
                + "\n" + s;
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("message/rfc822");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Please pick your preferred email application"));
    }
    public void createDialogQuestion() {

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        String s = "\nPlease make sure the correct answer is selected below, then press Send Q Button";
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("My Patriots Trivia Question");
        adb.setView(linear);
        LayoutInflater mInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View VolumeView = mInflater.inflate(R.layout.question, linear);
    }
}
