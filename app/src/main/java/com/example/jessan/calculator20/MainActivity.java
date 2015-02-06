package com.example.jessan.calculator20;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    static final private String CURRENT_ANSWER = null;
    private TextView mAnswerRepresentation= null;
    String mText = " ";
    private double mNumber1=0;
    private double mNumber2=0;
    private char mOperator='+';
    CharSequence error = "Enter valid expression!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnswerRepresentation=(TextView) findViewById(R.id.answer);
        mText = getPreferences(MODE_PRIVATE).getString(CURRENT_ANSWER,mText);
        update();
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(CURRENT_ANSWER,mText);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void update(){
        mAnswerRepresentation.setText(mText);
    }
    public void onSevenClick(View view){
        mText+="7";
        update();
    }
    public void onEightClick(View view){
        mText+="8"; update();
    }
    public void onNineClick(View view){
        mText+="9"; update();
    }
    public void onFourClick(View view){
        mText+="4"; update();
    }
    public void onFiveClick(View view){
        mText+="5"; update();
    }
    public void onSixClick(View view){
        mText+="6"; update();
    }
    public void onOneClick(View view){
        mText+="1"; update();
    }
    public void onTwoClick(View view){
        mText+="2"; update();
    }
    public void onThreeClick(View view){
        mText+="3"; update();
    }
    public void onDotClick(View view){
        mText+="."; update();
    }
    public void onZeroClick(View view){
        mText+="0"; update();
    }
    public void onPlusClick(View view){
        mText+="+";
        mOperator='+';
        update();
    }
    public void onMinusClick(View view){
        mText+="-";
        mOperator='-';
        update();
    }
    public void onMultiplyClick(View view){
        mText+="*";
        mOperator='*';
        update();
    }
    public void onDivideClick(View view){
        mText+="/";
        mOperator='/';
        update();
    }
    public void onClearClick(View view){
        mText=" ";
        mOperator='+';
        update();
    }
    public void onDeleteClick(View view){
        int length = mText.length();
        if(length==0) return;
        mText = mText.substring(0,length-1);
        update();
    }
    private void simpleToast( CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        //CONSTRUCTOR
        Toast toast = Toast.makeText(context, text, duration);
        toast.show(); // do this always
    }
    public void onEqualsClick(View view){
        int index = -1;
        index = mText.indexOf(mOperator);
        if(index + 1 == mText.length() || index ==-1 || index ==0) {
             simpleToast(error);
             mText = ""; update();
            return;
        }
        String n=mText.substring(0,index);
        mNumber1 = Double.parseDouble(n);
        n = mText.substring(index+1);
        mNumber2 = Double.parseDouble(n);

        double result = solve(mNumber1,mNumber2,mOperator);
        mText = String.format("%.2f", result);
        update();
        //mText = "";
    }
    public double solve(double n1,double n2, char op){
        switch(op){
            case '+': return n1 + n2;
            case '-': return n1 - n2;
            case '*': return n1 * n2;
            case '/': return n1 / n2;
            default: return -1;
        }
    }


}
