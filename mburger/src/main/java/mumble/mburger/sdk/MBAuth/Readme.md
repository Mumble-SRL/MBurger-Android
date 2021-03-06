<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">

## MBurger: Auth Usage

If your project is configured to register users you can use the "Auth" features of MBurger.
You will need to check the `MBurgerAuthTasks` class, where you will find all static methods to use authentication API, both with "action" and "listener" approach.



### Register a new user

If you wish to register a new user you will have to call the `MBurgerAuthTasks.registerUser(...)` method. 
Be aware that a MBurger user contains these fields, some required, some unrequired, some autofilled when registering:

- (Auto-filled) **ID**
- (Required) **Name**
- (Required) **Surname**
- (Required) **Email**
- **Phone**
- **Image**
- **Auxiliar data**
- (Auto-filled) **Auth mode**

Where Auxiliar data is an arbitrary array you can pass while registering an user.

Registration of an user will be like:

```java
private MBAuthApiRegisterListener listener;
private EditText edt_name, edt_surname, edt_email, edt_password;
private String phone;
private Uri img_uri;
private String auxiliar_data;

MBurgerAuthTasks.registerUser(getApplicationContext(),
	listener,                             //Listener for registration
    edt_name.getText().toString(),        //Name, REQUIRED
    edt_surname.getText().toString(),     //Surname, REQUIRED
    phone,							      //Phone number, nullable
    img_uri, 						      //Image, nullable
    edt_email.getText().toString(),       //Email, REQUIRED
    edt_password.getText().toString(),    //Password REQUIRED
    auxiliar_data);					      //Auxiliar data nullable
```

This method won't return anything less than an error if the email is already taken or any other problem happened. To obtain the profile of the user and also call all the other API with this authorization, you will need to **login** this user.



### Authenticate an user

After a successfull registration you would probably need to authenticate your user by using the registration email and password, so you will need to call:

```java
private MBAuthApiAuthenticateListener listener;
private String email, password;

MBurgerAuthTasks.authenticateUser(getApplicationContext(), 
	listener, 			 //Listener for authentication
	email,               //Email of the user
	password);           //Password of the user
```

This method will return the `jwt_token` that <u>will automatically be used in all subsequential calls until app is uninstalled or user is logged out</u> in order to authenticate all API calls. It will be automatically stored encrypted within your application, but if you need to save it for your purposes you can obtain int through the listener or with the "action" mode inside the returned bundle with the key `MBApiPayloadKeys.key_jwt_token`.

If you wish to logout an user just call

```java
MBurgerAuthTasks.clearAuthToken(getApplicationContext());
```

and make sure that your app UI will respond to the "logout" in a significative way.



### Authenticate an user with social login

You can login your user with Facebook or Google login and make it an user in your app, this will be considered like a "registration" +  "login" way because social login will replace a normal registration, this means an **user can't login with social then register with the same mail linked to the social**.

To get started with social login please refer [this page](https://developers.facebook.com/docs/facebook-login/android) for Facebook authentication and [this page](https://developers.google.com/identity/sign-in/android/start) for Google authentication. Be aware that in both cases **you'll have to ask for email retrievement**.

When you have a user token then you'll have to call this API:

```java
private NKAuthApiAuthenticateListener listener;
private String token;

private int social_type = MBAuthAsyncTask_AuthenticateSocial.SOCIAL_FACEBOOK;
//or
private int social_type = MBAuthAsyncTask_AuthenticateSocial.SOCIAL_GOOGLE;

MBurgerAuthTasks.authenticateUserWithSocial(getApplicationContext(), 
	listener, 			 //Listener for authentication
	token,               //User social token
	social_type);        /*Social type, identifies which social are you using, can be
						 found in MBAuthAsyncTask_AuthenticateSocial as static value*/
```

Calling this API will result in immediate login, even if it's the first time the user ever logged in, so you will have the `jwt_token` that <u>will automatically be used in all subsequential calls until app is uninstalled or user is logged out</u>. Nothing changes for logging out, you should still need to call

```java
MBurgerAuthTasks.clearAuthToken(getApplicationContext());
```

and make sure that your app UI will respond to the "logout" in a significative way.



### User profile

When an user is authenticated you can see his data on the dashboard, you can also obtain the user profile calling the API

```java
private NKAuthApiProfileListener listener;

MBurgerAuthTasks.getLoggedUserProfile(getApplicationContext(), listener);
```

This API call will return the profile of the actual authenticated user in the form of a `MBAuthUser`.

**BE AWARE** that if you call this method before a login you will receive an error because there is no user authenticated, so make sure an user is authenticated before calling this API (you can easily achieve this by calling `MBCommonMethods.hasLoggedIn(context)`).



### Other features

##### Password recovery

You can add a "password lost" feature by calling the API:

```java
String email = edt_email.getText().toString();
MBurgerAuthTasks.requestPasswordRecovery(getApplicationContext(), 
                                             new NKAuthApiForgotPasswordListener() {
                    @Override
                    public void onForgotPasswordRequested() {
                        //TODO show an alert
                    }

                    @Override
                    public void onForgotPasswordRequestedError(String error) {
                        //TODO show an error dialog
                    }
                }, email);
```

This API will send an email to the user registered with the indicated `email`, if the email is not present, it will return an error.



##### Password editing

You can add a "edit password" feature in your app by calling the API:

```java
String old_password = edt_old_password.getText().toString();
String new_password = edt_new_password.getText().toString();

MBurgerAuthTasks.changePassword(getApplicationContext(), new NKAuthApiChangePasswordListener() {
                    @Override
                    public void onPasswordChanged() {
                    	//TODO show an alert
                    }

                    @Override
                    public void onPasswordChangedError(String error) {
					  //TODO show an error dialog
                    }
                }, old_password, new_password);
```

Be aware that **this API will work only with an user authenticated**.