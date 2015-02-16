package cs410.baseapp;

import cs410.baseapp.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class isaacsActivity extends isaacsBaseActivity {
	// --------------------------------------------------------------------------
	// this is the Main Activity for the app
	// this activity extends isaacsBaseActivity which loads the xml store info
	// this puts up the main menu of actions for the user
	// Originally written by Dr. Roger Webster
	// --------------------------------------------------------------------------

	Context mContext = this;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
			ViewParent parent = titleView.getParent();
			if (parent != null && (parent instanceof View)) {
				View parentView = (View) parent;
				parentView.setBackgroundColor(Color.rgb(0x00, 0x21, 0x42));
			}
		}

		String title = getResources().getString(R.string.app_name_extended)
				+ " " + getResources().getString(R.string.version);

		setTitle(title);

	
	}

	public void onUserLeaveHint() {
		// System.exit(0);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.gameoptions, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_about_this_app:
			DialogAbouttheApp();
			return true;
		case R.id.menu_item_rate_this_app:
			showRateDialog(mContext);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}