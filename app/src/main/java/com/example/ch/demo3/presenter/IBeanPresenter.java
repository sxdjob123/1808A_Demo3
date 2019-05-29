package com.example.ch.demo3.presenter;

import com.example.ch.demo3.bean.Bean;
import com.example.ch.demo3.callback.BeanCallBack;
import com.example.ch.demo3.model.BeanModel;
import com.example.ch.demo3.view.BeanView;

import java.util.List;

public class IBeanPresenter implements BeanPresenter, BeanCallBack {

    private BeanModel beanModel;
    private BeanView beanView;

    public IBeanPresenter(BeanModel beanModel, BeanView beanView) {
        this.beanModel = beanModel;
        this.beanView = beanView;
    }

    @Override
    public void getData() {
        if (beanModel != null) {
            beanModel.getData(this);
        }
    }

    @Override
    public void onSuccess(List<Bean.ResultsEntity> list) {
        if (beanView != null) {
            beanView.onSuccess(list);
        }
    }

    @Override
    public void onFail(String error) {
        if (beanView != null) {
            beanView.onFail(error);
        }
    }
}
