package cade_a_nota.bigmini.com.br.cade_a_nota.core.application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;


/**
 * Created by Kanda on 08/11/2016.
 */

public class Application extends android.app.Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "gq2uCGSmIBI6Aptyd3VHh3Xv7";
    private static final String TWITTER_SECRET = "SZMzuru3KpACS38EZ7VqyFTisBri55njauz9kVtO642ZkcS4GZ";

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

    }
}
