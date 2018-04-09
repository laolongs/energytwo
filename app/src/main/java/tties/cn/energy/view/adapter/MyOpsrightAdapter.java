package tties.cn.energy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.model.result.Opsbean;


/**
 * Created by li on 2018/3/22
 * description：
 * author：guojlli
 */

public class MyOpsrightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    LayoutInflater inflater;
    List<Opsbean.ResultBean.QuestionListBean> listbean;
    List<String> listhead = new ArrayList<>();
    onClickListener listener;
    Opsbean.ResultBean opsbean;


    public void setonClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public void setApapterData(List<Opsbean.ResultBean.QuestionListBean> listbean) {
        this.listbean = listbean;
    }

    public void setOpsbean(Opsbean.ResultBean opsbean) {
        this.opsbean = opsbean;
    }

    public MyOpsrightAdapter(Context context) {
        listhead.add("1");
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            return new MyNoQuestionViewHoder(inflater.inflate(R.layout.activity_ops_item_right_no, parent, false));
        } else {
            return new MyViewHoder(inflater.inflate(R.layout.activity_ops_item_right, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyNoQuestionViewHoder) {
            ((MyNoQuestionViewHoder) holder).opsItemRightNoTv.setText("全部设备运转良好，请继续保持！");
        }
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
            //没问题的  处理过的
           if(listbean.get(position).getStatus()==1){
               switch (listbean.get(position).getPatrolType()) {
                   //柜体
                   case 1:
                       ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_cabinet_processed);
                       break;
                   //温度
                   case 2:
                       ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_temperature_processed);
                       break;
                   //普通
                   case 3:
                       ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_common_processed);
                       break;
                   //电表
                   case 4:
                       ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_electricity_processed);
                       break;
                   //变压器
                   case 5:
                       ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_temperature_processed);
                       break;
                   //卫生
                   case 6:
                       ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_sanitation_processed);
                       break;
               }
           }
            //有问题的  未处理过的
            if(listbean.get(position).getStatus()==0){
                switch (listbean.get(position).getPatrolType()){
                    //柜体
                    case 1:
                        ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_cabinet_pending);
                        break;
                    //温度
                    case 2:
                        ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_temperature_pending);
                        break;
                    //普通
                    case 3:
                        ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_common_pending);
                        break;
                    //电表
                    case 4:
                        ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_electricity_pending);
                        break;
                    //变压器
                    case 5:
                        ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_temperature_pending);
                        break;
                    //卫生
                    case 6:
                        ((MyViewHoder) holder).opsItemImg.setImageResource(R.mipmap.ops_sanitation_pending);
                        break;

                }
            }


        }
    }

    @Override
    public int getItemViewType(int position) {
        if (opsbean.getCount()== 0&&position<listhead.size()) {
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
        @BindView(R.id.ops_item_img)
        ImageView opsItemImg;
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
//    public void apapterClear(){
//        for (int i = 0; i < listhead.size(); i++) {
//            listhead.remove(i);
//        }
//    }
}
