package com.example.androidmasterclass.modules.firebase_realtime.domain.use_case

import com.example.androidmasterclass.modules.firebase_realtime.data.repository.FirebaseUserRepository
import javax.inject.Inject

class FirebaseUseCase @Inject constructor(val firebaseUserRepository: FirebaseUserRepository) {
}