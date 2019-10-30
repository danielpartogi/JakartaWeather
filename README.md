# JakartaWeather(Using Retrofit + MVVM + data Binding)


- Using Open Weather Api
- Using Coroutines as the async/threading task
- Using Room Database for cached process


## 1. MainActivity.kt

in here we send our viewmodel (MainViewModel) to our layout. We had ObservableField which will observe the values.
Which will change the values of our layout, if we set something on our ObservableField.

## 2. Initialized ViewModel

I'm using background task for initialized the viewmodel, because too many request to repository which maybe will make memory leak,
so I put them in background task, which makes our main thread is safe.

## 3. Process

if we open our apps, it will begin the observer of the livedata. And fetching data from the Api. If we not getting any error(such as
not internet connection) it will updating our database. And our observer of the livedata will be triggered and updating the view

## 4. Retrofit

in retrofit 2.6 we just need suspend and gson parser to make our api became the models. We don't need rxjava.

## 5. Offline Use

our room database help us to use it in offline/cached data. it will shown data of the last time we launch our apps
