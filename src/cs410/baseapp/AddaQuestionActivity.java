package cs410.baseapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class AddaQuestionActivity extends Activity {
    public final int SPINNER_SIZE = 4;

    EditText question;
    EditText answer1Text;
    EditText answer2Text;
    EditText answer3Text;
    EditText answer4Text;
    EditText commentText;
    EditText nameText;

    String[] answers = new String[SPINNER_SIZE];
    String correctAnswer;
    Spinner spin;

    Button backButton;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        initGui();
        initSpinner();



    }

    private void initGui(){
        question = (EditText) findViewById(R.id.add_a_question_question);
        answer1Text = (EditText) findViewById(R.id.add_a_question_choice1);
        answer2Text = (EditText) findViewById(R.id.add_a_question_choice2);
        answer3Text = (EditText) findViewById(R.id.add_a_question_choice3);
        answer4Text = (EditText) findViewById(R.id.add_a_question_choice4);
        nameText = (EditText) findViewById(R.id.add_a_question_name);
        commentText = (EditText) findViewById(R.id.add_a_question_name);

        spin = (Spinner)  findViewById(R.id.add_a_question_correctAnsSpinner);
        answers[0] =  answer1Text.toString();
        answers[1] =  answer2Text.toString();
        answers[2] =  answer3Text.toString();
        answers[3] =  answer4Text.toString();

        backButton = (Button) findViewById(R.id.backButton);

        submitButton = (Button) findViewById(R.id.submitButton);
    }

    private void initSpinner(){
        // Throw object around or create String array?
        // Application of the Array to the Spinner

        final ArrayAdapter<String> answersArray_adapter;
        answersArray_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, answers);
        answersArray_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        spin.setAdapter(answersArray_adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = adapterView.getSelectedItemPosition();

                correctAnswer = answers[index];

                //System.out.println(CorrectAnswer);

                //Toast.makeText(getBaseContext(), "Your Answer is - "+CorrectAnswer,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        answersArray_adapter.notifyDataSetChanged();

        answer1Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                answers[0] = s.toString();
                answersArray_adapter.notifyDataSetChanged();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }


        });

        answer2Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                answers[1] = s.toString();
                answersArray_adapter.notifyDataSetChanged();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }

        });

        answer3Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                answers[2] = s.toString();
                answersArray_adapter.notifyDataSetChanged();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });

        answer4Text.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                answers[3] = s.toString();
                answersArray_adapter.notifyDataSetChanged();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
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

        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Barcelona Trivia Game App Create Your Own question");
        String msg = "<item> \n" + "<question>"
                + question.getText().toString() + "</question> " + "\n"
                + "<answer1>" + answer1Text.getText().toString() + "</answer1>"
                + "\n" + "<answer2>" + answer2Text.getText().toString()
                + "</answer2>" + "\n" + "<answer3>"
                + answer3Text.getText().toString() + "</answer3>" + "\n"
                + "<answer4>" + answer4Text.getText().toString() + "</answer4>"
                + "\n" + "<correctanswer>" + correctAnswer + "</correctanswer>"
                + "\n" + "<comment>" + "Sent in by: name. " + commentText.getText().toString() + "</comment>" + "</item> \n"
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
        adb.setTitle("My Patriots Trivia question");
        adb.setView(linear);
        LayoutInflater mInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View VolumeView = mInflater.inflate(R.layout.question, linear);
    }
}
