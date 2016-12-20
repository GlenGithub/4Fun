package com.joker.fourfun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joker.fourfun.R;
import com.joker.fourfun.base.BaseMvpFragment;
import com.joker.fourfun.model.Picture;
import com.joker.fourfun.presenter.PictureChildPresenter;
import com.joker.fourfun.presenter.contract.PictureChildContract;
import com.joker.fourfun.utils.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureChildFragment extends BaseMvpFragment<PictureChildContract.View,
        PictureChildPresenter> implements PictureChildContract.View {
    @BindView(R.id.iv_content)
    ImageView mIvContent;
    @BindView(R.id.tv_VOL)
    TextView mTvVOL;
    @BindView(R.id.tv_author)
    TextView mTvAuthor;
    @BindView(R.id.tv_des)
    TextView mTvDes;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    // 网页更新慢一天，所以默认值为 1
    private int mDay = 1;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_child, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getContent(mDay * -1);
    }

    public void setFragmentPosition(int position) {
        // 网页更新慢一天 故要 +1
        mDay = position + 1;
    }

    @Override
    public void showContent(Picture picture) {
        GlideUtil.setImage(mActivity, picture.getPicUrl(), mIvContent);
        mTvAuthor.setText(picture.getAuthorWork());
        mTvVOL.setText(picture.getVOL());
        mTvDes.setText(picture.getPicDescription());
        mTvDate.setText(picture.getPicDate().replace("-", " "));
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void initInject() {
        getComponent().inject(this);
    }
}
