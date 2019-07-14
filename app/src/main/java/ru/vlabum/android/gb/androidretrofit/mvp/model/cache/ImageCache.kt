package ru.vlabum.android.gb.androidretrofit.mvp.model.cache

import android.graphics.Bitmap
import io.reactivex.Scheduler
import ru.vlabum.android.gb.androidretrofit.App
import ru.vlabum.android.gb.androidretrofit.mvp.model.entity.IImage
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ImageCache(val cache: IImageCacheDb, val mainThreadScheduler: Scheduler) {

    companion object {
        private val IMAGE_FOLDER_NAME = "image"
    }

    fun getFile(url: String): File? {
        var cachedImage: IImage? = cache.findFirst(url)
        return cachedImage?.getPath()?.let {
            File(it)
        }
    }

    fun contains(url: String): Boolean {
        return cache.contains(url)
    }

    fun clear() {
        cache.clear()
        deleteFileOrDirRecursive(getImageDir())
    }

    fun saveImage(url: String, bitmap: Bitmap): File? {

        if (!getImageDir().exists() && !getImageDir().mkdirs()) {
            throw RuntimeException("Failed to create directory: " + getImageDir().toString())
        }

        val fileFormat: String = if (url.contains(".jpg")) ".jpg" else ".png"
        val imageFile: File = File(getImageDir(), SHA1(url) + fileFormat)
        val fos: FileOutputStream

        try {
            fos = FileOutputStream(imageFile)
            bitmap.compress(
                if (fileFormat == "jpg") Bitmap.CompressFormat.JPEG else Bitmap.CompressFormat.PNG,
                100,
                fos
            )
            fos.close()
        } catch (e: Exception) {
            Timber.d("Failed to save image")
            return null
        }

        cache.saveImage(url, imageFile.path)

        return imageFile
    }

    fun SHA1(s: String): String {
        var m: MessageDigest? = null

        try {
            m = MessageDigest.getInstance("SHA1")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        m!!.update(s.toByteArray(), 0, s.length)
        return BigInteger(1, m.digest()).toString(16)
    }

    fun getImageDir(): File {
        val path: String = App.getInstance().getExternalFilesDir(null)!!.path + "/" + IMAGE_FOLDER_NAME
        return File(path)
    }

    fun deleteFileOrDirRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory) {
            for (child in fileOrDirectory.listFiles()) {
                deleteFileOrDirRecursive(child)
            }
        }
        fileOrDirectory.delete()
    }
}