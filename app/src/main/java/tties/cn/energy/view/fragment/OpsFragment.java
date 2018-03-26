package tties.cn.energy.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import tties.cn.energy.R;
import tties.cn.energy.base.BaseFragment;
import tties.cn.energy.model.result.Opsbean;
import tties.cn.energy.presenter.OpsPresenter;
import tties.cn.energy.utils.PtrClassicFoot;
import tties.cn.energy.utils.PtrClassicHeader;
import tties.cn.energy.utils.ToastUtil;
import tties.cn.energy.view.activity.QuestionsActivity;
import tties.cn.energy.view.adapter.MyOpsrightAdapter;
import tties.cn.energy.view.iview.IOpsView;

/**
 * Created by li on 2018/3/21
 * description：
 * author：guojlli
 */

public class OpsFragment extends BaseFragment<OpsPresenter> implements IOpsView {


    @BindView(R.id.ops_toolbar_img)
    ImageView opsToolbarImg;
    @BindView(R.id.ops_toolbar_text)
    TextView opsToolbarText;
    @BindView(R.id.data_toolbar)
    Toolbar dataToolbar;
    Unbinder unbinder;
    @BindView(R.id.ops_rcy_right)
    RecyclerView opsRcyRight;
    @BindView(R.id.ops_rcy_left_bt1)
    RadioButton opsRcyLeftBt1;
    @BindView(R.id.ops_rcy_left_bt2)
    RadioButton opsRcyLeftBt2;
    @BindView(R.id.ops_rcy_left_bt3)
    RadioButton opsRcyLeftBt3;
    @BindView(R.id.ops_rcy_left_bt4)
    RadioButton opsRcyLeftBt4;
    @BindView(R.id.ops_rcy_left_bt5)
    RadioButton opsRcyLeftBt5;
    @BindView(R.id.ops_rcy_left_bt6)
    RadioButton opsRcyLeftBt6;
    @BindView(R.id.ops_rcy_left_rg)
    RadioGroup opsRcyLeftRg;
    @BindView(R.id.ops_number)
    TextView opsNumber;
    @BindView(R.id.ops_refreshLayout)
    PtrFrameLayout opsRefreshLayout;
    private List<Opsbean.ResultBean> list;
    boolean flag=false;
    int item=5;
    int allnumber=0;
    Opsbean opsbean;
    private MyOpsrightAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_ops, null);
        unbinder = ButterKnife.bind(this, inflate);
        opsRcyLeftRg.check(R.id.ops_rcy_left_bt1);
        initView();
        initRefresh();
        mPresenter.getOpsRightData();

        return inflate;
    }

    private void initRefresh() {
        //上拉加载 下拉刷新
        PtrClassicFoot foot = new PtrClassicFoot(getActivity());
        PtrClassicHeader header = new PtrClassicHeader(getActivity());
        opsRefreshLayout.setHeaderView(header);
        opsRefreshLayout.setFooterView(foot);
        opsRefreshLayout.addPtrUIHandler(header);
        opsRefreshLayout.addPtrUIHandler(foot);
        opsToolbarImg.setImageResource(R.mipmap.ic_login_logo);
        opsRefreshLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                Log.i("-----------", "onLoadMoreBegin: "+"111111");
                flag=true;
                item=item+5;
                setlist(item);
                adapter.notifyDataSetChanged();
                opsRefreshLayout.refreshComplete();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                Log.i("-----------", "onLoadMoreBegin: "+"111111");
                opsRefreshLayout.refreshComplete();
            }
        });
    }

    private void initView() {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new OpsPresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setOpsRightData(Opsbean opsbean) {
        View view=View.inflate(getActivity(),R.layout.activity_ops_item_right_no,null);
        this.opsbean=opsbean;
        allnumber=opsbean.getResult().size();
        Log.i("--------", "setOpsRightData: "+opsbean.getResult().size());
        list = new ArrayList<>();
        setlist(item);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        opsRcyRight.setLayoutManager(manager);
        adapter = new MyOpsrightAdapter(list);
        adapter.setHeadView(view);
        adapter.setAllOpsbean(opsbean);
        opsRcyRight.setAdapter(adapter);
        opsNumber.setText(adapter.getQuestionflag() + "");
        adapter.notifyDataSetChanged();
        adapter.setonClickListener(new MyOpsrightAdapter.onClickListener() {
            @Override
            public void onClickItemListener(int postion) {
                Intent intent = new Intent(getActivity(), QuestionsActivity.class);
                intent.putExtra("questionid", postion);
                startActivity(intent);
            }
        });
    }
    public void setlist(int item){
            if(flag&&item<=allnumber){
                for (int i = 0; i < item; i++) {
                    list.add(opsbean.getResult().get(i));
                }
            }else{
                if(item>=allnumber){
                    for (int i = 0; i < item-allnumber; i++) {
                        list.add(opsbean.getResult().get(i));
                        ToastUtil.showShort(getActivity(),"数据已加载完毕");
                    }
                }
                for (int i = 0; i < item; i++) {
                    list.add(opsbean.getResult().get(i));
                }
            }
    }

}
