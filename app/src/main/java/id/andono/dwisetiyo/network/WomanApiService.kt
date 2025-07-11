package id.andono.dwisetiyo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://run.mocky.io/v3/"

/**
 * membangun objek moshi yang digunakan untuk retrofit,
 * dan pastikan menambahkan adaptor kotlin untuk kompatibilitas penuh kotlin
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Gunakan pembuat Retrofit untuk membuat objek retrofit
 * menggunakan konverter Moshi dengan objek Moshi.
 */

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Antarmuka publik yang memperlihatkan metode [getPhotos]
 */
interface WomanApiService {
    /**
     *Antarmuka publik yang mengekspos metode [getPhotos] Mengembalikan [Daftar] [WomanPhoto]
     * dan metode ini dapat dipanggil dari Coroutine.
     * Anotasi @GET menunjukkan bahwa titik akhir "foto" akan diminta dengan metode GET HTTP
     */
    @GET("5b2d546e-dcb7-4246-8145-900ce0f75266")
    suspend fun getPhotos(): List<WomanPhoto>
}

/**
 * Objek Api publik yang mengekspos layanan Retrofit yang diinisialisasi dengan lazy
 */
object WomanApi {
    val retrofitService: WomanApiService by lazy { retrofit.create(WomanApiService::class.java) }
}
