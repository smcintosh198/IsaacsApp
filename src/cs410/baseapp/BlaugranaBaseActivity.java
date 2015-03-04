package cs410.baseapp;

import java.io.IOException;
import java.util.ArrayList;

import cs410.baseapp.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class BlaugranaBaseActivity extends Activity {
	// ----------------------------------------------------------------
	// this is the Base Activity for the entire app
	// these variables are used throughout the entire app
	// the activity is never launched by itself it is always extended
	// Originally written by Dr. Roger Webster and Ryan Garchinsky Summer 2012.
	// ----------------------------------------------------------------
	public static SharedPreferences.Editor prefEditor;
	public static SharedPreferences settings;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.locationmain);
        String s = (String) getResources()
                .getString(R.string.app_name_extended);
        this.setTitle(s);
    }

    public void createAboutThisAppDialog() {
        AlertDialog.Builder adb;
        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        // linear.setBackgroundColor(getResources().getColor(R.color.Blue));
        adb = new AlertDialog.Builder(this);
        String s = getResources().getString(R.string.app_name);
        adb.setTitle(s);
        TextView mytext = new TextView(this);
        mytext.append("This app written by: ");
        mytext.append("Shayne McIntosh, with framework written by -> ");
        mytext.append("Dr. Roger Webster\n");
        mytext.append("Have fun and enjoy!!!\n");
        linear.addView(mytext);
        adb.setView(linear);
        adb.setPositiveButton("Ok", null);
        adb.show();
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

	public void showRateDialog(final Context mContext) {

		String title = getResources().getString(R.string.app_name_extended);
		final Dialog dialog = new Dialog(mContext);

		dialog.setTitle("Rate " + title);
		LinearLayout ll = new LinearLayout(mContext);
		ll.setOrientation(LinearLayout.VERTICAL);
		TextView tv = new TextView(mContext);
		tv.setText("If you enjoy using " + title
				+ ", please take a moment to rate it. Thanks for your support!");
		tv.setWidth(240);
		tv.setPadding(4, 0, 4, 10);
		ll.addView(tv);
		Button b1 = new Button(mContext);
		b1.setText("Rate " + title);
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				openMarketAppPage(mContext);
				dialog.dismiss();
			}
		});
		ll.addView(b1);
		Button b2 = new Button(mContext);
		b2.setText("Remind me later");
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		ll.addView(b2);
		Button b3 = new Button(mContext);
		b3.setText("No, thanks");
		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				dialog.dismiss();
			}
		});
		ll.addView(b3);
		dialog.setContentView(ll);
		dialog.show();
	}

	public void openMarketAppPage(Context context) {
		String packageName = context.getApplicationInfo().packageName;
		try {
			// Open the market application on your app page
			Uri marketAppUri = Uri.parse("market://details?id=" + packageName);
			Intent intent = new Intent(Intent.ACTION_VIEW, marketAppUri);
			startActivity(intent);
		} catch (Exception e) {
			try {
				// If no market application on the device, open the market
				// website instead
				Uri marketWebUri = Uri
						.parse("https://play.google.com/store/apps/details?id="
								+ packageName);
				Intent intent = new Intent(Intent.ACTION_VIEW, marketWebUri);
				startActivity(intent);
			} catch (Exception e2) {

				DialogAbouttheApp();

			}
		}
	}

	public void DialogAbouttheApp() {
		AlertDialog.Builder adb;
		LinearLayout linear = new LinearLayout(this);
		linear.setOrientation(LinearLayout.VERTICAL);
		// linear.setBackgroundColor(getResources().getColor(R.color.LiteBlue));
		adb = new AlertDialog.Builder(this);
		String s = getResources().getString(R.string.app_name_extended);
		adb.setTitle(s);
		TextView mytext = new TextView(this);
		// mytext.setTextColor(R.color.Black);
		// mytext.setTextSize(getResources().getDimension(R.dimen.version_size));
		String msg = getResources().getString(R.string.AboutThisAppMsg);
		mytext.append(msg);
		linear.addView(mytext);
		adb.setView(linear);
		adb.setPositiveButton("Ok", null);
		adb.show();
	}

	
	

}
