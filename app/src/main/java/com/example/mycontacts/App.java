package com.example.mycontacts;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    //declare and initialize a Channel Id
    public static final String CHANNEL_CONTACT_ID = "channelcontact";

    //override the oncreate method
    @Override
    public void onCreate(){
        super.onCreate();
        //call method that creates notif channel for contact
        createNotificationChannel();
    }

    /**
     * This method creates the Notification Channel
     */
    private void createNotificationChannel(){

        //check if android oreo or api 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channelcontact = new NotificationChannel(
                    CHANNEL_CONTACT_ID,
                    "Channel Contact",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            //customize the notification channel
            channelcontact.setDescription("This is the contact channel");

            //initalize a notification manager
            NotificationManager manager = getSystemService(NotificationManager.class);

            //create contact notif
            manager.createNotificationChannel(channelcontact);
        }

    }

}
