<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">



## MBurger: Push

If your project it configured to send push notifications you must use this set of API to proper register your users' devices tor receive push notifications. Also you'll need to have basic knowledge of Google Firebase and Firebase Cloud Messaging.



### Before you start

You must **create a Firebase project** for your application, this way you'll have the push server key to insert in your `MBurger Push project`. This will generate a push API KEY.

To create a new Firebase project, please reference [this](https://firebase.google.com/docs/android/setup) documentation.
Remember that you'll need at least these Firebase dependecies:

```java
com.google.firebase:firebase-core
com.google.firebase:firebase-messaging
```

For **FCM client setup** refer [this](https://firebase.google.com/docs/cloud-messaging/android/client) documentation. Long story short you'll ned to extend a new `FirebaseMessagingService`  which will trigger `onNewToken` to obtain the Firebase token which you'll need to send to the MBurger API and `onMessageReceived`, an event triggered when a push message is received.
Be aware that **MBurger uses the "data" message** types in order to permit the developers to completely customize notifications and behaviour on receiveing notifications (you'll find all about data messages [here](https://firebase.google.com/docs/cloud-messaging/concept-options))

After you have set up your project for Firebase you can start using Nooko SDK to register users receive FCM messages.



### Register a device

To register a new device you'll need to have a Firebase token, which you can obtain one from your `FirebaseMessagingService` method  `onNewToken`:

```java
@Override
public void onNewToken(String token) {
}
```

 Then is a best practice to register your device calling the registration API:

```java
@Override
public void onNewToken(String token) {
    MBurgerPushTasks.sendToken(getApplicationContext(), getDeviceID(), token);
}
```

Where `getDeviceID()` is your method to obtain the **Android ID**  which will be your unique identifier. Pay attention to the changes Oreo makes to this data, refer to [this documentation](https://developer.android.com/reference/android/provider/Settings.Secure#ANDROID_ID).
Now your device is ready to receive push messages with your `FirebaseMessagingService` method `onMessageReceived`, but if you need to differentiate push groups you may need to use **topics**.



### Subscribe to topics

A topic is like a group you can subscribe in order to send push notifications specifically to that topic, you can subscribe to multiple topics creating a JSONArray with the topic names, then call the API:

```java
JSONArray topics = new JSONArray();

//You can decide whatever name you wish for the topics.
topics.put("topic1");

MBurgerPushTasks.registerTopics(context, getDeviceID(), topics);
```

From the `MBurger Push dashboard` then you can send push notification only to some topics of make an app send push notifications to a specific topic. While it's not necessary to subscribe to topics at every startup, it can be useful to resubscribe anytime your `InstanceID` changes in order to mantain data coherence.

When you receive a push notification then your `FirebaseMessagingService` will be triggered and you will find all the data you inserted on the "**data**" field of the **RemoteMessage** object.

```java
@Override
public void onMessageReceived(RemoteMessage remoteMessage) {
     Map<String, String> map = remoteMessage.getData();

     //The standard message is inside the "body" field
     String msg = map.get("body");
     if(map.containsKey("custom")) {
         String custom = map.get("custom");
         if(custom != null){
             try {
                JSONObject jCustom = new JSONObject(custom);
			   //Take out the data you inserted inside the notification and create your notification with Android SDK.
             } catch (JSONException e) {
                    e.printStackTrace();
             }
         }
     }
}
```

To create a notification with the Andorid SDK refer [this documentation](https://developer.android.com/training/notify-user/build-notification).



### Push topics management

To unsubscribe from a push topic you'll need to call the **unregisterTopics**() API with the same fields you used with **registerTopics**(). You'll need to call `registerTopics()` in order to be subscribed to the topics.
You can also unsubscribe to all topics you subscribed by calling:

```java
MBurgerPushTasks.unregisterAllTopics(context, getDeviceId())
```

This way you'll no more receive push messages from any topic you registered the device before.

