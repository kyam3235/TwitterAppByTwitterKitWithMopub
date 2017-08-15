package jp.lab.kyam.twitterappbytwitterkitwithmopub;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mopub.nativeads.MoPubAdAdapter;
import com.twitter.sdk.android.mopub.TwitterMoPubAdAdapter;
import com.twitter.sdk.android.mopub.TwitterStaticNativeAdRenderer;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

public class TimeLineActivity extends ListActivity {
    private MoPubAdAdapter moPubAdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#猫")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(searchTimeline)
                .build();

        moPubAdAdapter = new TwitterMoPubAdAdapter(this, adapter);
        final TwitterStaticNativeAdRenderer adRenderer = new TwitterStaticNativeAdRenderer();
        moPubAdAdapter.registerAdRenderer(adRenderer);
        moPubAdAdapter.loadAds(getResources().getString(R.string.mopub_ad_unit_id));

        setListAdapter(moPubAdAdapter);
    }

    @Override
    public void onDestroy(){
        moPubAdAdapter.destroy();
        super.onDestroy();
    }
}
