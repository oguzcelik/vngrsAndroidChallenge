package xyz.oguzcelik.vngrschallenge;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private static final String TWITTER_KEY = "LB6OGTYgveChQKFkXWcPJNDvP";
    private static final String TWITTER_SECRET = "BB8azPeFcEkheWI0bbwaNqjEoz72uUWBXfWBVzj2WKqtoSVPOK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#android")
                .build();

        final CustomTweetTimelineListAdapter adapter = new CustomTweetTimelineListAdapter(this, searchTimeline);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        Toast.makeText(getApplicationContext(), "Could not load tweets. Swipe to refresh.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        final ListView tweetsListView = (ListView) findViewById(R.id.listview_tweets);
        tweetsListView.setAdapter(adapter);
    }
}
