package com.example.testproject.data.repository

import com.example.testproject.domain.model.Post

interface PostRepository {
   suspend fun getPost():List<Post>
}