package cs410.baseapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MenuActivity extends isaacsBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void createAboutThisApp() {
        AlertDialog.Builder adb;
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        // linear.setBackgroundColor(getResources().getColor(R.color.Blue));
        adb = new AlertDialog.Builder(this);
        String s = (String) getResources().getString(R.string.app_name);
        adb.setTitle(s);
        TextView mytext = new TextView(this);
        mytext.append("This app written by: ");
        mytext.append("Dr. Roger Webster\n");
        mytext.append("Have fun and enjoy!!!\n");
        linear.addView(mytext);
        adb.setView(linear);
        adb.setPositiveButton("Ok", null);
        adb.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
