package xyz.oguzcelik.vngrschallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.SearchTimeline;

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

        CustomTweetTimelineListAdapter adapter = new CustomTweetTimelineListAdapter(this, searchTimeline);

        final ListView tweetsListView = (ListView) findViewById(R.id.listview_tweets);
        tweetsListView.setAdapter(adapter);
    }
}
