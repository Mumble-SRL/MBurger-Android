<img src="https://mumbleideas.it/wp-content/uploads/2017/12/Mumble-anim-300.gif" alt="Mumble Logo" title="Mumble Logo">



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

