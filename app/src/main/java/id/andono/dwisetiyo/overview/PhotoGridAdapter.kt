package id.andono.dwisetiyo.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.andono.dwisetiyo.databinding.GridViewItemBinding
import id.andono.dwisetiyo.network.WomanPhoto

/**
 * Kelas ini mengimplementasikan [RecyclerView] [ListAdapter]
 * yang menggunakan Data Binding untuk menyajikan data [Daftar],
 * termasuk menghitung perbedaan antara daftar.
 */
class PhotoGridAdapter :
    ListAdapter<WomanPhoto, PhotoGridAdapter.WomanPhotosViewHolder>(DiffCallback) {

    /**
     * Konstruktor WomanPhotosViewHolder mengambil variabel binding dari GridViewItem terkait,
     * yang dengan baik memberikannya akses ke informasi [WomanPhoto] lengkap.
     */
    class WomanPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(womanPhoto: WomanPhoto) {
            binding.photo = womanPhoto
            // Ini penting, karena memaksa pengikatan data untuk segera dieksekusi,
            // yang memungkinkan RecyclerView membuat pengukuran ukuran tampilan yang benar
            binding.executePendingBindings()
        }
    }

    /**
     * Memungkinkan RecyclerView untuk menentukan item
     * mana yang telah berubah ketika [List] dari [WomanPhoto] telah diperbarui.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<WomanPhoto>() {
        override fun areItemsTheSame(oldItem: WomanPhoto, newItem: WomanPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WomanPhoto, newItem: WomanPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /**
     * Buat tampilan item [RecyclerView] baru (dipanggil oleh pengelola tata letak)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WomanPhotosViewHolder {
        return WomanPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Mengganti konten tampilan (dipanggil oleh pengelola tata letak)
     */
    override fun onBindViewHolder(holder: WomanPhotosViewHolder, position: Int) {
        val womanPhoto = getItem(position)
        holder.bind(womanPhoto)
    }
}