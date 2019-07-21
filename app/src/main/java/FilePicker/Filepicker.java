package FilePicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;
import com.raghav.instaclone.R;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.AudioPickActivity;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.activity.VideoPickActivity;
import com.vincent.filepicker.filter.entity.AudioFile;
import com.vincent.filepicker.filter.entity.ImageFile;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.vincent.filepicker.filter.entity.VideoFile;

import java.util.ArrayList;

import constants.Constants;

import static com.vincent.filepicker.activity.AudioPickActivity.IS_NEED_RECORDER;
import static com.vincent.filepicker.activity.ImagePickActivity.IS_NEED_CAMERA;

//import static com.vincent.filepicker.activity.BaseActivity.IS_NEED_FOLDER_LIST;

public class Filepicker extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvResult;
    private StorageReference mStorageReference;
    private Context mContext;
    private static final String TAG = "Filepicker";
    private int imageCount = 0;
    private String imgUrl;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filepicker);

        mTvResult = (TextView) findViewById(R.id.tv_result);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_pick_image:
                Intent intent1 = new Intent(this, ImagePickActivity.class);
                intent1.putExtra(IS_NEED_CAMERA, true);
                intent1.putExtra(Constant.MAX_NUMBER, 9);
//                intent1.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                break;

            case R.id.btn_pick_video:
                Intent intent3 = new Intent(this, VideoPickActivity.class);
                intent3.putExtra(IS_NEED_CAMERA, true);
                intent3.putExtra(Constant.MAX_NUMBER, 9);
//                intent3.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent3, Constant.REQUEST_CODE_PICK_VIDEO);
                break;
            case R.id.btn_pick_audio:
                Intent intent4 = new Intent(this, AudioPickActivity.class);
                intent4.putExtra(IS_NEED_RECORDER, true);
                intent4.putExtra(Constant.MAX_NUMBER, 9);
//                intent4.putExtra(IS_NEED_FOLDER_LIST, true);
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_AUDIO);
                break;
            case R.id.btn_pick_file:
                Intent intent5 = new Intent(this, NormalFilePickActivity.class);
                intent5.putExtra(Constant.MAX_NUMBER, 9);
//                intent5.putExtra(IS_NEED_FOLDER_LIST, true);
                intent5.putExtra(NormalFilePickActivity.SUFFIX,
                        new String[]{"xlsx", "xls", "doc", "dOcX", "ppt", ".pptx", "pdf"});
                startActivityForResult(intent5, Constant.REQUEST_CODE_PICK_FILE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    StringBuilder builder = new StringBuilder();
                    for (ImageFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                    mTvResult.setText(builder.toString());

                    Intent intent = new Intent();
                    intent.putExtra(Constants.ATTACHMENT_URL, builder.toString());
                    setResult(Constants.FILE_PICKER_RC, intent);
                    finish();
                }
                break;
            case Constant.REQUEST_CODE_PICK_VIDEO:
                if (resultCode == RESULT_OK) {
                    ArrayList<VideoFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_VIDEO);
                    StringBuilder builder = new StringBuilder();
                    for (VideoFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                    mTvResult.setText(builder.toString());

                    Intent intent = new Intent();
                    intent.putExtra(Constants.ATTACHMENT_URL, builder.toString());
                    setResult(Constants.FILE_PICKER_RC, intent);
                    finish();
                }
                break;
            case Constant.REQUEST_CODE_PICK_AUDIO:
                if (resultCode == RESULT_OK) {
                    ArrayList<AudioFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_AUDIO);
                    StringBuilder builder = new StringBuilder();
                    for (AudioFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                    mTvResult.setText(builder.toString());

                    Intent intent = new Intent();
                    intent.putExtra(Constants.ATTACHMENT_URL, builder.toString());
                    setResult(Constants.FILE_PICKER_RC, intent);
                    finish();
                }
                break;
            case Constant.REQUEST_CODE_PICK_FILE:
                if (resultCode == RESULT_OK) {
                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    StringBuilder builder = new StringBuilder();
                    for (NormalFile file : list) {
                        String path = file.getPath();
                        builder.append(path + "\n");
                    }
                    mTvResult.setText(builder.toString());

                    Intent intent = new Intent();
                    intent.putExtra(Constants.ATTACHMENT_URL, builder.toString());
                    setResult(Constants.FILE_PICKER_RC, intent);
                    finish();
                }
                break;
        }
    }
}
