package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AlertDialog;

class MessageBox {


    public enum MessageResults {
        WAITING,
        YES,
        NO,
        CANCEL}

    static MessageResults MessageResult = MessageResults.WAITING;

    static void CreateMessageBox(Context context, String Title, String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
         else
            builder = new AlertDialog.Builder(context);

        builder.setTitle(Title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                MessageResult = MessageResults.YES;
                            }
                        })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                MessageResult = MessageResults.NO;
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
