package com.cheng.androidart;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by 李国财 on 2018-01-30.
 */

public class MyAppWidgetProvider extends AppWidgetProvider {

    private static final String TAG = MyAppWidgetProvider.class.getSimpleName();
    private static final String TAG_CHENG = "guang: ";

    private static final String CLICK_ACTION = "com.cheng.androidart.action.CLICK";



    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.i(TAG,TAG_CHENG+"onReceive");
        super.onReceive(context, intent);
        if (intent.getAction().equals(CLICK_ACTION)){
            Toast.makeText(context,"CLICKED!",Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbBitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    for (int i = 0; i<37; i++){
                        float degree = (i*30)%360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.layout_appwidget);
                        remoteViews.setImageViewBitmap(R.id.imageView,rotateBitmap(context,srcbBitmap,degree));
                        remoteViews.setTextViewText(R.id.textView,"degree:"+degree);
                        Intent intentClick = new Intent(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,PendingIntent.FLAG_UPDATE_CURRENT);
                        remoteViews.setOnClickPendingIntent(R.id.imageView,pendingIntent);
                        appWidgetManager.updateAppWidget(new ComponentName(context,MyAppWidgetProvider.class),remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i(TAG,TAG_CHENG+"onUpdate");
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        int counter = appWidgetIds.length;
        for(int i=0;i<counter;i++){
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context,appWidgetManager,appWidgetId);
        }
    }

    private void onWidgetUpdate(Context context, AppWidgetManager appWidgetManager, int appWidgetId){
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.layout_appwidget);
        Intent intentClick = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.imageView,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }

    private Bitmap rotateBitmap(Context context, Bitmap srcbBitmap, float degree){
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap tmpBitmap = Bitmap.createBitmap(srcbBitmap,0,0,srcbBitmap.getWidth(),srcbBitmap.getHeight(),matrix,true);
        return tmpBitmap;
    }
}
