package torres.gabriel.songapp.network.song

import android.content.Context
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import torres.gabriel.songapp.model.Song
import torres.gabriel.songapp.network.ApiUtils
import torres.gabriel.songapp.network.GeneralCallback


class SongInteractor{

    private var mAPIService:SongApiService = ApiUtils().getSongApiService()
    private var callback:SongCallback

    constructor(songCallback: SongCallback){
        callback = songCallback as SongCallback
    }

    fun getSongs(id:Int? = null) {
        mAPIService.getSongs().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Song>> {

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Song>) {
                        callback.onSongsObtained(t)
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }

                    override fun onComplete() {
                    }
                })
    }

    interface SongCallback:GeneralCallback{
        fun onSongsObtained(songs:List<Song>)
    }

}