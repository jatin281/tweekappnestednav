package com.example.tweekappnestednav.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun GroupList(name: String, nickname: String, address: String){

val namelist = remember { MutableStateFlow(arrayListOf<String>()) }
val nameList by remember { namelist }.collectAsState()

//    val nameList = ArrayList<String>()

    var nicknameList = ArrayList<String>()

    var addressList = ArrayList<String>()


    fun addName(name: String){
        nameList.add(name)
    }

    fun addNickname(nickname: String){
        nicknameList.add(nickname)
    }

    fun addAddress(address: String){
        addressList.add(address)
    }
}

