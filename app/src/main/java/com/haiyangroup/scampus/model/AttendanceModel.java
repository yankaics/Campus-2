package com.haiyangroup.scampus.model;


import com.haiyangroup.library.utils.SharedPreferencesUtil;
import com.haiyangroup.scampus.common.App;
import com.haiyangroup.scampus.common.SchedulerProvider;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.AbsService;
import com.haiyangroup.scampus.entity.AttendanceEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import rx.Observable;

/**
 * 实现MVP设计模式的model，用来从服务端提取数据，并提供数据接口供present使用
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@Module
public class AttendanceModel {
    private final SchedulerProvider schedulerProvider = SchedulerProvider.DEFAULT;
    private final AbsService mService = new AbsService();

    @Inject
    public AttendanceModel() {
    }

    public Observable<AbsReturn<ArrayList<AttendanceEntity>>> getAttendance(String weekNum){
        return mService.getApi().getAttendance(weekNum, SharedPreferencesUtil.getInstance(App.getInstance()).getString("session")).compose(schedulerProvider.applySchedulers());
    }
}