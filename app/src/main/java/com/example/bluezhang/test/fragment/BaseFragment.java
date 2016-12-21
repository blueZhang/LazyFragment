package com.example.bluezhang.test.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by blueZhang on 2016/12/21.
 *
 * @Author: BlueZhang
 * @date: 2016/12/21
 */

public abstract class BaseFragment extends Fragment {
    private boolean isVisible = false;
    private boolean isInitView = false;//To establish a mapping relationship with view
    private boolean isFirstLoad = true;

    private View convertView;
    private SparseArray<View> mViews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(getClass().getSimpleName(), "<-----------onCreateView---------->");
        convertView = inflater.inflate(getLayoutId(), container, false);
        mViews = new SparseArray<>();
        initView();
        isInitView = true;
        lazyLoadData();
        return convertView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("   " + this.getClass().getSimpleName(),"<---------onViewCreated-------------->");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("   " + this.getClass().getSimpleName(),"<---------onAttach-------------->");

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("   " + this.getClass().getSimpleName(),"<---------setUserVisibleHint-------------->");
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    private void lazyLoadData() {
        if (isFirstLoad) {
            Log.e(getClass().getSimpleName(),"第一次加载 " + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName());
        } else {
            Log.e(getClass().getSimpleName(),("不是第一次加载" + " isInitView  " + isInitView + "  isVisible  " + isVisible + "   " + this.getClass().getSimpleName()));
        }
        if (!isFirstLoad || !isVisible || !isInitView) {
            Log.e(getClass().getSimpleName(),"不加载" + "   " + this.getClass().getSimpleName());
            return;
        }

        Log.e(getClass().getSimpleName(),"完成数据第一次加载");
        initData();
        isFirstLoad = false;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId() ;

    /**
     * fragment中可以通过这个方法直接找到需要的view，而不需要进行类型强转
     * @param viewId
     * @param <E>
     * @return
     */
    protected <E extends View> E findView(int viewId) {
        if (convertView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = (E) convertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

}
