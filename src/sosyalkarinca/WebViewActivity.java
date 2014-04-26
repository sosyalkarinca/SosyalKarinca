package sosyalkarinca;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.kardelen.sosyalkarinca.R;

public class WebViewActivity extends Activity {

	// bu sinif web sitesi yonlendirmelerini acacaktir

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_activity);

		// web adresini aldim
		String url = getIntent().getStringExtra("url");

		WebView webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(url);

	}

}
