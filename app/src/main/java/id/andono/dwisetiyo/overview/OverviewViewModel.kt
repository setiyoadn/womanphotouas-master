package id.andono.dwisetiyo.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andono.dwisetiyo.network.WomanApi
import id.andono.dwisetiyo.network.WomanPhoto
import kotlinx.coroutines.launch

enum class WomanApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // MutableLiveData internal yang menyimpan status permintaan terbaru
    private val _status = MutableLiveData<WomanApiStatus>()

    // LiveData eksternal yang tidak dapat diubah untuk status permintaan
    val status: LiveData<WomanApiStatus> = _status

    // Secara internal, menggunakan MutableLiveData,
    // karena akan memperbarui Daftar Foto Woman dengan nilai baru
    private val _photos = MutableLiveData<List<WomanPhoto>>()

    // Antarmuka LiveData eksternal ke properti tidak dapat diubah, jadi hanya kelas ini yang dapat memodifikasi
    val photos: LiveData<List<WomanPhoto>> = _photos

    /**
     * Panggil getWomanPhotos() pada init sehingga kami dapat segera menampilkan status.
     */
    init {
        getWomanPhotos()
    }

    /**
     * Mendapatkan informasi foto Woman dari layanan Woman API Retrofit dan memperbarui
     * [WomanPhoto] [List] [LiveData].
     */
    private fun getWomanPhotos() {

        viewModelScope.launch {
            _status.value = WomanApiStatus.LOADING
            try {
                _photos.value = WomanApi.retrofitService.getPhotos()
                _status.value = WomanApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WomanApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}