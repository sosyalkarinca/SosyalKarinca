package sosyalkarinca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.kardelen.sosyalkarinca.R;

public class MainActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	String[] eventArray = new String[] { "Agac dikim", "Insani yardim",
			"Kanser Kosu" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// acilista bilgi gelmesi amacli bir web sitesi yukleniyor
		WebView eventView = (WebView) findViewById(R.id.etkinlikWeb);
		eventView.loadUrl("http://sosyalkarinca.wordpress.com/");

		// https://gelecegiyazanlar.turkcell.com.tr/konu/android/egitim/android-201/listview-kullanimi
		ListView eventList = (ListView) findViewById(R.id.etkinlikList);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				eventArray);
		eventList.setAdapter(adapter);

		// tepedeki butonlara click event'leri ekliyorum
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {

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
