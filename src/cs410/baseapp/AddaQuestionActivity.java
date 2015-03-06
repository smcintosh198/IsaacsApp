package cs410.baseapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


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

    TextView adbQuestion;
    RadioGroup rg;
    RadioButton adbRadioButton1;
    RadioButton adbRadioButton2;
    RadioButton adbRadioButton3;
    RadioButton adbRadioButton4;


    Context mContext = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        initGui();

    }

    private void initGui() {
        question = (EditText) findViewById(R.id.addQuestionQuestion);
        answer1Text = (EditText) findViewById(R.id.addQuestionAnswer1);
        answer2Text = (EditText) findViewById(R.id.addQuestionAnswer2);
        answer3Text = (EditText) findViewById(R.id.addQuestionAnswer3);
        answer4Text = (EditText) findViewById(R.id.addQuestionAnswer4);
        nameText = (EditText) findViewById(R.id.addQuestionName);
        commentText = (EditText) findViewById(R.id.addQuestionName);

        spin = (Spinner) findViewById(R.id.addQuestionAnswerSpinner);
        answers[0] = answer1Text.getText().toString();
        answers[1] = answer2Text.getText().toString();
        answers[2] = answer3Text.getText().toString();
        answers[3] = answer4Text.getText().toString();
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
                if (question.getText().toString().trim().length() == 0){
                    CharSequence text = "Please fill in your Question.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else if (answers[0].isEmpty()){
                    CharSequence text = "Please fill in your Choice 1.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else if (answers[1].isEmpty()){
                    CharSequence text = "Please fill in your Choice 2.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else if (answers[2].isEmpty()){
                    CharSequence text = "Please fill in your Choice 3.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else if (answers[3].isEmpty()){
                    CharSequence text = "Please fill in your Choice 4.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else if (nameText.getText().toString().trim().length() == 0){
                    CharSequence text = "Please provide your name.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                } else{
                    submitQuestionDialog();
                }

            }
        });
    }

    private void initSpinner() {
        // Throw object around or create String array?
        // Application of the Array to the Spinner


        final ArrayAdapter<String> answersArray_adapter;
        answersArray_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, answers);
        answersArray_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        spin.setAdapter(answersArray_adapter);

        answersArray_adapter.notifyDataSetChanged();
        answer1Text.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                answers[0] = s.toString();
                answersArray_adapter.notifyDataSetChanged();
            }
        });

        answer2Text.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                answers[1] = s.toString();
                answersArray_adapter.notifyDataSetChanged();
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

    private void submitQuestionDialog() {

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        String s = "\nPlease make sure the correct answer is \n selected below, then press Send Q Button";
        //String s = getResources().getString(R.string.app_name);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater mInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.question_submit, linear);

        adbQuestion = (TextView) v.findViewById(R.id.adbQuestion);
        adbQuestion.setText(question.getText().toString());

        rg = (RadioGroup) v.findViewById(R.id.adbRadioGroup);
        adbRadioButton1 = (RadioButton) v.findViewById(R.id.adbRadioButton1);
        adbRadioButton1.setText(answers[0]);
        final int RB1 = adbRadioButton1.getId();
        adbRadioButton2 = (RadioButton) v.findViewById(R.id.adbRadioButton2);
        adbRadioButton2.setText(answers[1]);
        final int RB2 = adbRadioButton2.getId();
        adbRadioButton3 = (RadioButton) v.findViewById(R.id.adbRadioButton3);
        adbRadioButton3.setText(answers[2]);
        final int RB3 = adbRadioButton3.getId();
        adbRadioButton4 = (RadioButton) v.findViewById(R.id.adbRadioButton4);
        adbRadioButton4.setText(answers[3]);
        final int RB4 = adbRadioButton4.getId();

        adb.setView(v);
        adb.setTitle(s);
        adb.setCancelable(true);
        AlertDialog.Builder submit = adb.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int selectedIndex = rg.getCheckedRadioButtonId();
                if (selectedIndex == RB1) {
                    selectedIndex = 0;
                    correctAnswer = answers[selectedIndex];
                    sendEmail();
                } else if (selectedIndex == RB2) {
                    selectedIndex = 1;
                    correctAnswer = answers[selectedIndex];
                    sendEmail();
                } else if (selectedIndex == RB3) {
                    selectedIndex = 2;
                    correctAnswer = answers[selectedIndex];
                    sendEmail();
                } else if (selectedIndex == RB4) {
                    selectedIndex = 3;
                    correctAnswer = answers[selectedIndex];
                    sendEmail();
                }
            }
        });
        adb.show();

    }

    /**
     * Method to send email
     */
    protected void sendEmail() {
        // Setup the recipient in a String array
        String[] mailto = {"s.mcintosh198@gmail.com"};
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
                + "\n" + "<comment>" + "Sent in by: name. " + nameText.getText().toString() + "</comment>" + "</item> \n"
                + "\n" + s;
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("message/rfc822");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Please pick your preferred email application"));
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
}
