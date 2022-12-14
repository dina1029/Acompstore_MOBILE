package com.example.acompstore.pAdditional;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.acompstore.R;

public class ErrorDialog extends AlertDialog {

    private Context context;
    private TextView nullHead;
    private TextView nullDesc;
    private AppCompatButton nullbterror;
    private AlertDialog alert;

    public ErrorDialog(Context context, String textHead, String textDesc) {
        super(context);
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = getLayoutInflater().inflate(R.layout.error_null_dialog, null);
        builder.setView(view);
        alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
        nullbterror = view.findViewById(R.id.nulldialog_btclose);
        nullHead = view.findViewById(R.id.nulldialog_header);
        nullDesc = view.findViewById(R.id.nulldialog_body);
        nullbterror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });
        nullHead.setText(textHead);
        nullDesc.setText(textDesc);
    }
}
