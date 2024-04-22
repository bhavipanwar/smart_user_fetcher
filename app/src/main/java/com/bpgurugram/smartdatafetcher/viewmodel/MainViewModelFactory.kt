package com.bpgurugram.smartdatafetcher.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bpgurugram.smartdatafetcher.repository.UserRepository

class MainViewModelFactory(var userRepository: UserRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository) as T
    }
}
