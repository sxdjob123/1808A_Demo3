package com.example.ch.demo3;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ch.demo3.bean.Bean;
import com.example.ch.demo3.model.IBeanModel;
import com.example.ch.demo3.presenter.BeanPresenter;
import com.example.ch.demo3.presenter.IBeanPresenter;
import com.example.ch.demo3.view.BeanView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BeanView, BeanAdapter.MyOnLickListener {

    private RecyclerView mRv;
    private ViewPager mVp;
    private ArrayList<Bean.ResultsEntity> arrayList;
    private BeanAdapter adapter;
    private IBeanPresenter iBeanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mVp = (ViewPager) findViewById(R.id.vp);

        arrayList = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);

        adapter = new BeanAdapter(this, arrayList);
        mRv.setAdapter(adapter);

        iBeanPresenter = new IBeanPresenter(new IBeanModel(), this);
        iBeanPresenter.getData();

        adapter.setMyOnLickListener(this);

    }

    @Override
    public void onSuccess(List<Bean.ResultsEntity> list) {
        arrayList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Log.i("tag", error);
    }

    @Override
    public void MyClick(int position, Bean.ResultsEntity resultsEntity) {
        mRv.setVisibility(View.GONE);
        mVp.setVisibility(View.VISIBLE);

        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.item_vp, null);
            ImageView mIv = view.findViewById(R.id.iv_vp);
            TextView mTv = view.findViewById(R.id.tv_vp);

            Glide.with(this).load(resultsEntity.getUrl())
                    .into(mIv);

            mTv.setText(i + 1 + "/" + arrayList.size());

            views.add(view);
        }


        MyVpAdapter myVpAdapter = new MyVpAdapter(views);
        mVp.setAdapter(myVpAdapter);
        myVpAdapter.notifyDataSetChanged();

        mVp.setCurrentItem(position);
    }
}
