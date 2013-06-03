package com.siriusif.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static final int IDM_EXIT = 103;
	public static final int IDM_SAVE = 102;

	final String LOG_TAG = "webCashAndroid";
	private EditText mEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mEdit = (EditText) findViewById(R.id.url);
		mEdit.setText(readProps());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_EXIT, Menu.NONE, R.string.exit)
				.setIcon(R.drawable.exit).setAlphabeticShortcut('x');
		menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, R.string.save)
		.setIcon(R.drawable.save).setAlphabeticShortcut('s');

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_SAVE:
			writeProps();
			break;
		case IDM_EXIT:
			finish();
			break;
		default:
			return false;
		}
		return true;
	}

	void writeProps() {
		Properties props = new Properties();
		props.setProperty("host", mEdit.getText().toString());
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/MyFiles/settings.properties";
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			props.save(fos, "hello");
		} catch (Exception e) {
			Log.e(LOG_TAG, "Can't write file", e);
		}
	}

	String readProps() {
		Properties props = new Properties();
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/MyFiles/settings.properties";
		Log.d(LOG_TAG, "Property file read");
		try {
			props.load(new FileInputStream(path));
		} catch (Exception e) {
			Log.e(LOG_TAG, "Can't read file", e);
		}
		return props.getProperty("host");
	}

}
