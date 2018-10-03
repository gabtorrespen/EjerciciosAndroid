package torres.gabriel.songapp.network.song

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*
import torres.gabriel.songapp.model.Song
import torres.gabriel.songapp.util.Constants

interface SongApiService {
    @GET(Constants.API_SONG)
    fun getSongs(): Observable<List<Song>>

    @GET(Constants.API_SONG+"{id}/")
    fun getSong(@Path("id")id:Int):Observable<Song>

    @POST(Constants.API_SONG)
    fun createSong(@Body song:Song): Observable<Song>

    @PUT(Constants.API_SONG+"{id}/")
    fun updateSong(@Path("id")id:Int,@Body song:Song): Observable<Song>

    @DELETE(Constants.API_SONG+"{id}/")
    fun deleteSong(@Path("id")id:Int):Observable<Response<Void>>
}