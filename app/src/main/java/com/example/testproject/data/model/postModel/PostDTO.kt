package com.example.testproject.data.model.postModel

import com.example.testproject.domain.model.Post

data class PostDTO(val id:Int, val userId:String, val title:String, val body:String){
    fun toMapIntoPost():Post{
        return Post(this.id,this.title)
    }
}
