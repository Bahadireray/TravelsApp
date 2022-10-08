# Bahadir-Eray-BootcampFinishProject
## Libraries and technologies used
* Navigation component : one activity contains multiple fragments instead of creating multiple activites.
* Retrofit : HTTP connection with the rest API and convert Travels json file to Kotlin/Java object.
* Room : Save Favori in local database.
* MVVM & LiveData : Saperate logic code from views and save the state in case the screen configuration changes.
* Coroutines : do some code in the background.
* view binding : instead of inflating views manually view binding will take care of that.
* Glide : Catch images and load them in imageView.



---

The file structure is set up like this

```
.
├── App.kt
├── MainActivity.kt
├── data
│    ├── Result.kt
│    ├── Result.kt
│    ├── network network
│    │   └── <feature>api
│    │       ├── <Feature>Service.kt
│    │       └── response
│    │           └── <Each>Response.kt
│    └── repository
│        └── RepositoryImpl.kt
├── di
│    ├── Module.kt
│    └── qualifiers
│        └── <Each>.kt
├── domain
│    ├── <Feature>UseCase.kt
│    └── entities
│        └── Entity<Each>.kt
├── ui
│    ├── BindingAdapters.kt
│    └── <feature>
│        ├── <Feature>Fragment.kt
│        ├── <Feature>RecyclerviewAdapter.kt
│        ├── <Feature>ViewModel.kt
│        └── adapter
│            └── ViewHolder.kt
└── util
    └── Extensions.kt
```

### İmplementation Library

```
def lifeCycleExtensionVersion = '1.1.1'
    def supportVersion = '28.0.0'
    def retrofitVersion = '2.9.0'
    def glideVersion = '4.13.2'
    def rxJavaVersion = '2.1.1'
    def room_version = "2.3.0"
    
    dependencies {
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")

    //viewmodel
    implementation "android.arch.lifecycle:extensions:$lifeCycleExtensionVersion"

    //Coroutine
    implementation "androidx.legacy:legacy-support-v4:1.0.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"

    //nav
    implementation 'androidx.navigation:navigation-fragment-ktx:2.5.2'
    implementation 'androidx.navigation:navigation-ui-ktx:2.5.2'

    //Design
    implementation "com.google.android.material:material:1.6.1"
    implementation "com.android.support:palette-v7:$supportVersion"
    implementation "com.android.support:design:$supportVersion"

    //Retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"

    //RxJava
    implementation "io.reactivex.rxjava2:rxjava:$rxJavaVersion"
    implementation "io.reactivex.rxjava2:rxandroid:$rxJavaVersion"
    implementation "androidx.room:room-rxjava3:$room_version"
    implementation "io.reactivex.rxjava3:rxandroid:3.0.0"
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    //Glide
    implementation "com.github.bumptech.glide:glide:$glideVersion"

    //gif
    implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.17'

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor "androidx.room:room-compiler:$room_version"
}

```
