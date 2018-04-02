package tties.cn.energy.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewActivity;

import java.util.ArrayList;
import java.util.List;

import tties.cn.energy.R;
import tties.cn.energy.application.MyApplication;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.view.activity.QuestionsActivity;

/**
 * Created by li on 2018/4/2
 * description：
 * author：guojlli
 */

public class DescriptionListViewAdapter extends BaseAdapter {
    Context context;
    Opsbean.ResultBean.QuestionListBean.DescriptionListBean bean;
    public DescriptionListViewAdapter(Context context,Opsbean.ResultBean.QuestionListBean.DescriptionListBean bean){
        this.context=context;
        this.bean=bean;
    }
    @Override
    public int getCount() {
        return bean!=null?bean.getImageList().size():0;
    }

    @Override
    public Object getItem(int i) {
        return bean.getImageList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view == null) {
            view = View.inflate(context,R.layout.listview_description,null);
            holder=new ViewHolder();
            holder.recyclerView=view.findViewById(R.id.recyclerView);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        if (bean.getImageList() != null) {
            ImageAdapter adapter = new ImageAdapter(context, bean.getImageList());
            DescriptionListViewAdapter.OnRecyclerViewItemClickListener clickListener = new DescriptionListViewAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, final int pos) {

                    String path = bean.getImageList().get(i).path;
                    //打开预览
                    Intent intentPreview = new Intent(context, ImagePreviewActivity.class);
                    ImageItem item = new ImageItem();
                    item.path = path;
                    List<ImageItem> list = new ArrayList<>();
                    list.add(item);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) list);
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, 0);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    ((QuestionsActivity)context).startActivityForResult(intentPreview, 101);
                }
            };
            adapter.setOnItemClickListener(clickListener);
            holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setAdapter(adapter);
        }

        return view;
    }
    class ViewHolder{
        RecyclerView recyclerView;
    }
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.SelectedPicViewHolder> {
        private Context mContext;
        private List<ImageItem> mData;
        private LayoutInflater mInflater;
        private OnRecyclerViewItemClickListener listener;

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            this.listener = listener;
        }

        public void setImages(List<ImageItem> data) {
            mData = new ArrayList<>(data);
            notifyDataSetChanged();
        }

        public List<ImageItem> getImages() {
            return mData;
        }

        public ImageAdapter(Context mContext, List<ImageItem> data) {
            this.mContext = mContext;
            this.mInflater = LayoutInflater.from(mContext);
            setImages(data);
        }

        @Override
        public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SelectedPicViewHolder(mInflater.inflate(R.layout.listview_image, parent, false));
        }

        @Override
        public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView iv_img;
            private int clickPosition;

            public SelectedPicViewHolder(View itemView) {
                super(itemView);
                iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            }

            public void bind(int position) {
                //设置条目的点击事件
                itemView.setOnClickListener(this);
                //根据条目位置设置图片
                ImageItem item = mData.get(position);
                ImagePicker.getInstance().getImageLoader().displayImage((QuestionsActivity) context, "http://192.168.2.127:8080/photo/%E5%A4%B4%E5%83%8F1.jpg", iv_img, 0, 0);
//                Glide.with(context).load(item.path).into(iv_img);
//
                clickPosition = position;
            }

            @Override
            public void onClick(View v) {
                if (listener != null) listener.onItemClick(v, clickPosition);
            }
        }
    }
}
