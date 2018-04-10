<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">

# Nooko Android SDK

With the Nooko3 Android SDK you can easily create a content-ful app without the need of a database or a backend. Before starting, make sure you read the Nooko3 guide on Nooko website in order to take confidence with Nooko namespaces and objects, also create a Project.



## Setup

First thing you should download or clone this repo, you will find a `nooko3` directory and `sample` directory. 

Note that Nooko3 requires at least Android Studio 3.1 and your project should target Android Version 27, with minimum SDK version 17, also your project will have these permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

Import `nooko3` library inside your Android Studio project and include it adding to your `settings.gradle` file, then import into your app adding to your app module `build.gradle` file

```java
implementation project(':nooko3')
```

Pay attentiom that this SDK implements

```java
//For support of oldest Android versions
implementation 'com.android.support:support-v4:27.1.0'
    
//For installing https certificates on older Android versions
implementation 'com.google.android.gms:play-services-base:12.0.1'
    
//For logging API error via Logcat
implementation 'com.github.omegasoft7.FSLogger:fslogger:1.9.1@aar'
```

And also a jar containing Apache Commons methods:

```java
implementation files('libs/android-java-air-bridge.jar')
```



## Initialization

In order to use the SDK you should create an API Key, which will let you use Nooko3 APIs, you can do that on the [dashboard](https://nooko3.mumbleserver.it/) settings for your Project and decide key permissions, at minimum it should be "read". 


![Dashboard image](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/master/Images/api_token.png)

API Keys can be used for Android apps as well as iOS apps, so you don't really need to create 2 keys for the two OS.
On your app before doing anything you must initialize the SDK with your API Key by using the `Nooko3` class

```java
Nooko3.initialize("<Your API Key>");
```

`Nooko3.initialize()` also may accept other parameters to control if you want Nooko3 SDK to cache your requests, you will find all information you need inside the [Javadoc](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/tree/master/Javadoc "Javadoc"). Requesting anything from the SDK before initializing will result in an exception, so you shoud initialize on your `onCreate` starting Activity or on your custom `Application` class.



## Usage

Basically you can obtain and use all your project data using 3 classes with public static methods: `Nooko3Tasks`, `Nooko3Mapper` and `Nooko3ApiActionInitializer`. All these classes are used to obtain and map your custom objects from Nooko objects.



### Data model

Nooko classes represent basic informations you put from the Dashboard, at the most basic you will have to use **NKProject**, **NKBlocks**, **NKSections** and **NKClasses**.

**NkProject** represents the informations about your project, name, id and features.
A **NKBlock** represent a part of your Project, e.g. a list of News. They have a title, subtitle, order and an ArrayList of *NKSections* which represent the elements of the block.
**NKSections** are the single elements of the blocks, every section contains a Map of *Elements*, which are the parts you choose from the dashboard to create your items. The Map uses the name of the Element as a key and a *NKClass* as a value. You should prior know which NKClass is which Element if you wish to map Nooko object to your custom objects manually, or you can use the `Nooko3Mapper` to do this automatically for simple classes. **NKClass** is a basic class which every Element extends, you will find all about the NKElements consulting the [Javadoc](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/tree/master/Javadoc "Javadoc").

You can consult the [Javadoc](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/tree/master/Javadoc "Javadoc") to know all the Nooko classes and methods if you will.



### Requests and data retrivial

You should use `Nooko3Tasks` static methods to retrieve data from Nooko3 dashboard, this class has public static fuctions which will call asynchronously Nooko3 API. You have 2 ways to retrieve data from API, using a **Listener** or using **Actions**.

The **Listener** approach is the easiest one, with every method of the `Nooko3Tasks` class you should pass the corresponding listener to return data from the fuction to your control. All the listeners are inside the `NKApiResultsListeners` and all of them are simple interfaces that will return the Nooko object you asked or or an error message. 
For example, you should use a `NKApiProjectResultListener` (implementing it to your Activity or Fragment or creating a new one runtime) to obtain Project basic data, so the class will implement these methods:

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
First you must implement `NKGenericApiResultListener` which will implement

```java
@Override
public void onApiResult(NKAPIResponse response) {        
}
```

The NKAPIResponse is an object wich contains a boolean representing if the API call went well (if not a String error will be valorized), the apiAction from which it came and a Bundle that will contain the payload of the API. You can obtain the payload using the keys inside the class `NKApiPayloadKeys`.
Then you should add to your `onResume` and `onPause` methods one of the receivers of `Nooko3ApiActionInitializer` class in order to let your Activity/Fragment to receive the action associated with the async task you need and to release the receiver for the action in onPause. You can also use a custom Initializer, already included in the task, to listen to your custom actions if you will.
For example if you need Project data your Activity code should result

```java
//You need to mantain the BroadcastReceiver associated with your Activity
private BroadcastReceiver bRec;

@Override
protected void onResume() {
    super.onResume();
    bRec = Nooko3ApiActionInitializer.initializeNookoReceiverForProject(this, this);
    //Call api from this moment on
}

@Override
protected void onPause() {
	super.onPause();
	Nooko3ApiActionInitializer.pauseNookoReceiver(this, bRec);
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



### Requesting Nooko data examples

#### Project

**Listener** approach

```java
Nooko3Tasks.askForProject(this, new NKApiProjectResultListener() {
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
    bRec = Nooko3ApiActionInitializer.initializeNookoReceiverForProject(this, this);
    Nooko3Tasks.askForProject(this);
}

@Override
protected void onPause() {
	super.onPause();
    Nooko3ApiActionInitializer.pauseNookoReceiver(this, bRec);
}

@Override
public void onApiResult(NKAPIResponse response) {
	if (response.getResult()) {
    	NKProject project = (NKProject) response.getPayload().get(NKApiPayloadKeys.key_project);
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

//Inside the blocks returned the "sections" field will be valorized
boolean getSections = true;

//Inside the sections the "elements" field will not be valorized (null)
boolean getElements = false;

Nooko3Tasks.askForBlocks(this, arrayOfFilters, getSections, getElements, 
                         new NKApiBlocksResultListener() {
	@Override
    public void onBlocksApiResult(ArrayList<NKBlock> blocks, NKPaginationInfo paginationInfos) {
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
    bRec = Nooko3ApiActionInitializer.initializeNookoReceiverForBlocks(this, this);
	
    //Add custom filters to the API call or leave it null
	ArrayList<Object> arrayOfFilters = null;

	//Inside the blocks returned the "sections" field will be valorized
	boolean getSections = true;

	//Inside the sections the "elements" field will not be valorized (null)
	boolean getElements = false;
    
	Nooko3Tasks.askForBlocks(this, arrayOfFilters, getSections, getElements);
}

@Override
protected void onPause() {
	super.onPause();
    Nooko3ApiActionInitializer.pauseNookoReceiver(this, bRec);
}

@Override
public void onApiResult(NKAPIResponse response) {
	if (response.getResult()) {
    	ArrayList<NKBlock> blocks = (ArrayList<NKBlock>) response.getPayload().get(NKApiPayloadKeys.key_blocks);
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
Nooko3Tasks.askForSections(this, block_id, arrayOfFilters, getElements, 
                           new NKApiSectionsResultListener() {
	@Override
    public void onSectionsApiResult(ArrayList<NKSection> sections, long block_id, 
                                    NKPaginationInfo paginationInfos) {
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
	bRec = Nooko3ApiActionInitializer.initializeNookoReceiverForSections(this, this);
    
    //Add custom filters to the API call or leave it null
	ArrayList<Object> arrayOfFilters = null;

	//Id of the block you wish to retrieve sections
	long block_id = 10;
    
	//Inside the sections the "elements" field will be valorized
	boolean getElements = true;
	Nooko3Tasks.askForSections(this, block_id, arrayOfFilters, getElements);
}

@Override
protected void onPause() {
	super.onPause();
    Nooko3ApiActionInitializer.pauseNookoReceiver(this, bRec);
}

@Override
public void onApiResult(NKAPIResponse response) {
	if (response.getResult()) {
    	ArrayList<NKSection> sections = (ArrayList<NKSection>) response.getPayload().get(NKApiPayloadKeys.key_sections);
        //Your code
    } else {
    	//Show error message
	}
}

```



### Mapping

You can map your custom objects starting from `NKSection` automatically using `Nooko3Mapper` class.
You should provide, using the commodity class `NKFieldsMapping` which fields of your custom class should be mapped with the fields of the `NKSection` you named on your Project dashboard. Your destination object should be can be an empty constructor and if you wish to obtain simple values or Nooko object values for:

- Images -> First NKImage
- Media & Documents -> First NKFile
- Addresses -> latitude, longitude or textual address

You will find all possible simple data inside the `NKMappingArgs` class.
Make sure your custom class is public and that provides getters and setters for all fields you wish to map.

For example, for a simple "News" class with title and Nooko image

```java
public class News implements Serializable {

    private String tit;
    private NKImage img;

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
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

//"tit" is my custom object element, "Title" is the name of the Element we want to map from the section
fieldsMapping.putMap("tit", "Title");

//"img" is my custom object element, "Images" is the name of the Element we want to map from the section, we add "imageArguments" which tells to take only the first image from the array.
String[] imageArguments = {NKMappingArgs.mapping_first_image_media};
fieldsMapping.putMap("img", "Images", imageArguments);

//If getSimpleValues was "true" we would not have a single NKImage but the basic value, so the URL of the image and News class "img" should be a String
boolean getSimpleValues = false;
News n = (News) Nooko3Mapper.mapToCustomObject(nkSection, fieldsMapping, new News(), getSimpleValues);
```



## Proguard

If you are using proguard to shring and obfuscate your code, you must not objuscate the custom classes you want to map using the SDK or it will result in empty objects of exceptions. To achieve that you should add these lines to your proguard configuration file, replacing `your.package.classname` with your class full package name.

```java
-keep class your.package.classname
-keepclassmembers class your.package.classname { *; }
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

