package hu.renesans.eredmenyek.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {
    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contentView = findViewById(android.R.id.content);
    }

    public final void showSnackbar(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_LONG)
                .setAction(android.R.string.ok, v -> {
                }).show();
    }
}
