package com.haiyangroup.scampus.present;

import com.haiyangroup.scampus.common.EndObserver;
import com.haiyangroup.scampus.common.MyScope;
import com.haiyangroup.scampus.data.AbsReturn;
import com.haiyangroup.scampus.data.DefaultData;
import com.haiyangroup.scampus.model.AttendanceTeacherInfoModel;
import com.haiyangroup.scampus.view.AttendanceTeacherInfoView;

import javax.inject.Inject;

import compartment.BasePresenter;
import rx.Observer;
import rx.Subscription;

/**
 * 实现MVP设计模式的present指挥者，用来控制model与view的交互
 * @author 何伟杰
 * @version 1.0, 2016/5/11
 */
@MyScope
public class AttendanceTeacherInfoPresenter extends BasePresenter<AttendanceTeacherInfoView> {
    @Inject
    AttendanceTeacherInfoModel model;

    @Inject
    AttendanceTeacherInfoPresenter() {
    }

    private Subscription mAttendanceTeacherInfoSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAttendanceTeacherInfoSubscription != null && !mAttendanceTeacherInfoSubscription.isUnsubscribed()) {
            mAttendanceTeacherInfoSubscription.unsubscribe();
        }

    }


    private Observer<AbsReturn<DefaultData>> AttendanceTeacherInfoObserver = new EndObserver<AbsReturn<DefaultData>>() {
        @Override
        public void onEnd() {
            if (getBaseView() != null) {
                getBaseView().hideLoading();
            }
        }

        @Override
        public void onMyNext(AbsReturn<DefaultData> entity) {
            getBaseView().hideLoading();
        }
    };
}
