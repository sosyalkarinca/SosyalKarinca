package sosyalkarinca;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.kardelen.sosyalkarinca.R;
import com.turkcell.readerapp.helper.Downloader;
import com.turkcell.readerapp.helper.RssParser;

public class EventActivity extends Activity implements OnClickListener {

	public static String concatStringsWSep(Set<String> strings, String separator) {
		StringBuilder sb = new StringBuilder();
		String sep = "";
		for (String s : strings) {
			sb.append(sep).append(s);
			sep = separator;
		}
		return sb.toString();
	}

	private class FeedTask extends AsyncTask<Void, Void, List> {

		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EventActivity.this);
			pDialog.setTitle("Sosyal Karinca");
			pDialog.setMessage("Yukleniyor...");
			if (pDialog != null) {
				pDialog.show();
			}
		}

		@Override
		protected List doInBackground(Void... params) {

			SharedPreferences sp = getSharedPreferences("MyApp",
					Context.MODE_PRIVATE);
			Set<String> selectedList = sp.getStringSet("kategori",
					new TreeSet<String>());
			String ozelFeedUrl;
			if (selectedList.size() == 0) {
				ozelFeedUrl = "http://sosyalkarinca.wordpress.com/feed/";
			} else {
				String concat = concatStringsWSep(selectedList, ",");
				ozelFeedUrl = "http://sosyalkarinca.wordpress.com/category/"
						+ concat + "/feed/";
			}

			String rssContent = Downloader.getContent(ozelFeedUrl);
			List feeds = RssParser.parseFeed(rssContent);

			return feeds;
		}

		@Override
		protected void onPostExecute(List result) {
			super.onPostExecute(result);

			ListView ozelList = (ListView) findViewById(R.id.eventListView);
			ListAdapter adapter = new ListAdapter(result,
					getApplicationContext());
			ozelList.setAdapter(adapter);
			if (pDialog != null) {
				pDialog.dismiss();
			}

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_activity);

		// tepedeki butonlara click event'leri ekliyorum
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);

		new FeedTask().execute();

	}

	@Override
	public void onClick(View view) {
		// butonlarin acacaklari ekranlar
		if (view.getId() == R.id.button1) {
			Intent intent = new Intent(EventActivity.this, MainActivity.class);
			startActivity(intent);
		} else if (view.getId() == R.id.button2) {
			Intent intent = new Intent(EventActivity.this, EventActivity.class);
			startActivity(intent);
		} else if (view.getId() == R.id.button3) {
			// burada profilim gelmeli
			Intent intent = new Intent(EventActivity.this,
					CharityActivity.class);
			startActivity(intent);
		}
		// mevcut ekrani yeni ekran gelince kapatiyorum
		finish();
	}
}