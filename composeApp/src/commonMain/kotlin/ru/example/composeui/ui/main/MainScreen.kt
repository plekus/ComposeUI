package ru.example.composeui.ui.main

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.example.composeui.ui.base.SafeArea
import ru.example.composeui.ui.picker.PhotosPickerBottomSheet
import ru.example.composeui.utils.OnBackHandler
import ru.example.composeui.utils.PermissionCallback
import ru.example.composeui.utils.PermissionStatus
import ru.example.composeui.utils.PermissionType
import ru.example.composeui.utils.createPermissionsManager
import ru.example.composeui.utils.rememberCameraManager
import ru.example.composeui.utils.rememberGalleryManager

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel { MainViewModel() }
) {

    val uiState by viewModel.uiState.collectAsState()
    val uiEvent by viewModel.uiEvent.collectAsState(null)

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val permissionType = remember { mutableStateOf<PermissionType?>(null) }

    OnBackHandler(enabled = sheetState.targetValue != ModalBottomSheetValue.Hidden) {
        scope.launch { sheetState.hide() }
    }

    LaunchedEffect(uiEvent) {
        when(uiEvent) {
            MainUiEvent.AskCameraPermission -> {
                permissionType.value = PermissionType.CAMERA
            }
            MainUiEvent.AskGalleryPermission -> {
                permissionType.value = PermissionType.GALLERY
            }
            is MainUiEvent.OpenPickerBottomSheet -> {
                scope.launch { sheetState.show() }
            }
            null -> { /* no-op */ }
        }
    }

    val galleryManager = rememberGalleryManager {
            viewModel.obtainAction(MainAction.OnLoadPhoto(it))
        }

    val cameraManager = rememberCameraManager {
            viewModel.obtainAction(MainAction.OnLoadPhoto(it))
        }

    val permissionsManager =
        createPermissionsManager(
            object : PermissionCallback {
                override fun onPermissionStatus(
                    permissionType: PermissionType,
                    status: PermissionStatus,
                ) {
                    when (status) {
                        PermissionStatus.GRANTED -> {
                            when (permissionType) {
                                PermissionType.CAMERA -> {
                                    scope.launch {
                                        withContext(Dispatchers.Main) {
                                            cameraManager.launch()
                                        }
                                    }
                                }
                                PermissionType.GALLERY -> {
                                    scope.launch {
                                        withContext(Dispatchers.Main) {
                                            galleryManager.launch()
                                        }
                                    }
                                }
                            }
                        }
                        else -> { /* no-op */ }
                    }
                }
            },
        )

    permissionType.value?.let {
        permissionsManager.askPermission(it)
        permissionType.value = null
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            PhotosPickerBottomSheet(
                onCameraSelect = {
                    scope.launch { sheetState.hide() }
                    viewModel.obtainAction(MainAction.OnCameraSelect)
                },
                onGallerySelect = {
                    scope.launch { sheetState.hide() }
                    viewModel.obtainAction(MainAction.OnGallerySelect)
                }
            )
        },
        content = {
            SafeArea {
                MainView(
                    state = uiState,
                    onButtonClick = {
                        viewModel.obtainAction(MainAction.OnButtonClick)
                    }
                )
            }
        }
    )

}