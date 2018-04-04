package tties.cn.energy.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import tties.cn.energy.R;
import tties.cn.energy.model.result.AllElectricitybean;

/**
 * Created by li on 2018/3/28
 * description：
 * author：guojlli
 */

public class StyleAdapter extends BaseAdapter {

    private Context mContext;
    AllElectricitybean allElectricitybean;
    public StyleAdapter(Context context, AllElectricitybean allElectricitybean) {
        mContext = context;
        this.allElectricitybean=allElectricitybean;
    }

    @Override
    public int getCount() {
        return allElectricitybean!=null?allElectricitybean.getMeterList().size():0;
    }

    @Override
    public Object getItem(int i) {
        return allElectricitybean.getMeterList().get(i);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_dialog_style, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_dialog_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(allElectricitybean.getMeterList().get(position).getMeterName());

        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
    }

}
