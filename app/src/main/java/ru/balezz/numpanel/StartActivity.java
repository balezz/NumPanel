package ru.balezz.numpanel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class StartActivity extends AppCompatActivity {
    public static final String EXTRA_COLOR = "extra_color";
    private static final String TAG = "StartActivity";

    Button mButtonBlack;
    Button mButtonGreen;
    Button mButtonRed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mButtonBlack = findViewById(R.id.btn_black);
        setListener(mButtonBlack, Color.BLACK);

        mButtonGreen = findViewById(R.id.button_green);
        setListener(mButtonGreen, Color.GREEN);

        mButtonRed = findViewById(R.id.button_red);
        setListener(mButtonRed, Color.RED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(R.string.info_title);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Log.i(TAG, "onOptionsItemSelected: select info");
                new AlertDialog.Builder(this)
                        .setMessage(R.string.info)
                        .setPositiveButton(android.R.string.ok, null)
                        .create()
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setListener(Button button, final int color) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, MainActivity.class);
                i.putExtra(EXTRA_COLOR, color);
                startActivity(i);
            }
        });
    }
}
