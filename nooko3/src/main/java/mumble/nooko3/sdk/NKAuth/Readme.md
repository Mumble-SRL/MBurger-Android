<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">

## Nooko 3: Auth Usage

If your project is configured to register users you can use the "Auth" features of Nooko3.
You will need to check the `Nooko3AuthTasks`, where you will find all static methods to use authentication API, both with "action" and "listener" approach.



### Register a new user

If you wish to register a new user you will have to call the `Nooko3AuthTasks.registerUser(...)` method. 
Be aware that a Nooko user contains these fields, some required, some unrequired, some autofilled when registering:

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
private NKAuthApiRegisterListener listener;
private EditText edt_name, edt_surname, edt_email, edt_password;
private String phone;
private Uri img_uri;
private String auxiliar_data;

Nooko3AuthTasks.registerUser(getApplicationContext(),
	listener,                             //Listener for registration
    edt_name.getText().toString(),        //Name, REQUIRED
    edt_surname.getText().toString(),     //Surname, REQUIRED
    phone,							    //Phone number, nullable
    img_uri, 						    //Image, nullable
    edt_email.getText().toString(),       //Email, REQUIRED
    edt_password.getText().toString(),    //Password REQUIRED
    auxiliar_data);					    //Auxiliar data nullable
```

This method won't return anything less than an error if the email is already taken or any other problem happened. To obtain the profile of the user and also call all the other API with this authorization, you will need to **login** this user.



### Authenticate an user

After a successfull registration you would probably need to authenticate your user by using the registration email and password, so you will need to call:

```java
private NKAuthApiAuthenticateListener listener;
private String email, password;

Nooko3AuthTasks.authenticateUser(getApplicationContext(), 
	listener, 			//Listener for authentication
	email,               //Email of the user
	password);           //Password of the user
```

This method will return the `jwt_token` that <u>will automatically be used in all subsequential calls until app is uninstalled or user is logged out</u> in order to authenticate all API calls. It will be automatically stored encrypted within your application, but if you need to save it for your purposes you can obtain int through the listener or with the "action" mode inside the returned bundle with the key `NKApiPayloadKeys.key_jwt_token`.

If you wish to logout an user just call

```java
Nooko3AuthTasks.clearAuthToken(getApplicationContext());
```

and make sure that your app UI will respond to the "logout" in a significative way.



### User profile

When an user is authenticated you can see his data on the dashboard, you can also obtain the user profile calling the API

```java
private NKAuthApiProfileListener listener;

Nooko3AuthTasks.getLoggedUserProfile(getApplicationContext(), listener);
```

This API call will return the profile of the actual authenticated user in the form of a `NKAuthUser`.

**BE AWARE** that if you call this method before a login you will receive an error because there is no user authenticated, so make sure an user is authenticated before calling this API (you can easily achieve this by calling `NKCommonMethods.hasLoggedIn(context)`).



### Other features

##### Password recovery

You can add a "password lost" feature by calling the API:

```java
String email = edt_email.getText().toString();
Nooko3AuthTasks.requestPasswordRecovery(getApplicationContext(), 
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

Nooko3AuthTasks.changePassword(getApplicationContext(), new NKAuthApiChangePasswordListener() {
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