package com.example.ch.demo3.callback;

import com.example.ch.demo3.bean.Bean;

import java.util.List;

public interface BeanCallBack {
    void onSuccess(List<Bean.ResultsEntity> list);

    void onFail(String error);
}
