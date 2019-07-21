package Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.raghav.instaclone.R;

import static utils.StatusUtil.updateuserstatus;

public class AttachActivity extends AppCompatActivity {

    private final static int EXTERNAL_STORAGE_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach);
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
}

