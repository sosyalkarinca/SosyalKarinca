
package com.turkcell.readerapp.asynctask;

import java.util.List;

import android.os.AsyncTask;

import com.turkcell.readerapp.asynctask.callback.ParseFeedCallback;
import com.turkcell.readerapp.helper.Downloader;
import com.turkcell.readerapp.helper.RssParser;
import com.turkcell.readerapp.model.Rss;

public class ParseFeedAsyncTask extends AsyncTask<String, Void, List<Rss>> {

   private ParseFeedCallback   callback;

   public ParseFeedAsyncTask(ParseFeedCallback callback) {
      this.callback = callback;
   }

   @Override
   protected List<Rss> doInBackground(String... params) {
      String xmlContent = Downloader.getContent(params[0]);
      return RssParser.parseFeed(xmlContent);
   }

   @Override
   protected void onPostExecute(List<Rss> result) {
      super.onPostExecute(result);

      callback.finishedLoadingFeeds(result);
   }

}
