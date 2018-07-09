<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">



## Nooko 3: Pay

If your project it configured to accept subscriptions, you can use the "Pay" features of Nooko3.
You will need to check the `Nooko3PayTasks` class, where you will find all static methods to use pay API, both with "action" and "listener" approach.



### Before you start

The pay api, uses Stripe™ to process payments and subscriptions, so, before you start, you should create a Stripe account from [here](https://dashboard.stripe.com/login). Then you should provide the Nooko dashboard with the secret key you will find inside the "Developers -> API Keys" section if your Stripe™ dashboard.

![Stripe™ Api Keys](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_dashboard_keys.JPG)

Then also copy the Publishable key inside you app, because you will need it to create new customers and add new credit cards. You SHOULD NOT write the secret API key inside you application.

Your application should also need to add Stripe™ as a dependency (please check out [Stripe™ documentation](https://stripe.com/docs/mobile/android) here to learn how to add it to your project and gain confidence with Stripe™ development).

Be aware you should first use the test keys and Stripe™ devMode before using the live keys, in order to check if your code is working properly.

Also, in order to charge an user for a subscription you should, first, create a "Product" on Stripe™ dashboard and add a paying plan to it:

![Stripe™ product](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_product.JPG)


Set a price and a billing interval

![Stripe™ pricing plan](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_product_payment.JPG)



Take note of the plan id (starts with **plan_**), you will need inside your app.

![Stripe™ product plan id](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_product_plan.JPG)





### Creating and charge a customer

You will need to have an user logged in on your application in order to create a new customer and subscribe it to the previously created plan. So, check out the [Auth](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/tree/develop/nooko3/src/main/java/mumble/nooko3/sdk/NKAuth) section if you have not done that before.
After that you can just create a new customer or create a customer and already start the new subscribe plan. In order to do the first you have just to call

```java
Nooko3PayTasks.createCustomer(context);
```

Then next time you call the "profile" API you will have new informations about the customer logged in.

To create a customer, add a card and also charge it for the first payment cycle, you should first gain a **Stripe™ token**, which can be created using the **Stripe™ SDK** from a card (check out the [documentation](https://stripe.com/docs/mobile/android) for reference), then you can use this API:

```java
//Stripe™ token obtained via the SDK from a card
String stripe_token;

//The subscription plan id of your Stripe™ product
String subscription_plan = "plan_12345789";

//If you plan to use discount code, you can provide it in the API, otherwise leave it null
String discount_code = null;

//Eventual metadata to add, null if you don't need to add anything
String meta = null;

//Quantity to charge, usually 1
int quantity = 1;

//If your plan provides trial days, you can set them, otherwise, set as -1
int trial_days = 30;

Nooko3PayTasks.subscribeToPlan(getApplicationContext(), new NKPayApiSubscribeListener() {
            @Override
            public void onSubscriptionSuccess() {
                //DO SOMETHING
            }

            @Override
            public void onSubscriptionError(String error) {
                //SHOW ERROR
            }
        }, 
subscription_plan, stripe_token, discount_code, meta, quantity, trial_days);
```

Then next time you call the "profile" API you will have new informations about the customer logged in, including the subscriptions he has.

Automatically Stripe™ will charge an user with the inserted card, if there is not enough money on it or the payment will go wrong, you will know when you call the profile api.



### Cards

To **retrieve the cards** associated with a customer, you should call the API

```java
Nooko3PayTasks.getCards()
```

which will return an array of `NKStripeCard`, that represent a credit card associated with the customer.
Stripe secures the card data so you can not read the complete number (only the last 4 digits, the brand and the expiration date).

You can **add a new card** by using the API

```java
Nooko3PayTasks.addCard(context, token)
```

Where the token is a new Stripe™ token generated from the Android SDK.
Pay attention that each token can be used only one time, so if you need to do multiple things with a single card, you should request multiple tokens.

In order to **delete an existing customer card**, you will need a `card_id` which can be gathered from the `getCards()` API.

```java
Nooko3PayTasks.deleteCard(context, card_id)
```

When you add a new card, it becomes automatically the default one, so the one Stripe™ will use to charge the customer once the current period ends. In order to **change the default card** you should call the API:

```java
Nooko3PayTasks.changeDefaultCard(context, card_id)
```



### Subscription status

To **cancel a running subscription** you should call the API

```java
Nooko3PayTasks.cancelPlan(context, subscription_plan)
```

Where subscription_plan is the subscription plan id from the Stripe™ dashboard.
Pay attention, though, if you cancel a subscription, it will be in a "<u>grace</u>" period (which usually it's the time left before the next payment period starts), in which can be **resumed** without any problem calling the API.

```java
Nooko3PayTasks.resumePlan(context, subscription_plan)
```

If you try to resume a subscription after the "<u>grace</u>" period, it will result in an error.

**In order to restore a plan you should call the "subscribe" API again**, but without providing a Stripe™ token (null), in this way the current customer associated with the Nooko user will have a new subscription running.

