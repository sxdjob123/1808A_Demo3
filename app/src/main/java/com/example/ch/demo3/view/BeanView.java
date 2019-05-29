package com.example.ch.demo3.view;

import com.example.ch.demo3.bean.Bean;

import java.util.List;

public interface BeanView {
    void onSuccess(List<Bean.ResultsEntity> list);

    void onFail(String error);
}
