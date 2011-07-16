package com.SAB_v1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

public final class MyAlarmServiceCalllist extends BroadcastReceiver
{	private static final int NOTIFICATION_ID = 1000;
private String listnames;

@Override

public void onReceive(Context context, Intent intent) 
{	
	//  Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
	Bundle bun=intent.getExtras();
	listnames=bun.getString("listname");
	System.out.println("task.."+listnames);
	NotificationManager notificationManager = 
		(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

	PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, null,  PendingIntent.FLAG_CANCEL_CURRENT);		

	Notification notification = createNotification();
	notification.setLatestEventInfo(context, "Call List Alert!",listnames, pendingIntent);
	notificationManager.notify(NOTIFICATION_ID, notification);

}

private Notification createNotification() {
	Notification notification = new Notification();
	notification.icon = R.drawable.ic_menu_notifications;
	notification.when = System.currentTimeMillis();
	notification.flags |= Notification.FLAG_AUTO_CANCEL;
	notification.flags |= Notification.FLAG_SHOW_LIGHTS;
	notification.defaults |= Notification.DEFAULT_VIBRATE;
	notification.defaults |= Notification.DEFAULT_LIGHTS;
	notification.defaults |= Notification.DEFAULT_SOUND;
	notification.ledARGB = Color.WHITE;
	notification.ledOnMS = 1500;
	notification.ledOffMS = 1500;

	return notification;
}
}
