package com.example.win_10.eurokegel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

class MessageBox {


    public enum MessageAsks {
        NONE,
        PLYONEGIVEUP,
        PLYTWOGIVEUP,
        REDBALLSHOT,
        PLYONEWIN,
        PLYTWOWIN
        }

    static MessageAsks MessageAsk = MessageAsks.NONE;
    static Context actualContext;
    private boolean mResult;

    static void CreateMessageBox(Context context, String Title, String message) {

        AlertDialog.Builder builder;
        actualContext = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
         else
            builder = new AlertDialog.Builder(context);

        builder.setCancelable(false);

        builder.setTitle(Title)
                .setMessage(message)
                .setPositiveButton(actualContext.getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                MessageAsk = MessageAsks.NONE;
                            }
                        })
                .setNegativeButton(actualContext.getResources().getString(R.string.No), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                if (MessageAsk == MessageAsks.REDBALLSHOT) {
                                    ((PointerActivity)actualContext).BackPressed();
                                }

                                MessageAsk = MessageAsks.NONE;
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
