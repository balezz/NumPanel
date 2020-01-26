package ru.balezz.numpanel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class NumPickerFragment extends DialogFragment {
    static final String PANEL_INDEX = "panel_index";
    static final String EXTRA_NUMBER = "number";
    static final int CANCEL = -1;
    private static final String TAG = "NumPickerFragment";
    View mDecorView;
    List<Button> mButtons = new ArrayList<>(10);
    Button btnCancel;
    int mPanelIndex;

    public static NumPickerFragment newInstance(int panel) {
        Bundle args = new Bundle();
        args.putInt(PANEL_INDEX, panel);
        NumPickerFragment npf = new NumPickerFragment();
        npf.setArguments(args);
        return npf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDecorView = getActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        mDecorView.setSystemUiVisibility(uiOptions);
        mPanelIndex = getArguments().getInt(PANEL_INDEX);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_layout, container);
        setListeners(v);
        return v;
    }

    private void sendResult(int number) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NUMBER, number);
        intent.putExtra(PANEL_INDEX, mPanelIndex);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        Log.i(TAG, "sendResult: " + number);
        dismiss();
    }

    private void setListeners(View v) {
        mButtons.add((Button) v.findViewById(R.id.btn0));
        mButtons.add((Button) v.findViewById(R.id.btn1));
        mButtons.add((Button) v.findViewById(R.id.btn2));
        mButtons.add((Button) v.findViewById(R.id.btn3));
        mButtons.add((Button) v.findViewById(R.id.btn4));
        mButtons.add((Button) v.findViewById(R.id.btn5));
        mButtons.add((Button) v.findViewById(R.id.btn6));
        mButtons.add((Button) v.findViewById(R.id.btn7));
        mButtons.add((Button) v.findViewById(R.id.btn8));
        mButtons.add((Button) v.findViewById(R.id.btn9));
        btnCancel = v.findViewById(R.id.btn_cancel);

        int count = 0;
        for (Button btn : mButtons) {
            final int i = count;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendResult(i);
                }
            });
            count++;
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(CANCEL);
            }
        });
    }
}
