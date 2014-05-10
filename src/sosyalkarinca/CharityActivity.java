package sosyalkarinca;

import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kardelen.sosyalkarinca.R;

public class CharityActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	// wordpress'teki kategorileri buraya girelim
	String[] charityArray = new String[] { "TOG", "TEGV", "LÖSEV",
			"Türk Kalp Vakfi", "ÇYDD", "Sağlıklı Yaşam", "TEV", "ÇEKÜL" };
	String[] kategoriArray = new String[] { "TOG", "TEGV", "LOSEV",
			"turk-kalp-vakfi", "cagdas-yasami-destekleme-dernegi",
			"saglikli-yasam", "TEV", "CEKUL" };

	private Set<String> selectedList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.charity_activity);

		// https://gelecegiyazanlar.turkcell.com.tr/konu/android/egitim/android-201/listview-kullanimi
		ListView eventList = (ListView) findViewById(R.id.charityList);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				charityArray);
		eventList.setAdapter(adapter);
		// satirlara bastigimda aksiyon alsin
		eventList.setOnItemClickListener(this);

		// tepedeki butonlara click event'leri ekliyorum
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(this);

		SharedPreferences sp = getSharedPreferences("MyApp",
				Context.MODE_PRIVATE);

		selectedList = sp.getStringSet("kategori", new TreeSet<String>());
	}

	@Override
	public void onClick(View view) {
		// butonlarin acacaklari ekranlar
		if (view.getId() == R.id.button1) {
			Intent intent = new Intent(CharityActivity.this, MainActivity.class);
			startActivity(intent);
		} else if (view.getId() == R.id.button2) {
			Intent intent = new Intent(CharityActivity.this,
					EventActivity.class);
			startActivity(intent);
		} else if (view.getId() == R.id.button3) {
			// burada profilim gelmeli
			Intent intent = new Intent(CharityActivity.this,
					CharityActivity.class);
			startActivity(intent);
		}
		// mevcut ekrani yeni ekran gelince kapatiyorum
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {

		String kat = kategoriArray[position];
		if (selectedList.contains(kat)) {
			selectedList.remove(kat);
			Toast.makeText(getApplicationContext(),
					charityArray[position] + " listeden çıkarıldı!",
					Toast.LENGTH_LONG).show();
		} else {
			selectedList.add(kat);
			Toast.makeText(getApplicationContext(),
					charityArray[position] + " listeye eklendi!",
					Toast.LENGTH_LONG).show();
		}

		SharedPreferences sp = getSharedPreferences("MyApp",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor spEditor = sp.edit();
		spEditor.putStringSet("kategori", selectedList);
		spEditor.commit();

	}
}