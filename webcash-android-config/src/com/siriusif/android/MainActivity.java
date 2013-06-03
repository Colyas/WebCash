package com.siriusif.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	final String LOG_TAG = "webCashAndroid";
	private EditText mEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mEdit = (EditText) findViewById(R.id.url);
		mEdit.setText(readProps());
	}

	public void onclick(View v) {
		switch (v.getId()) {
		case R.id.btn_save:
			writeProps();
			break;
		}
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
