package citics.sharing.activity.base

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ext.SdkExtensions
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import citics.sharing.data.model.others.ImageModel
import citics.sharing.data.model.others.ImageStatus
import citics.sharing.extension.checkSelfPermissionCompat
import citics.sharing.extension.toListMultiPart
import citics.sharing.utils.FileUtils
import citics.sharing.utils.IUploadFileHandle
import citics.sharing.viewmodels.UploaderSVM
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ChinhQT on 22/02/2023.
 */
@AndroidEntryPoint
abstract class ImageActivity : ThemeActivity() {

    private val uploaderSVM: UploaderSVM by viewModels()
    private var iHandleResult: IUploadFileHandle? = null

    private lateinit var pickMultipleMediaLauncher: ActivityResultLauncher<Intent>
    private lateinit var startForPickImages: ActivityResultLauncher<Intent>
    private lateinit var startForTakeImage: ActivityResultLauncher<Intent>

    private var imageFilePath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize multiple media picker launcher
        pickMultipleMediaLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                Timber.d("pickMultipleMediaLauncher")
                if (result.resultCode != Activity.RESULT_OK) {
//                    showError(getString(R.string.picker_fail))
                } else {
                    processPickedImages(result)
                }
            }

        startForPickImages =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                Timber.d("startForPickImages-registerForActivityResult")
                if (result.resultCode == Activity.RESULT_OK) {
                    processPickedImages(result)
                }
            }

        startForTakeImage =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                Timber.d("startForTakeImage")
                if (result.resultCode == Activity.RESULT_OK) {
                    processTakeImage()
                }
            }
    }

    //region Image Chooser

    private fun processTakeImage() {
        Timber.d("processTakeImage")
        imageFilePath?.let {
            val id = it.hashCode().toString()
            val imagesList = HashMap<String, ImageModel>()
            imagesList[id] = ImageModel(id, it, ImageStatus.NONE.ordinal, "")
            resultImagesList(imagesList)
        }
    }

    private fun processPickedImages(result: ActivityResult) {
        Timber.d("processPickedImages")
        val imagesList = HashMap<String, ImageModel>()

        val uris = result.data?.clipData

        uris?.let {
            for (index in 0 until uris.itemCount) {
                val uriPath = uris.getItemAt(index).uri.toString()
                val id = uriPath.hashCode().toString()
                imagesList[id] = ImageModel(id, uriPath, ImageStatus.NONE.ordinal, "")
            }
        } ?: kotlin.run {
            val uriPath = result.data?.data?.toString()
            uriPath?.let {
                val id = uriPath.hashCode().toString()
                imagesList[id] = ImageModel(id, uriPath, ImageStatus.NONE.ordinal, "")
            }
        }

        resultImagesList(imagesList)
    }

    fun imageChooser() {
        Timber.d("imageChooser")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            pickMultipleMediaLauncher.launch(Intent(MediaStore.ACTION_PICK_IMAGES).apply {
//                type = "image/*"
//                // putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 3)
//            })
//        } else {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        pickMultipleMediaLauncher.launch(intent)
//        }
    }

    fun isPhotoPickerAvailable(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            true
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                SdkExtensions.getExtensionVersion(Build.VERSION_CODES.R) >= 2
            } else {
                false
            }
        } else {
            false
        }
    }

    //endregion

    //region Camera and Image

    open fun showImagePreview() {
        Timber.d("showImagePreview")
        handleSystem({
            imageChooser()
        }) {
            if (checkSelfPermissionCompat(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // Permission is already available, start camera preview
                imageChooser()
            } else {
                // Permission is missing and must be requested.
                requestStoragePermission()
            }
        }
    }

    fun handleSystem(newSdk: () -> Unit, oldSdk: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            newSdk.invoke()
        } else {
            oldSdk.invoke()
        }
    }

    open fun showCameraPreview() {
        // Check if the Camera permission has been granted
//        handleSystem({
//            startCamera()
//        }) {
            if (checkSelfPermissionCompat(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                // Permission is already available, start camera preview
                startCamera()
            } else {
                // Permission is missing and must be requested.
                requestCameraPermission()
            }
//        }
    }

    open fun showFilePreview() {
        handleSystem({
            fileChooser()
        }) {
            if (checkSelfPermissionCompat(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED || checkSelfPermissionCompat(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is already available, start camera preview
                fileChooser()
            } else {
                // Permission is missing and must be requested.
                requestAllFileInStoragePermission()
            }
        }
    }


    open fun fileChooser() {
        var chooseFile = Intent(Intent.ACTION_GET_CONTENT)
        chooseFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        chooseFile.type = "*/*"
        chooseFile.putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 10)
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
        chooseFile = Intent.createChooser(chooseFile, "Choose a file")
        pickMultipleMediaLauncher.launch(chooseFile)
    }

    fun startCamera() {
        Timber.d("startCamera")
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            //Create a file to store the image
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (error: IOException) {
                error.printStackTrace()
            }

            if (photoFile != null) {
                val photoURI: Uri =
                    FileProvider.getUriForFile(this, "$packageName.provider", photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                takePictureIntent.flags =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                startForTakeImage.launch(takePictureIntent)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat(
            "yyyyMMdd_HHmmss", Locale.getDefault()
        ).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        imageFilePath = image.absolutePath
        return image
    }

    //endregion

    //region For Register Agent

    fun uploadAgencyPhoto() {
        Timber.d("uploadAgencyPhoto")

        val imagesList = uploaderSVM.imageList.value
        val fileList = imagesList?.map { item ->
            FileUtils.fileFromGeneralUri(
                this, Uri.parse(item.value.clientPath)
            )
        }?.toMutableList()

        fileList.let {
            if (it != null) {
                uploaderSVM.uploadAgencyPhoto(it.toListMultiPart(this))
            }
        }
    }

    //endregion

    //region For Record - Note

    fun uploaderImages() {
        Timber.d("uploaderImages")

        val imagesList = uploaderSVM.imageList.value
        val fileList = imagesList?.map {
            FileUtils.fileFromGeneralUri(
                this, Uri.parse(it.value.clientPath)
            )
        }?.toMutableList()

        fileList.let {
            it?.toListMultiPart(this)?.let { images -> uploaderSVM.uploaderImages(images) }
        }
    }

    //endregion

    //region For Record - Legal

    fun uploaderLegalImages() {
        Timber.d("uploaderLegalImages")

        val imagesList = uploaderSVM.imageList.value
        val fileList = imagesList?.filter {
            it.value.downloadStatus != ImageStatus.DOWNLOADED.ordinal
        }?.map {
            FileUtils.fileFromGeneralUri(
                this, Uri.parse(it.value.clientPath)
            )
        }?.toMutableList()

        if (fileList?.isEmpty() == true) {
            uploaderSVM.uploaderNoImages()
        } else {
            fileList?.toListMultiPart(this)?.let { it1 -> uploaderSVM.uploaderLegalImages(it1) }
        }
    }

    //endregion

    fun uploadAvatarPhoto() {
        Timber.d("uploadAvatarPhoto")

        val imagesList = uploaderSVM.imageList.value
        val fileList = imagesList?.map { item ->
            FileUtils.fileFromGeneralUri(
                this, Uri.parse(item.value.clientPath)
            )
        }

        fileList.let {
            if (it != null) {
                uploaderSVM.avatarUpload(
                    MultipartBody.Part.createFormData(
                        "avatar",
                        it[0].name,
                        it[0]
                            .asRequestBody("*/*".toMediaTypeOrNull())
                    )
                )
            }
        }
    }

    abstract fun showError(message: String)
    abstract fun showInfo(message: String)
    abstract fun requestStoragePermission()
    abstract fun requestAllFileInStoragePermission()
    abstract fun requestCameraPermission()

    public fun setIUploadFileHandle(handle: IUploadFileHandle) {
        this.iHandleResult = handle
    }

    private fun resultImagesList(imagesList: HashMap<String, ImageModel>) {
        iHandleResult?.onResultFile(-1, imagesList)
        // Add all the previous files to the new list
        uploaderSVM.imageList.value?.let { imagesList.putAll(it) }

        uploaderSVM.changeImages(imagesList)
    }
}