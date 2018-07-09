<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">



## Nooko 3: Pay

If your project it configured to accept subscriptions, you can use the "Pay" features of Nooko3.
You will need to check the `Nooko3PayTasks` class, where you will find all static methods to use pay API, both with "action" and "listener" approach.



### Before you start

The pay api, uses Stripe™ to process payments and subscriptions, so, before you start, you should create a Stripe account from [here](https://dashboard.stripe.com/login). Then you should provide the Nooko dashboard with the secret key you will find inside the "Developers -> API Keys" section if your Stripe™ dashboard.

![Stripe Api Keys](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_dashboard_keys.JPG)

Then also copy the Publishable key inside you app, because you will need it to create new customers and add new credit cards. You SHOULD NOT write the secret API key inside you application.

Your application should also need to add Stripe™ as a dependency (please check out [Stripe™ documentation](https://stripe.com/docs/mobile/android) here to learn how to add it to your project and gain confidence with Stripe™ development).

Be aware you should first use the test keys and Stripe™ devMode before using the live keys, in order to check if your code is working properly.



### Creating a customer

In order to charge an user for a subscription you should, first, create a "Product" on Stripe™ dashboard and add a paying plan to it:

![Stripe product](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_product.JPG)


![Stripe pricing plan](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_product_payment.JPG)


![Stripe product plan id](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/develop/Images/stripe_product_plan.JPG)