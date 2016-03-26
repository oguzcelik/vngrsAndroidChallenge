package xyz.oguzcelik.vngrschallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetView;


public class DetailActivity extends AppCompatActivity {
    LinearLayout root;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String tweetAsJson;
        root = (LinearLayout) findViewById(R.id.rootFrame);
        Bundle extra = getIntent().getExtras();
        tweetAsJson = extra.getString("tweet");
        tweet = new Gson().fromJson(tweetAsJson, Tweet.class);
        root.addView(new TweetView(getApplicationContext(), tweet));
        disableViewAndSubViews(root);

    }

    private void disableViewAndSubViews(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                disableViewAndSubViews((ViewGroup) child);
            } else {
                child.setEnabled(false);
                child.setClickable(false);
                child.setLongClickable(false);
            }
        }
    }
}
