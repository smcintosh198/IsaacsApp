package cs410.baseapp;

/**
 * Created by Sid on 3/5/2015.
 */
public class Question extends Object {
    public String Question;
    public String Answer1;
    public String Answer2;
    public String Answer3;
    public String Answer4;
    public String CorrectAnswer;
    public String Comment;

    public Question(){

    }

    //getters
    public String getQuestion(){
        return Question;
    }

    public String getAnswer1(){
        return Answer1;
    }

    public String getAnswer2(){
        return Answer2;
    }

    public String getAnswer3(){
        return Answer3;
    }

    public String getAnswer4(){
        return Answer4;
    }

    public String getCorrectAnswer(){
        return CorrectAnswer;
    }

    public String getComment(){
        return Comment;
    }

    //setters
    public void setQuestion(String question){
        Question = question;
    }
    public void setAnswer1(String answer1){
        Answer1 = answer1;
    }

    public void setAnswer2(String answer2){
        Answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
