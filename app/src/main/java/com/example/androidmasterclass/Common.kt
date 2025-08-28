package com.example.androidmasterclass

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirestoreRepo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealtimeRepo
