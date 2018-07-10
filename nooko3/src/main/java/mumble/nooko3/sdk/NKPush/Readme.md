<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">



## Nooko 3: Push

If your project it configured to send push notifications you must use this set of API to proper register your users' devices tor receive push notifications. Also you'll need to have basic knowledge of Google Firebase and Firebase Cloud Messaging.



### Before you start

You must **create a Firebase project** for your application, this way you'll have the push server key to insert in the "Settings" tab

![Push notification token location](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/push_notifications_token.JPG)



To create a new Firebase project, please reference [this](https://firebase.google.com/docs/android/setup) documentation.
Remember that you'll need at least these Firebase dependecies:

```java
com.google.firebase:firebase-core
com.google.firebase:firebase-messaging
```

For **FCM client setup** refer [this](https://firebase.google.com/docs/cloud-messaging/android/client) documentation. Long story short you'll ned to extend a new `FirebaseInstanceIdService` , which you'll need for obtaining the push token from your device and sent it to Nooko, and a `FirebaseMessagingService`  which will trigger an event when a push message is received.
Be aware that **Nooko uses the "data" message** types in order to permit the developers to completely customize notifications and behaviour on receiveing notifications (you'll find all about data messages [here](https://firebase.google.com/docs/cloud-messaging/concept-options))

After you have set up your project for Firebase you can start using Nooko SDK to register users receive CM messages.



### Register a device



### Subscribe to topics



### Push management





