package citics.sharing.utils

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import citics.sharing.extension.isDocument
import timber.log.Timber
import java.io.*


object FileUtils {
    fun getFileType(context: Context, uri: Uri): String? {
        val cR: ContentResolver = context.contentResolver
        val mime = MimeTypeMap.getSingleton()
        var type = mime.getExtensionFromMimeType(cR.getType(uri))
        Timber.d("TYPE FILE  $type")
        if (type == null) {
            if (cR.getType(uri)?.contains("pdf") == true) {
                type = "pdf"
            }
        }
        return type

//        return context.contentResolver.getType(uri)

    }

    fun fileFromGeneralUri(context: Context, filePath: Uri): File {
        return if (filePath.toString().startsWith("content://")) {
            val file = fileFromContentUri(context, filePath)
            file
        } else {
            File(filePath.toString())
        }
    }

    fun fileFromContentUri(context: Context, contentUri: Uri): File {
        // Preparing Temp file name
//        val fileExtension = getFileExtension(context, contentUri)
//        val nameFile = Date().time + Random.nextInt(1000)
//        val fileName = "temp_file_${nameFile}" + if (fileExtension != null) ".$fileExtension" else ""
        val fileName = context.getFileName(contentUri)

        // Creating Temp file
        val tempFile = File(context.cacheDir, fileName)
        tempFile.createNewFile()

        try {
            val oStream = FileOutputStream(tempFile)
            val inputStream = context.contentResolver.openInputStream(contentUri)

            inputStream?.let {
                copy(inputStream, oStream)
            }

            oStream.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tempFile
    }

    fun Context.getFileName(uri: Uri): String? = when(uri.scheme) {
        ContentResolver.SCHEME_CONTENT -> getContentFileName(uri)
        else -> uri.path?.let(::File)?.name
    }

    private fun Context.getContentFileName(uri: Uri): String? = runCatching {
        contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            cursor.moveToFirst()
            return@use cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME).let(cursor::getString)
        }
    }.getOrNull()

    private fun getFileExtension(context: Context, uri: Uri): String? {
        val fileType: String? = context.contentResolver.getType(uri)
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType)
    }

    @Throws(IOException::class)
    private fun copy(source: InputStream, target: OutputStream) {
        val buf = ByteArray(8192)
        var length: Int
        while (source.read(buf).also { length = it } > 0) {
            target.write(buf, 0, length)
        }
    }

    fun getIntentToOpenFile(context: Context, uri: Uri): Intent {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW

        val url: String = if (uri.toString().startsWith("content://")) {
            ".${getFileExtension(context, uri)}"
        } else {
            uri.toString()
        }

        if (url.contains(".doc") || url.contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword")
        } else if (url.contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf")
        } else if (url.contains(".ppt") || url.contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint")
        } else if (url.contains(".xls") || url.contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel")
        } else if (url.contains(".zip") || url.contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav")
        } else if (url.contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf")
        } else if (url.contains(".wav") || url.contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav")
        } else if (url.contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif")
        } else if (url.contains(".jpg") || url.contains(".jpeg") || url.contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg")
        } else if (url.contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain")
        } else if (url.contains(".3gp") || url.contains(".mpg") || url.contains(".mpeg") || url.contains(
                ".mpe"
            ) || url.contains(".mp4") || url.contains(".avi")
        ) {
            // Video files
            intent.setDataAndType(uri, "video/*")
        } else {
            if (uri.isDocument(context) == null) {
                // image
                intent.setDataAndType(uri, "image/jpeg")
            } else {
                // Other files
                intent.setDataAndType(uri, "*/*")
            }
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return intent
    }
}