package com.mutualmobile.mmnotes

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}