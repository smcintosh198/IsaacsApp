package cs410.baseapp;

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


public class AddaQuestionActivity extends BlaugranaActivity {
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

    Context mContext = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        initGui();

    }

    private void initGui(){
        question = (EditText) findViewById(R.id.addQuestionQuestion);
        answer1Text = (EditText) findViewById(R.id.addQuestionAnswer1);
        answer2Text = (EditText) findViewById(R.id.addQuestionAnswer2);
        answer3Text = (EditText) findViewById(R.id.addQuestionAnswer3);
        answer4Text = (EditText) findViewById(R.id.addQuestionAnswer4);
        nameText = (EditText) findViewById(R.id.addQuestionName);
        commentText = (EditText) findViewById(R.id.addQuestionName);

        spin = (Spinner)  findViewById(R.id.addQuestionAnswerSpinner);
        answers[0] =  answer1Text.getText().toString();
        answers[1] =  answer2Text.getText().toString();
        answers[2] =  answer3Text.getText().toString();
        answers[3] =  answer4Text.getText().toString();
        initSpinner();

        backButton = (Button) findViewById(R.id.addQuestionBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });

        submitButton = (Button) findViewById(R.id.addQuestionSubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // submitQuestionDialog();
                sendEmail();
            }
        });
    }
    /*
    private void submitQuestionDialog(){
        AlertDialog.Builder adb;
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        // linear.setBackgroundColor(getResources().getColor(R.color.Blue));
        adb = new AlertDialog.Builder(this);
        String s = getResources().getString(R.string.app_name);
        adb.setTitle(s);
        TextView mytext = new TextView(this);
        mytext.append(question.getText());
        createRadioButton(linear);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i = 0; i < 5; i++) {
                    rg.removeView(rb[i]);//now the RadioButtons are in the RadioGroup
                }
                ll.removeView(submit);
                Questions();
            }
        });


        linear.addView(mytext);
        adb.setView(linear);
        adb.setPositiveButton("Ok", null);
        adb.show();

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.question_submit, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialoglayout);
        builder.show();

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

    private void createRadioButton(LinearLayout ll) {
        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<5; i++){
            rb[i]  = new RadioButton(this);
            rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
            rb[i].setText(answers[i]);
        }
        ll.addView(rg);//you add the whole RadioGroup to the layout
        ll.addView(submit);

    }
    */

    private void initSpinner(){
        // Throw object around or create String array?
        // Application of the Array to the Spinner


        final ArrayAdapter<String> answersArray_adapter;
        answersArray_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, answers);
        answersArray_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        spin.setAdapter(answersArray_adapter);

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


                answersArray_adapter.notifyDataSetChanged();
                answers[3] = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }
        });

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
