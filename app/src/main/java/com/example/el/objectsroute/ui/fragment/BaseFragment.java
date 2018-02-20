package com.example.el.objectsroute.ui.fragment;

import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.el.objectsroute.presentation.view.BaseView;

/**
 * Created by el on 15.02.2018.
 */

public class BaseFragment extends MvpAppCompatFragment implements BaseView{
    @Override
    public void showError(int error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}
