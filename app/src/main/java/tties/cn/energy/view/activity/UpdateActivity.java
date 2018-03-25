package tties.cn.energy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tties.cn.energy.R;

public class UpdateActivity extends AppCompatActivity {
    public static void show(Activity activity) {
        Intent intent = new Intent(activity, UpdateActivity.class);
        activity.startActivityForResult(intent, 0x01);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }
}
