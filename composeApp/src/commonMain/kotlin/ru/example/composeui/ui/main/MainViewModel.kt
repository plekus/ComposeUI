package ru.example.composeui.ui.main

import androidx.compose.ui.graphics.ImageBitmap
import ru.example.composeui.ui.base.BaseViewModel
import ru.example.composeui.utils.ImageFile
import ru.example.composeui.utils.uuid

sealed class MainAction {
    data object OnButtonClick: MainAction()
    data object OnCameraSelect: MainAction()
    data object OnGallerySelect: MainAction()
    data class OnLoadPhoto(val photo: ImageFile?): MainAction()

}

sealed class MainUiEvent {
    data class OpenPickerBottomSheet(val uid: String = uuid()): MainUiEvent()
    data object AskCameraPermission: MainUiEvent()
    data object AskGalleryPermission: MainUiEvent()
}

data class MainUiState(
    val image: ImageFile? = null
)

class MainViewModel: BaseViewModel<MainUiState, MainUiEvent, MainAction>(MainUiState()) {

    override fun obtainAction(action: MainAction) {
        when(action) {
            MainAction.OnButtonClick -> postEvent(MainUiEvent.OpenPickerBottomSheet())
            MainAction.OnCameraSelect -> postEvent(MainUiEvent.AskCameraPermission)
            MainAction.OnGallerySelect -> postEvent(MainUiEvent.AskGalleryPermission)
            is MainAction.OnLoadPhoto -> updateState { copy(image = action.photo) }
        }
    }
}