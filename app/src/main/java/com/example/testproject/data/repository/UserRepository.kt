package com.example.testproject.data.repository

import com.example.testproject.data.model.userModel.UserModelDTO
import com.example.testproject.domain.model.Post

interface UserRepository {
   suspend fun getUser():List<UserModelDTO>
}