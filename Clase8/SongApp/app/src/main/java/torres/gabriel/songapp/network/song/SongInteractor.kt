package torres.gabriel.songapp.network.song

import android.content.Context
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import torres.gabriel.songapp.model.Song
import torres.gabriel.songapp.network.ApiUtils
import torres.gabriel.songapp.network.GeneralCallback


class SongInteractor{

    private var mAPIService:SongApiService = ApiUtils().getSongApiService()
    private var callback:GeneralCallback

    constructor(songCallback: GeneralCallback){
        callback = songCallback
    }

    fun getSongs(id:Int? = null) {
        mAPIService.getSongs().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Song>> {

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Song>) {
                        (callback as SongListCallback).onSongsObtained(t)
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }

                    override fun onComplete() {
                    }
                })
    }

    fun createSong(song:Song) {
        mAPIService.createSong(song).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Song> {

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Song) {
                        (callback as SongAddEditCallback).onSongCreated(t)
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }

                    override fun onComplete() {
                    }
                })
    }

    fun updateSong(song:Song) {
        mAPIService.updateSong(song.id!!,song).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Song> {

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Song) {
                        (callback as SongAddEditCallback).onSongUpdated(t)
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }

                    override fun onComplete() {
                    }
                })
    }

    fun deleteSong(id:Int) {
        mAPIService.deleteSong(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<Response<Void>> {

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Response<Void>) {
                        (callback as SongListCallback).onSongDeleted(id)
                    }

                    override fun onError(e: Throwable) {
                        callback.onError(e)
                    }

                    override fun onComplete() {
                    }
                })
    }

    interface SongListCallback:GeneralCallback{
        fun onSongsObtained(songs:List<Song>)
        fun onSongDeleted(id:Int)
    }

    interface SongAddEditCallback:GeneralCallback{
        fun onSongCreated(song:Song)
        fun onSongUpdated(song:Song)
    }

}