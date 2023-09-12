package com.example.skarvoassigment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModel : ViewModel() {



    private val bottomSheetLiveData = MutableStateFlow<List<Model>>(emptyList())
    private val mainViewData = MutableStateFlow<List<Model>>(emptyList())
    val bottomSheetData=bottomSheetLiveData.asStateFlow()

    val mainViedData=mainViewData.asStateFlow()

    fun getData() {
        bottomSheetLiveData.value = plantsList
    }
    fun getMainDataData() {
        mainViewData.value = maiViewList
    }

    fun addItem(item: Model) {
        maiViewList.add(item)
        mainViewData.update {
            it+item
        }
    }
    fun delItem(item: Model) {
        maiViewList.add(item)
        mainViewData.update {
            it-item
        }
    }

    private val plantsList =
        listOf(
            Model("firstName", "firstDesc"), Model("secondName", "secondDesc"),
            Model("thirdName", "thirdDesc"),
            Model("fourthName", "fourthDesc"),

            Model("fifthName", "fifthDesc"),

            Model("sixthName", "sixthDesc")
        ).toMutableList()

    private val maiViewList =
        listOf(
            Model("firstName", "firstDesc"), Model("secondName", "secondDesc"),
            Model("thirdName", "thirdDesc"),
            Model("fourthName", "fourthDesc"),

            Model("fifthName", "fifthDesc"),

            Model("sixthName", "sixthDesc")
        ).toMutableList()

}