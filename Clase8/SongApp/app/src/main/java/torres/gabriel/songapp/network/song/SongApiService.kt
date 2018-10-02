package torres.gabriel.songapp.network.song

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import torres.gabriel.songapp.model.Song
import torres.gabriel.songapp.util.Constants

interface SongApiService {
    @GET(Constants.API_SONG)
    fun getSongs(): Observable<List<Song>>

    @GET(Constants.API_SONG+"/{id}")
    fun getSong(@Path("id")id:Int):Observable<Song>
}