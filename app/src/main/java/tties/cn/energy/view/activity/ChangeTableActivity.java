package tties.cn.energy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.model.result.OpsLoginbean;
import tties.cn.energy.view.adapter.MyElectricityAdapter;

public class ChangeTableActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_left)
    ImageView toolbarLeft;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.identity_change_lv)
    ListView lv;
    @BindView(R.id.electrical_table_confirm)
    LinearLayout electricalTableConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_table);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbarText.setText("切换电表");
        toolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        OpsLoginbean bean = (OpsLoginbean) intent.getSerializableExtra("bean");
        MyElectricityAdapter adapter = new MyElectricityAdapter(ChangeTableActivity.this, bean);
        lv.setAdapter(adapter);
        //确认使用此电表
        electricalTableConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
