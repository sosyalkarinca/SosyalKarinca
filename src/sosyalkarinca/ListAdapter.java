package sosyalkarinca;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kardelen.sosyalkarinca.R;
import com.turkcell.readerapp.model.Rss;

public class ListAdapter extends BaseAdapter {

	private List data;
	private Context context;

	public ListAdapter(List data, Context context) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View cellView = inflater.inflate(R.layout.row_main, null);

		Rss rss = (Rss) data.get(position);
		TextView titleView = (TextView) cellView.findViewById(R.id.rss_title);
		titleView.setText(rss.getTitle());
		
		WebView webview = (WebView) cellView.findViewById(R.id.rss_content);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setFocusable(false);
		webview.loadDataWithBaseURL("", rss.getContent(), "text/html", "UTF-8",
				"");

		Log.d("List", rss.getImageUrl());

		return cellView;
	}

}
