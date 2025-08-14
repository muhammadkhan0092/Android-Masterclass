package com.example.androidmasterclass.hilt.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//Entry Point for Dependency Injection in our app
@HiltAndroidApp
class AndroidMasterClassApplication : Application() {
}