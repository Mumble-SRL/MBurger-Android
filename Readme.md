<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">

# MBurger Android SDK

With the MBurger Android SDK you can easily create a content-ful app without the need of a database or a backend. Remember that you would have to provide your own UI for your project, MBurger will not create any Activity or View, it would only provide data using a set of API which interfaces with MBurger backend.
Before starting, make sure you read the MBurger guide on MBurger website in order to take confidence with MBurger namespaces and objects, also create an account and a Project.



## Setup

First thing, you can get this sdk via **Maven** adding to your top `build.gradle` file this repository:

```
maven { 
	url "https://dl.bintray.com/mumbleideas/MBurger-Android/" 
}
```

Then add to your dependencies:

```
implementation 'mumble.mburger:android:1.5.2'
```

If you want you can also download or clone this repo, you will find a `MBurger` directory, which contains the client SDK to use MBurger. 

Note that MBurger requires at least Android Studio 3.3.2 and your project should target, at least, Android Version 28, with minimum SDK version 16 also your project would need these permissions:

```xml
//To gather data from MBurger API
<uses-permission android:name="android.permission.INTERNET" />

//To check internet connection and also provide error checking
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

You can also import `MBurger` library inside your Android Studio project and include it adding to your `settings.gradle` file, then import into your app adding to your app module `build.gradle` file

```java
implementation project(':mburger')
```

Pay attention that this SDK implements

```java
//In order to support some components on older Android versions
implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    
//For installing https certificates on older Android versions
implementation 'com.google.android.gms:play-services-base:16.1.0'
    
//For logging API error via Logcat
implementation 'com.github.omegasoft7.FSLogger:fslogger:1.9.1@aar'
    
//For Admin API support
implementation 'com.squareup.okhttp3:okhttp:3.14.0'
    
//For storing auth token
implementation 'com.scottyab:aescrypt:0.0.1'
```

The library is also fully working on a project developed on <u>Kotlin</u> but the SDK is fully written in <u>Java</u>.

**Warning**: From version `1.5` on, MBurger uses <u>Android X</u> dependencies, if your project does not target Android Sdk Version 28 or has still the "support.appcompat" dependecies use the `1.4` version, be aware that it is <u>not officially supported</u>.



## Initialization

In order to use the SDK you should create an API Key, which will let you use MBurger APIs, you can do that on the [dashboard](https://MBurger.mumbleserver.it/) settings for your Project and decide key permissions, at minimum it should be `"read"`.  If you wish to delete, update or create new sections you should add `"write"` and `"delete"` permissions.


![Dashboard image](/Images/api_token.png)

API Keys can be used for Android apps as well as iOS apps, so you don't really need to create 2 keys for the two OS.
On your app before doing anything you must initialize the SDK with your API Key by using the `MBurger` class, also you should specify if you are using the development api or the standard API, be aware that different mode means different key, so a "development api key" would not work if you are using standard api.

```java
MBurger.initialize("<Your API Key>", <Development mode>);
```

`MBurger.initialize()` also may accept other parameters to control if you want MBurger SDK to cache your requests, you will find all information you need inside the [Javadoc](https://github.com/Mumble-SRL/MBurger/tree/master/Javadoc "Javadoc"). Requesting anything from the SDK before initializing will result in an exception, so you shoud initialize on your `onCreate` starting Activity or on your custom `Application` class.



## Client usage

Basically you can obtain and use all your project data using 3 classes with public static methods: `MBurgerTasks`, `MBurgerMapper` and `MBurgerApiActionInitializer`. All these classes are used to obtain and map your custom objects from MBurger objects.



### Data model

MBurger classes represent basic informations you put from the Dashboard, at the most basic you will have to use **MBProject****, MBBlocks**, **MBSections** and **MBClasses**.

**MBProject** represents the informations about your project, name, id and features.
A **MBBlock** represent a part of your Project, e.g. a list of News. They have a title, subtitle, order and an ArrayList of *MBSections* which represent the elements of the block.
**MBSections** are the single elements of the blocks, every section contains a Map of *Elements*, which are the parts you choose from the dashboard to create your items. The Map uses the name of the Element as a key and a *MBClass* as a value. You should prior know which MBClass is which Element if you wish to map MBurger object to your custom objects manually, or you can use the `MBurgerMapper` to do this automatically for simple classes. **MBClass** is a basic class which every Element extends, you will find all about the MBElements consulting the [Javadoc](https://github.com/Mumble-SRL/MBurger/tree/master/Javadoc "Javadoc").

You can consult the [Javadoc](https://github.com/Mumble-SRL/MBurger/tree/master/Javadoc "Javadoc") to know all the MBurger classes and methods if you will.



### Requests and data retrivial

You should use `MBurgerTasks` static methods to retrieve data from MBurger dashboard, this class has public static fuctions which will call asynchronously MBurger API. You have 2 ways to retrieve data from API, using a **Listener** or using **Actions**.

The **Listener** approach is the easiest one, with every method of the `MBurgerTasks` class you should pass the corresponding listener to return data from the fuction to your control. All the listeners are inside the `MBApiResultsListeners` and all of them are simple interfaces that will return the MBurger object you asked or or an error message. 
For example, you should use a `MBApiProjectResultListener` (implementing it to your Activity or Fragment or creating a new one runtime) to obtain Project basic data, so the class will implement these methods:

```java
@Override
public void onProjectApiResult(NKProject project) {
    /*API call went well*/
}

