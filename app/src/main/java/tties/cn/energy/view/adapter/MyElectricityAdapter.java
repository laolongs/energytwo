package tties.cn.energy.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import tties.cn.energy.R;
import tties.cn.energy.model.result.AllElectricitybean;
import tties.cn.energy.model.result.OpsLoginbean;

/**
 * Created by li on 2018/3/28
 * description：
 * author：guojlli
 */

public class MyElectricityAdapter extends BaseAdapter {

    private Context mContext;
    OpsLoginbean bean;
    public MyElectricityAdapter(Context context, OpsLoginbean bean) {
        mContext = context;
        this.bean=bean;
    }
    @Override
    public int getCount() {
        return bean!=null?bean.getResult().getEnergyLedgerList().size():0;
    }

    @Override
    public Object getItem(int i) {
        return bean.getResult().getEnergyLedgerList().get(i);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_dialog_style, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_item_dialog_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(bean.getResult().getEnergyLedgerList().get(position).getEnergyLedgerId()+"");
        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
    }
}
