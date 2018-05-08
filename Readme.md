<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">

# Nooko Android SDK 0.5 beta

With the Nooko3 Android SDK you can easily create a content-ful app without the need of a database or a backend. Remember that you would have to provide your own UI for your project, Nooko will not create any Activity or View, it would only provide data using a set of API which interfaces with Nooko backend.
Before starting, make sure you read the Nooko3 guide on Nooko website in order to take confidence with Nooko namespaces and objects, also create an account and a Project.



## Setup

First thing, you should download or clone this repo, you will find a `nooko3` directory, which contains the client SDK to use Nooko. 

Note that Nooko3 requires at least Android Studio 3.1 and your project should target Android Version 27, with minimum SDK version 17, also your project would need these permissions:

```xml
//To gather data from Nooko API
<uses-permission android:name="android.permission.INTERNET" />

//To check internet connection and also provide error checking
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

Import `nooko3` library inside your Android Studio project and include it adding to your `settings.gradle` file, then import into your app adding to your app module `build.gradle` file

```java
implementation project(':nooko3')
```

Pay attention that this SDK implements

```java
//In order to suppor some components on older Android versions
implementation 'com.android.support:support-v4:27.1.1'
    
//For installing https certificates on older Android versions
implementation 'com.google.android.gms:play-services-base:15.0.1'
    
