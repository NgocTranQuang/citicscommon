package citics.sharing.utils

import citics.sharing.data.model.others.ImageModel

interface IUploadFileHandle {
    fun onResultFile(type: Int, data: HashMap<String, ImageModel>)
}