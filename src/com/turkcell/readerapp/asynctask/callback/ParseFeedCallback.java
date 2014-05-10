package com.turkcell.readerapp.asynctask.callback;

import java.util.List;

import com.turkcell.readerapp.model.Rss;

public interface ParseFeedCallback {
   
   public void finishedLoadingFeeds(List<Rss> feeds);
   
}
