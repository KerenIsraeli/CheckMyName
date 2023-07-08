package com.example.checkmyname.nameChecker.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.checkmyname.nameChecker.domain.NameUpdaterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NameCheckerViewModel(
    private val nameUpdaterRepository: NameUpdaterRepository
) : ViewModel(), INameUpdater {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    override fun onUpdateName(name: String) {
        viewModelScope.launch {
            nameUpdaterRepository.updateName(name)
            _name.value = ""
        }
        _name.value = name
    }
}