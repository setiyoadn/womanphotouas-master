package id.andono.dwisetiyo

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.andono.dwisetiyo.network.WomanPhoto
import id.andono.dwisetiyo.overview.PhotoGridAdapter
import id.andono.dwisetiyo.overview.WomanApiStatus

/**
 * Memperbarui data yang ditampilkan di [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<WomanPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
 * Menggunakan perpustakaan Coil untuk memuat gambar dengan URL ke dalam [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Adaptor pengikat ini menampilkan [WomanApiStatus] dari permintaan jaringan dalam tampilan gambar.
 * Saat permintaan sedang dimuat, ini akan menampilkan file loading_animation.
 * Jika permintaan memiliki kesalahan, ini akan menampilkan gambar yang rusak untuk mencerminkan kesalahan koneksi.
 * Ketika permintaan selesai, itu menyembunyikan tampilan gambar.
 */
@BindingAdapter("womanApiStatus")
fun bindStatus(statusImageView: ImageView, status: WomanApiStatus?) {
    when (status) {
        WomanApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        WomanApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        WomanApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
