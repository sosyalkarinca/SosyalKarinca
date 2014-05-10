package sosyalkarinca;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.kardelen.sosyalkarinca.R;
import com.turkcell.readerapp.helper.Downloader;
import com.turkcell.readerapp.helper.RssParser;
import com.turkcell.readerapp.model.Rss;

public class MainActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	List<Rss> eventList;
	List<Rss> specialList;

	private class FeedTask extends AsyncTask<Void, Void, List> {
		
		private ProgressDialog pDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setTitle("Sosyal Karinca");
			pDialog.setMessage("Yukleniyor...");
			if(pDialog != null) {
				pDialog.show();
			}
		}
		

		@Override
		protected List doInBackground(Void... params) {
			String ozelFeedUrl = "http://sosyalkarinca.wordpress.com/category/OZEL/feed/";
			String rssContent = Downloader.getContent(ozelFeedUrl);
			List feeds = RssParser.parseFeed(rssContent);

			return feeds;
		}

		@Override
		protected void onPostExecute(List result) {
			super.onPostExecute(result);

			ListView ozelList = (ListView) findViewById(R.id.ozelGunlerList);
			ListAdapter adapter = new ListAdapter(result,
					getApplicationContext());
			ozelList.setAdapter(adapter);
			if(pDialog != null) {
				pDialog.dismiss();
			}

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

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
	public void onItemClick(AdapterView<?> adapterView, View listView,
			int position, long id) {

	}

	@Override
	public void onClick(View view) {
		// butonlarin acacaklari ekranlar
		if (view.getId() == R.id.button1) {
			Intent intent = new Intent(MainActivity.this, MainActivity.class);
			startActivity(intent);
		} else if (view.getId() == R.id.button2) {
			Intent intent = new Intent(MainActivity.this, EventActivity.class);
			startActivity(intent);
		} else if (view.getId() == R.id.button3) {
			// burada profilim gelmeli
			Intent intent = new Intent(MainActivity.this, CharityActivity.class);
			startActivity(intent);
		}
		// mevcut ekrani yeni ekran gelince kapatiyorum
		finish();
	}

}
