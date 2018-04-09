# Nooko3 Android Client

With the Nooko3 Android Client you can easily create a content-ful app without the need of a database or a backend. Before starting, make sure you read the Nooko3 guide on Nooko website in order to take confidence with Nooko namespaces and objects, also create a Project.



## Setup

First thing you should download or clone this repo, you will find a `nooko3` directory and `sample` directory. The first is the library itself, the latter is a sample app that demonstrates the easiness and flexibility of Nooko3.

Note that Nooko3 requires at least Android Studio 3.1 and your project should target Android Version 27, with minimum SDK version 17.

Import `nooko3` library inside your Android Studio project and include it adding to your `settings.gradle` file, then import into your app adding to your app module `build.gradle` file

```java
implementation project(':nooko3')
```



## Initialization

In order to use the SDK you should create an API Key, which will let you use Nooko3 APIs, you can do that on the dashboard for your Project and decide key permissions, at minimum it should be "read". API Keys can be used for Android apps as well as iOS apps, so you don't really need to create 2 keys for the two OS.
On your app before doing anything you must initialize the SDK with your API Key by using the `Nooko3` class

```java
Nooko3.initialize("<Your API Key>");
```

`Nooko3.initialize()` also may accept other parameters to control if you want Nooko3 SDK to cache your requests, you will find all information you need inside the [Javadoc](https://www.google.com "Javadoc"). Requesting anything from the SDK before initializing will result in an exception, so you shoud initialize on your `onCreate` starting Activity or on your custom `Application` class.



## Usage

Basically you can obtain and use all your project data using 3 classes with public static methods: `Nooko3Tasks`, `Nooko3Mapper` and `Nooko3ApiActionInitializer`. All these classes are used to obtain and map your custom objects from Nooko objects.



### Data model

Nooko classes represent basic informations you put from the Dashboard, at the most basic you will have to use **NKProject**, **NKBlocks**, **NKSections** and **NKClasses**.

**NkProject** represents the informations about your project, name, id and features.
A **NKBlock** represent a part of your Project, e.g. a list of News. They have a title, subtitle, order and an ArrayList of *NKSections* which represent the elements of the block.
**NKSections** are the single elements of the blocks, every section contains a Map of *Elements*, which are the parts you choose from the dashboard to create your items. The Map uses the name of the Element as a key and a *NKClass* as a value. You should prior know which NKClass is which Element if you wish to map Nooko object to your custom objects manually, or you can use the `Nooko3Mapper` to do this automatically for simple classes. **NKClasse** is a basic class which every Element extends, you will find all about the NKElements consulting the [Javadoc](https://www.google.com "Javadoc").

You can consult the [Javadoc](https://www.google.com "Javadoc") to know all the Nooko classes and methods if you will.



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

### Requesting Nooko data

#### Project

#### Blocks

#### Sections



## Proguard

If you are using proguard to shring and obfuscate your code, you must not objuscate the custom classes you want to map using the SDK or it will result in empty objects of exceptions. To achieve that you should add these lines to your proguard configuration file, replacing `your.package.classname` with your class full package name.

```java
-keep class your.package.classname
-keepclassmembers class your.package.classname { *; }
```



## Contact us

If you wish you can make a Pull request and feel free to open an Issue if you have difficulties using the SDK, you can also contact us at mumble@mumble.com



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