//For logging API error via Logcat
implementation 'com.github.omegasoft7.FSLogger:fslogger:1.9.1@aar'
```

And also a jar containing Apache Commons methods:

```java
implementation files('libs/android-java-air-bridge.jar')
```



## Initialization

In order to use the SDK you should create an API Key, which will let you use Nooko3 APIs, you can do that on the [dashboard](https://nooko3.mumbleserver.it/) settings for your Project and decide key permissions, at minimum it should be `"read"`.  If you wish to delete, update or create new sections you should add `"write"` and `"delete"` permissions.


![Dashboard image](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/raw/master/Images/api_token.png)

API Keys can be used for Android apps as well as iOS apps, so you don't really need to create 2 keys for the two OS.
On your app before doing anything you must initialize the SDK with your API Key by using the `Nooko3` class

```java
Nooko3.initialize("<Your API Key>");
```

`Nooko3.initialize()` also may accept other parameters to control if you want Nooko3 SDK to cache your requests, you will find all information you need inside the [Javadoc](https://gitlab.mumbleserver.it/Enri/Nooko3_LIB/tree/master/Javadoc "Javadoc"). Requesting anything from the SDK before initializing will result in an exception, so you shoud initialize on your `onCreate` starting Activity or on your custom `Application` class.



## Client usage

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
Pay attention that if you are using the "action" mode you should check for the action that triggered `onApiResult`, you will find all standard actions inside the class `NKAPIConstants`



### Filtering Nooko data requests

You can filter, sort and request for particular data using a set of premade filters adding them to the Nooko calls. It's applied to the sections, so, if you want to only have a list of POI on a particular place you can add a filter to have a geofenced array of sections. FIlters are

- `NKFilterParameter`
- `NKGeofenceParameter`
- `NKPaginationParameter`
- `NKSortParameter`

If you want to pass another type of parameter you can use the `NKGeneralParameter` class that can be initialized with a key and a value that will be passed to the apis.
You can add whatever number you wish, but remember that for same type of filters <u>it will be considered only the last one of the array.</u>

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

//Inside the blocks returned the "sections" field will be valorized, if false it would be null
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

	//Inside the blocks returned, the "sections" field will be valorized, if false it would be        null
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



#### Custom actions example

```java
@Override
    protected void onResume() {
        super.onResume();
        String[] receivers = {NKAPIConstants.ACTION_GET_PROJECT, NKAPIConstants.ACTION_GET_BLOCKS};
        bRec = Nooko3ApiActionInitializer.initializeNookoReceiverCustom(this, this, receivers);
        Nooko3Tasks.askForProject(getApplicationContext());
        Nooko3Tasks.askForBlocks(getApplicationContext(), null, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Nooko3ApiActionInitializer.pauseNookoReceiver(this, bRec);
    }

@Override
    public void onApiResult(NKAPIResponse response) {
        if (response.getApiAction().equals(NKAPIConstants.ACTION_GET_PROJECT)) {
            if (response.getResult()) {
                NKProject project = 
                    (NKProject) response.getPayload().get(NKApiPayloadKeys.key_project);
            }
        }

        if (response.getApiAction().equals(NKAPIConstants.ACTION_GET_BLOCKS)) {
            if (response.getResult()) {
                ArrayList<NKBlock> blocks = 
                    (ArrayList<NKBlock>) response.getPayload().get(NKApiPayloadKeys.key_blocks);
            }
        }
    }
```



### Live messages & Poll

If your project has been set up for using these two features please note that there are special rules to use them:

- **Live Messages**: the method to send live messages can only send text messages with a name and a content, no results are provided apart from the classic "OK"/"Not OK"
- **Poll**: You should use Nooko objects if you use poll feature, the dashboard will take care to take only one answer per user (using device_id or user_id if authenticated) but it's a best practice to control in-app if your user has already voted checking the `NKPollAnswers` object, which contains all the answers and also your personal answer. You should absolutely check if the poll is still valid checking the "available_at" for the section object.



### Mapping

You can map your custom objects starting from `NKSection` automatically using `Nooko3Mapper` class.
Using the commodity class `NKFieldsMapping` which fields of your custom class should be mapped with the fields of the NKSection you named on your Project dashboard, your destination object **should at least override getters and setters** and if you wish to obtain simple values or Nooko object values for:

- Images -> First NKImage (only an object, not an array)
- Media & Documents -> First NKFile (only an object, not an array)
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

Pay attention that if you need to map images the SDK will return a `NKImages` object, which contains an array of `NKImage`, if you want an array of URLs you will have to set `getSimpleValues` to `true`. 



## Admin Usage

If you are also using editing and creating features ("ADMIN" features) within your app, you would also need to check `Nooko3AdminTasks`. Please note that all the concepts from the "client" usage are still valid, so all `Nooko3AdminTasks` methods have the "action" and the "listener" approach.



### Delete a Section/Media

In order to <u>delete a specific section</u> you will need to obtain the `id` from the `NKSection` objects you will retrieve through APIs. Then you will just have to call the static method

```java
Nooko3AdminTasks.deleteSection(this, section_id);
```

which will return back the section_id, with the listener `NKAdminApiDeleteSectionListener` and from the callback bundle in "action" mode using the `NKApiPayloadKeys.key_section_id`.

<u>Deleting a media</u> from a section is pretty similar:

```java
Nooko3AdminTasks.deleteMedia(this, media_id);
```

where `media_id` is the id of the media element you wish to delete, it can be used with both files and images.
This method will return back the media_id, with the listener `NKAdminApiDeleteMediaListener`, and from the callback bundle in "action" mode using the `NKApiPayloadKeys.key_media_id`.
It's suggested to mantain the `NKObjects` (NKImage for example) instead of simple values in order to mantain the `media_id` in a simpler way.



### Create a new Section

Before creating a new section please take not of the names of the elements the section is made of and the id of the block you need to add a new section.
Then you will need to use the static method

```java
Nooko3AdminTasks.addSection(getApplicationContext(), block_id, params, params_file, Locale.getDefault().getLanguage());
```

Where **params** is an ArrayList of `NKAdminParameter` and **params_file** is an ArrayList of `NKAdminParameterFile` . You will also need to provide the `Locale` (in "it" like form) for multilanguage sake support, please note that if the locale you'll provide is not supported the API will return an error.
**NKAdminParameter** is a key-value object which will need the name of the element and the value you wish to give it. The value must be a string.
**NKAdminParameterFile** also is a key-value object but the value is an **arraylist of `NKAdminSingleFile`** which represent a file to be uploaded. All fields of `NKAdminSingleFile` must be valorized and <u>the order of the files is given by the order you put the objects inside the ArrayList</u>.
Both these arraylists can be null, it's all about how you configured your section in the dashboard, you can see the process of adding the values as a mirror of ehat you can do on the dashboard.
<u>Please note that for the address field you should just give the textual address</u>, Nooko3 API will retrieve automatically latitude and longitude of the address.

For example, if your section is composed like:

- **"title"** -> text field
- **"content"** -> text field
- **"link"** -> text field
- **"image"** -> array of media (images)

So your code for adding a new section (assuming the supported locale is the same as your device) will result like this:

```java
//EditTexts for inserting content
private EditText edt_title, edt_content, edt_link;

private NKAdminApiAddSectionListener listener;

private block_id = //BLOCK_ID
    
//URI of the image, taken from camera or gallery
private Uri img_uri;

public void addSection() {
	ArrayList<NKAdminParameter> params = new ArrayList<>();
	params.add(new NKAdminParameter("title", edt_title.getText().toString()));
	params.add(new NKAdminParameter("content", edt_content.getText().toString()));
	params.add(new NKAdminParameter("link", edt_link.getText().toString()));

	ArrayList<NKAdminParameterFile> params_file = new ArrayList<>();
	ArrayList<NKAdminSingleFile> files = new ArrayList<>();
	files.add(new NKAdminSingleFile(getFileName(), 
                                    getMimeType(), 
                                    getUriRealPath(getApplication(), img_uri)));
	params_file.add(new NKAdminParameterFile("image", files));

	Nooko3AdminTasks.addSection(getApplicationContext(), 
                                block_id, 
                                listener, 
                                params, 
                                params_file, 
                                Locale.getDefault().getLanguage());
}
```

Where `getFileName()`, `getMimeType()`, `getUriRealPath(getApplication(), img_uri)` are methods to obtain the image name, image mime type and the full file path from the image Uri.



### Update an existing Section

Updating a section is a process very similar to creating a new one, but you will need the id of the section ou wish to update, the block_id is not necessary.
You should only pay attention that you must provide a valid locale and <u>only the section with the given locale will be updated</u>, other locale variants will remain untouched. Also a<u>ll media you will send through the API will be appended to the pre-existent array of media</u>. If you wish to replace and image or a media you will need to call the `Nooko3AdminTasks.deleteMedia(this, media_id)` method before calling the update.

If you wish to update a section, the method is similar to the create one:

```java
//EditTexts for inserting content
private EditText edt_title, edt_content, edt_link;

private NKAdminApiUpdateSectionListener listener;

private section_id = //ID of the section you wish to update
    
//URI of the image, taken from camera or gallery
private Uri img_uri;

public void updateSection() {
	ArrayList<NKAdminParameter> params = new ArrayList<>();
	params.add(new NKAdminParameter("title", edt_title.getText().toString()));
	params.add(new NKAdminParameter("content", edt_content.getText().toString()));
	params.add(new NKAdminParameter("link", edt_link.getText().toString()));

	ArrayList<NKAdminParameterFile> params_file = new ArrayList<>();
	ArrayList<NKAdminSingleFile> files = new ArrayList<>();
	files.add(new NKAdminSingleFile(getFileName(), 
                                getMimeType(), 
                                getUriRealPath(getApplication(), img_uri)));
	params_file.add(new NKAdminParameterFile("image", files));

	Nooko3AdminTasks.updateSection(getApplicationContext(), 
                            section_id, 
                            listener, 
                            params, 
                            params_file, 
                            Locale.getDefault().getLanguage());
}
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

