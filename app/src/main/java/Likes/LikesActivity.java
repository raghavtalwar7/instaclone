package Likes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.raghav.instaclone.R;

import utils.BottomNavigationViewHelper;

import static utils.StatusUtil.updateuserstatus;

public class LikesActivity extends AppCompatActivity {
    private static final String TAG = "LikesActivity";
    private static final int Activity_NUM = 3;

    private Context mcontext = LikesActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started.");

        setupbottomnavView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateuserstatus("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateuserstatus("offline");
    }

    //bottom nav view setup
    private void setupbottomnavView(){
        Log.d(TAG, "setupbottomnavView: setting up bottomnavView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mcontext,LikesActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(Activity_NUM);
        menuItem.setChecked(true);
    }
}
