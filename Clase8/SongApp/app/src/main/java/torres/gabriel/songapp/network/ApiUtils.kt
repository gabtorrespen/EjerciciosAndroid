package torres.gabriel.songapp.network

import torres.gabriel.songapp.network.song.SongApiService
import torres.gabriel.songapp.util.Constants

class ApiUtils {

    fun getSongApiService():SongApiService{
        return RetrofitClient().getClient(Constants.BASE_URL).create(SongApiService::class.java)
    }

}