@Override
public void onProjectApiError(String error) {
    /*There was an error, you should show it*/
}
```

The **Action** approach is more complicated but more flexible, it uses `LocalBroadcastMessages` to send data from the API to your Activity/Fragment using an `Action` which your Activity/Fragment is listening to, you can use this method also if you are doing your own API calls to your server.
First you must implement `MBGenericApiResultListener` which will implement

```java
@Override
public void onApiResult(NKAPIResponse response) {        
}
```

The MBAPIResponse is an object wich contains a boolean representing if the API call went well (if not a String error will be valorized), the apiAction from which it came and a Bundle that will contain the payload of the API. You can obtain the payload using the keys inside the class `MBApiPayloadKeys`.
Then you should add to your `onResume` and `onPause` methods one of the receivers of `MBurgerApiActionInitializer` class in order to let your Activity/Fragment to receive the action associated with the async task you need and to release the receiver for the action in onPause. You can also use a custom Initializer, already included in the task, to listen to your custom actions if you will.
For example if you need Project data your Activity code should result

```java
//You need to mantain the BroadcastReceiver associated with your Activity
private BroadcastReceiver bRec;

@Override
protected void onResume() {
    super.onResume();
    bRec = MBurgerApiActionInitializer.initializeMBurgerReceiverForProject(this, this);
    //Call api from this moment on
}

@Override
protected void onPause() {
	super.onPause();
	MBurgerApiActionInitializer.pauseMBurgerReceiver(this, bRec);
}

@Override
public void onApiResult(NKAPIResponse response) {
	if(response.getResult()) {
		NKProject project = (NKProject) response.getPayload()
            .get(NKApiPayloadKeys.key_project);
            //Your code
	}
    else{
    	//Show error message 
    }
}

```

This method implies that you should not use an AsyncTask before `onResume` method occured.
Pay attention that if you are using the "action" mode you should check for the action that triggered `onApiResult`, you will find all standard actions inside the class `MBAPIConstants`



### Filtering MBurger data requests

You can filter, sort and request for particular data using a set of premade filters adding them to the MBurger calls. It's applied to the sections, so, if you want to only have a list of POI on a particular place you can add a filter to have a geofenced array of sections. FIlters are

- `MBFilterParameter`
- `MBGeofenceParameter`
- `MBPaginationParameter`
- `MBSortParameter`

If you want to pass another type of parameter you can use the `MBGeneralParameter` class that can be initialized with a key and a value that will be passed to the apis.
You can add whatever number you wish, but remember that for same type of filters <u>it will be considered only the last one of the array.</u>

### Requesting MBurger data examples

#### Project

**Listener** approach

```java
MBurgerTasks.askForProject(this, new NKApiProjectResultListener() {
	@Override
	public void onProjectApiResult(NKProject project) {
    	//Your code
	}

	@Override
	public void onProjectApiError(String error) {
		//Show error message
	}
});
```

**Action** approach

```java
@Override
protected void onResume() {
	super.onResume();
    bRec = MBurgerApiActionInitializer.initializeMBurgerReceiverForProject(this, this);
    MBurgerTasks.askForProject(this);
}

@Override
protected void onPause() {
	super.onPause();
    MBurgerApiActionInitializer.pauseMBurgerReceiver(this, bRec);
}

