package tties.cn.energy.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.application.MyApplication;
import tties.cn.energy.model.result.Opsbean;


/**
 * Created by li on 2018/3/22
 * description：
 * author：guojlli
 */

public class MyOpsrightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    List<Opsbean.ResultBean.QuestionListBean> listbean;
    List<View> listhead = new ArrayList<>();
    onClickListener listener;
    Opsbean opsbean;
    public void setonClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public MyOpsrightAdapter(List<Opsbean.ResultBean.QuestionListBean> listbean) {
        this.listbean = listbean;
    }
    public void setOpsbean(Opsbean opsbean){
        this.opsbean=opsbean;
    }
    public void setHeadView(View view) {
        listhead.add(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View inflate = View.inflate(MyApplication.getInstance(), R.layout.activity_ops_item_right_no, null);
            return new MyNoQuestionViewHoder(inflate);
        } else {
            View inflate = View.inflate(MyApplication.getInstance(), R.layout.activity_ops_item_right, null);
            return new MyViewHoder(inflate);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        if (holder instanceof MyNoQuestionViewHoder) {
//            ((MyNoQuestionViewHoder) holder).opsItemRightNoTv.setText("无问题");
//        }
        if (holder instanceof MyViewHoder) {
            ((MyViewHoder) holder).opsItemTitle.setText(listbean.get(position).getTitle());
            ((MyViewHoder) holder).opsItemTime.setText(listbean.get(position).getCreateTime());
            ((MyViewHoder) holder).opsItemAddress.setText(listbean.get(position).getDescription());
            ((MyViewHoder) holder).opsItemLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickItemListener(position);
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (opsbean.getResult().getCount() == 0 && position < listhead.size()) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }

    }

    @Override
    public int getItemCount() {
        return listbean != null ? listbean.size() : 0;
    }

    public class MyViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.ops_item_title)
        TextView opsItemTitle;
        @BindView(R.id.ops_item_time)
        TextView opsItemTime;
        @BindView(R.id.ops_item_address)
        TextView opsItemAddress;
        @BindView(R.id.ops_item_ll)
        LinearLayout opsItemLl;

        public MyViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class MyNoQuestionViewHoder extends RecyclerView.ViewHolder {
        @BindView(R.id.ops_item_right_no_tv)
        TextView opsItemRightNoTv;
        public MyNoQuestionViewHoder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface onClickListener {
        void onClickItemListener(int postion);
    }
}
