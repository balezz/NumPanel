package ru.balezz.numpanel;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class FullscreenFragment extends Fragment {
    private static final String TAG = "FullscreenFragment";
    private static final int REQUEST_NUMBER = 0;

    ImageView mView0;
    ImageView mView1;
    ImageView mViewDot;

    View mDecorView;
    int uiOptions;
    public int mDigitsColor;

    static Fragment getInstance() {
        return new FullscreenFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDigitsColor = getActivity().getIntent().getIntExtra(StartActivity.EXTRA_COLOR, Color.BLACK);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fullscreen, container, false);
        mView0 = (ImageView) v.findViewById(R.id.n0);
        mView1 = (ImageView) v.findViewById(R.id.n1);
        mViewDot = (ImageView) v.findViewById(R.id.dot);

        Drawable d = getResources().getDrawable(R.drawable.ic_0);

        mView0.setImageDrawable(d);
        mView1.setImageDrawable(d);

        mDecorView = getActivity().getWindow().getDecorView();
        uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        mDecorView.setSystemUiVisibility(uiOptions);

        mDecorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        mDecorView.setSystemUiVisibility(uiOptions);
                        ActionBar actionBar = getActivity().getActionBar();
                        if (actionBar != null) {
                            actionBar.hide();
                        }
                    }
                });

        mView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                NumPickerFragment dialog = NumPickerFragment.newInstance(0);
                dialog.setTargetFragment(FullscreenFragment.this, REQUEST_NUMBER);
                dialog.show(fm, TAG);
            }
        });

        mView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                NumPickerFragment dialog = NumPickerFragment.newInstance(1);
                dialog.setTargetFragment(FullscreenFragment.this, REQUEST_NUMBER);
                dialog.show(fm, TAG);
            }
        });

        setColor();
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int number = (int) data.getIntExtra(NumPickerFragment.EXTRA_NUMBER, 1);
        Log.i(TAG, "onActivityResult: get number " + number);
        int panelIndex = (int) data.getIntExtra(NumPickerFragment.PANEL_INDEX, 0);
        if (number == NumPickerFragment.CANCEL) return;
        if (panelIndex == 0) {
            setNumber(mView0, number);
        } else if (panelIndex == 1) {
            setNumber(mView1, number);
        }
        setColor();
    }


    private void setColor() {
        Drawable d0 = DrawableCompat.wrap(mView0.getDrawable());
        Drawable d1 = DrawableCompat.wrap(mView1.getDrawable());
        Drawable dot = DrawableCompat.wrap(mViewDot.getDrawable());
        DrawableCompat.setTint(d0, mDigitsColor);
        DrawableCompat.setTint(d1, mDigitsColor);
        DrawableCompat.setTint(dot, mDigitsColor);

    }

    private void setNumber(ImageView imageView, int number) {
        switch (number) {
            case 0:
                imageView.setImageResource(R.drawable.ic_0);
                break;
            case 1:
                imageView.setImageResource(R.drawable.ic_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.ic_6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.ic_7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.ic_8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.ic_9);
                break;
            default:
                break;
        }
    }
}

