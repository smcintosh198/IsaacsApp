package cs410.baseapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;

public class BlaugranaActivity extends BlaugranaBaseActivity {
	// --------------------------------------------------------------------------
	// this is the Main Activity for the app
	// this activity extends MenuBaseActivity which loads the xml store info
	// this puts up the main menu of actions for the user
	// Originally written by Dr. Roger Webster
	// --------------------------------------------------------------------------
    Button playGameButton;
    Button addQuestionButton;
    Button aboutAppButton;
    Button quitButton;
	Context mContext = this;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

        String title = getResources().getString(R.string.app_name_extended)
                + " " + getResources().getString(R.string.version);

        setTitle(title);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
			ViewParent parent = titleView.getParent();
			if (parent != null && (parent instanceof View)) {
				View parentView = (View) parent;
				parentView.setBackgroundColor(Color.rgb(0x00, 0x21, 0x42));
			}
		}



        initGui();

	
	}

    private void initGui(){

        playGameButton = (Button) findViewById(R.id.playGameButton);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PlayGameActivity.class);
                startActivity(i);

            }
        });

        addQuestionButton = (Button) findViewById(R.id.addQuestionButton);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AddaQuestionActivity.class);
                startActivity(i);
            }
        });

        aboutAppButton = (Button) findViewById(R.id.aboutThisAppButton);
        aboutAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAboutThisAppDialog();
            }
        });

        quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
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
        case R.id.menu_item_reset_preferences:
            DialogDeletePreferences();
            return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}