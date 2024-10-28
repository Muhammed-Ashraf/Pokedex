package com.example.pokedex.compose.core.viewmodel

// BaseViewModel serves as an abstract class for ViewModels, providing common functionality
// for state management. By inheriting from Android's ViewModel class, it helps maintain
// UI-related data in a lifecycle-conscious way, preventing data loss during configuration
// changes like screen rotations.
abstract class BaseViewModel : ViewModel() {

    // A unique `ViewModelKey` associated with this ViewModel instance.
    // `ViewModelKey` is generated from the name of the class that inherits BaseViewModel,
    // ensuring that each ViewModel instance can have a unique key even when multiple
    // ViewModels of the same type are created. This key can be used to identify or tag
    // specific flows or pieces of data associated with this ViewModel.
    protected val key: ViewModelKey = ViewModelKey(this::class.java.name)

    // An extension function for BaseViewModel that creates a `ViewModelStateFlow` instance.
    // This function takes a generic parameter `T`, meaning it can handle values of any type.
    // It initializes a ViewModelStateFlow with the ViewModel’s unique key (defined above)
    // and an initial value provided by the caller.
    //
    // Parameters:
    // - value: The initial value for the `ViewModelStateFlow`. This could represent any kind of
    //   UI state or data that the ViewModel needs to manage.
    //
    // Returns:
    // - A new instance of `ViewModelStateFlow`, initialized with this ViewModel's unique key
    //   and the provided initial value. This flow is then used to manage and observe state
    //   in a controlled, isolated way within the ViewModel.
    //
    // Usage:
    // Subclasses of BaseViewModel can call this function to create and initialize
    // their own `ViewModelStateFlow` for any type of data they need to manage.
    protected fun <T> BaseViewModel.viewModelStateFlow(value: T): ViewModelStateFlow<T> {
        return ViewModelStateFlow(key = key, value = value)
    }
}
