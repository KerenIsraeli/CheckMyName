package com.example.checkmyname.nameChecker.domain

interface NameUpdaterRepository {
    suspend fun updateName(name : String)
}