@Override
public void onApiResult(NKAPIResponse response) {
	if (response.getResult()) {
    	MBProject project = (MBProject) response.getPayload().get(MBApiPayloadKeys.key_project);
        	//Your code
     } else {
     	//Show error message
	}
}
```



#### Blocks

**Listener** approach

```java
//Add custom filters to the API call or leave it null
ArrayList<Object> arrayOfFilters = null;

//Inside the blocks returned the "sections" field will be valorized, if false it would be null
boolean getSections = true;

//Inside the sections the "elements" field will not be valorized (null)
boolean getElements = false;

MBurgerTasks.askForBlocks(this, arrayOfFilters, getSections, getElements, 
                         new MBApiBlocksResultListener() {
	@Override
    public void onBlocksApiResult(ArrayList<NKBlock> blocks, MBPaginationInfo paginationInfos) {
    	//Your code
	}

    @Override
    public void onBlocksApiError(String error) {
		//Show error message
    }
});
```

**Action** approach

```java
@Override
protected void onResume() {
	super.onResume();
    bRec = MBurgerApiActionInitializer.initializeMBurgerReceiverForBlocks(this, this);
	
    //Add custom filters to the API call or leave it null
	ArrayList<Object> arrayOfFilters = null;

	//Inside the blocks returned, the "sections" field will be valorized, if false it would be        null
	boolean getSections = true;

	//Inside the sections the "elements" field will not be valorized (null)
	boolean getElements = false;
    
	MBurgerTasks.askForBlocks(this, arrayOfFilters, getSections, getElements);
}

@Override
protected void onPause() {
	super.onPause();
    MBurgerApiActionInitializer.pauseMBurgerReceiver(this, bRec);
}

@Override
public void onApiResult(MBAPIResponse response) {
	if (response.getResult()) {
    	ArrayList<MBBlock> blocks = (ArrayList<MBBlock>) response.getPayload().get(MBApiPayloadKeys.key_blocks);
        //Your code
	} else {
    	//Show error message
	}
}
```



#### Sections

**Listener** approach

```java
//Add custom filters to the API call or leave it null
ArrayList<Object> arrayOfFilters = null;

//Id of the block you wish to retrieve sections
long block_id = 10;

//Inside the sections the "elements" field will be valorized
boolean getElements = true;
MBurgerTasks.askForSections(this, block_id, arrayOfFilters, getElements, 
                           new MBApiSectionsResultListener() {
	@Override
    public void onSectionsApiResult(ArrayList<MBSection> sections, long block_id, 
                                    MBPaginationInfo paginationInfos) {
    	//Your code 
    }

    @Override
    public void onSectionsApiError(String error) {
		//Show error message
    }
});

```

**Action** approach

```java
@Override
protected void onResume() {
	super.onResume();
	bRec = MBurgerApiActionInitializer.initializeMBurgerReceiverForSections(this, this);
    
    //Add custom filters to the API call or leave it null
	ArrayList<Object> arrayOfFilters = null;

	//Id of the block you wish to retrieve sections
	long block_id = 10;
    
	//Inside the sections the "elements" field will be valorized
	boolean getElements = true;
	MBurgerTasks.askForSections(this, block_id, arrayOfFilters, getElements);
}

@Override
protected void onPause() {
	super.onPause();
    MBurgerApiActionInitializer.pauseMBurgerReceiver(this, bRec);
}

@Override
public void onApiResult(NKAPIResponse response) {
	if (response.getResult()) {
    	ArrayList<MBSection> sections = (ArrayList<MBSection>) response.getPayload().get(MBApiPayloadKeys.key_sections);
        //Your code
    } else {
    	//Show error message
	}
}

