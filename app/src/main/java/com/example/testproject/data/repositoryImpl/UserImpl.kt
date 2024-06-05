package com.example.testproject.data.repositoryImpl

import com.example.testproject.data.model.userModel.UserModelDTO
import com.example.testproject.data.network.ApiService
import com.example.testproject.data.repository.UserRepository

class UserImpl(val apiService: ApiService):UserRepository {
   override suspend fun getUser(): List<UserModelDTO> {
        return  apiService.getUser()
    }
}