package tties.cn.energy.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import tties.cn.energy.R;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.utils.AppUtils;

/**
 * Created by li on 2018/4/2
 * description：
 * author：guojlli
 */

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {
    private static final String TAG = "QuestionListAdapter";
    Opsbean.ResultBean.QuestionListBean listbean;
    LayoutInflater inflater;
    Context context;



    public QuestionListAdapter(Context context, Opsbean.ResultBean.QuestionListBean listbean) {
        this.context = context;
        this.listbean = listbean;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.listview_question_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.listviewTitle.setText(listbean.getDescriptionList().get(position).getContent());
        DescriptionListViewAdapter adapter = new DescriptionListViewAdapter(context, listbean.getDescriptionList().get(position));
        holder.listViewDescription.setAdapter(adapter);
        AppUtils.setListViewHeight(holder.listViewDescription, 30);
    }

    @Override
    public int getItemCount() {
        return listbean == null ? 0 : listbean.getDescriptionList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.listview_title)
        TextView listviewTitle;
        @BindView(R.id.listview_description)
        ListView listViewDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