```



#### Custom actions example

```java
@Override
    protected void onResume() {
        super.onResume();
        String[] receivers = {MBAPIConstants.ACTION_GET_PROJECT, MBAPIConstants.ACTION_GET_BLOCKS};
        bRec = MBurgerApiActionInitializer.initializeMBurgerReceiverCustom(this, this, receivers);
        MBurgerTasks.askForProject(getApplicationContext());
        MBurgerTasks.askForBlocks(getApplicationContext(), null, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MBurgerApiActionInitializer.pauseMBurgerReceiver(this, bRec);
    }

@Override
    public void onApiResult(NKAPIResponse response) {
        if (response.getApiAction().equals(MBAPIConstants.ACTION_GET_PROJECT)) {
            if (response.getResult()) {
                MBProject project = 
                    (MBProject) response.getPayload().get(MBApiPayloadKeys.key_project);
            }
        }

        if (response.getApiAction().equals(MBAPIConstants.ACTION_GET_BLOCKS)) {
            if (response.getResult()) {
                ArrayList<MBBlock> blocks = 
                    (ArrayList<MBBlock>) response.getPayload().get(MBApiPayloadKeys.key_blocks);
            }
        }
    }
```



### Live messages & Poll

If your project has been set up for using these two features please note that there are special rules to use them:

- **Live Messages**: the method to send live messages can only send text messages with a name and a content, no results are provided apart from the standard "OK"/"Not OK"
- **Poll**: You should use MBurger objects if you use poll feature, the dashboard will take care to take only one answer per user (using device_id or user token if authenticated) but it's a best practice to control in-app if your user has already voted checking the `MBPollAnswers` object, which contains all the answers and also your personal answer. You should absolutely check if the poll is still valid checking the "available_at" for the section object.



### Mapping

You can map your custom objects starting from `MBSection` automatically using `MBurgerMapper` class.
Using the commodity class `MBFieldsMapping` which fields of your custom class should be mapped with the fields of the MBSection you named on your Project dashboard, your destination object **should at least override getters and setters** and if you wish to obtain simple values or MBurger object values for:

- Images -> First MBImage (only an object, not an array)
- Media & Documents -> First MBFile (only an object, not an array)
- Addresses -> latitude, longitude or textual address

You will find all possible simple data inside the `MBMappingArgs` class.
Make sure your custom class is public and that provides getters and setters for all fields you wish to map.

For example, for a simple "News" class with title and MBurger image

```java
public class News implements Serializable {

    private String titl;
    private NKImage img;

    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public NKImage getImg() {
        return img;
    }

    public void setImg(NKImage img) {
        this.img = img;
    }
}
```

Can be mapped this way:

```java
NKFieldsMapping fieldsMapping = new NKFieldsMapping();

//"titl" is my custom object element, "Title" is the name of the Element we want to map from the section
fieldsMapping.putMap("titl", "Title");

//"img" is my custom object element, "Images" is the name of the Element we want to map from the section, we add "imageArguments" which tells to take only the first image from the array.
String[] imageArguments = {NKMappingArgs.mapping_first_image_media};
fieldsMapping.putMap("img", "Images", imageArguments);

//If getSimpleValues was "true" we would not have a single NKImage but the basic value, so the URL of the image and News class "img" should be a String
boolean getSimpleValues = false;
News n = (News) MBurgerMapper.mapToCustomObject(nkSection, fieldsMapping, new News(), getSimpleValues);
```

Pay attention that if you need to map images the SDK will return a `MBImages` object, which contains an array of `MBImage`, if you want an array of URLs you will have to set `getSimpleValues` to `true`.

> ```
> DISCLAIMER:
> Due to the nature of Android 9.0 Pie this functionaity could give you light greylist logcat messages because it uses reflections. For now on, there should be no problem, but this functionality may change when Android Pie is fully released or could be not available for Pie application users.
> ```



## Admin, Auth, Pay and Push

For admin (create/delete/edit sections) and auth (login user/register, profile) please check out **Admin usage** [here](https://github.com/Mumble-SRL/MBurger/tree/develop/mburger/src/main/java/mumble/mburger/sdk/MBAdmin) and for **Auth usage** [here](https://github.com/Mumble-SRL/MBurger/tree/develop/mburger/src/main/java/mumble/mburger/sdk/MBAuth). For using the **Pay** functionalities, please check out [this documentation](https://github.com/Mumble-SRL/MBurger/tree/develop/mburger/src/main/java/mumble/mburger/sdk/MBPay), for **Push notifications** functionality look it up [here](https://github.com/Mumble-SRL/MBurger/tree/develop/mburger/src/main/java/mumble/mburger/sdk/MBPush).



## Proguard

If you are using proguard to shring and obfuscate your code, you must not objuscate the custom classes you want to map using the SDK or it will result in empty objects of exceptions. To achieve that you should add these lines to your proguard configuration file, replacing `your.package.classname` with your class full package name.

```java
-keep class your.package.classname
-keepclassmembers class your.package.classname { *; }
```

Also you may need to add these lines to your proguard file:

```java
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
```



## Contact us

If you wish you can make a Pull request and feel free to open an Issue if you have problems using the SDK, you can also contact us at [info@mumbleideas.it](info@mumbleideas.it).



## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

