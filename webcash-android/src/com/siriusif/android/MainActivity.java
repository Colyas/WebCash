package com.siriusif.android;

import java.io.FileInputStream;
import java.util.Properties;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	WebView mWebView;
	final private String LOG_TAG = "logWebCashAndroid";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl(readProps());
		mWebView.setWebViewClient(new WebCashWebViewClient());
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

	private class WebCashWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
