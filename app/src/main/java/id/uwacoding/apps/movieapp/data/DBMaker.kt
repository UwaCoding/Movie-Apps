package id.uwacoding.apps.movieapp.data

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import id.uwacoding.apps.movieapp.data.MovinDB.Companion.DB_NAME
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Firman on 6/15/2017.
 */
object DBMaker {

    val isDbCreated = MutableLiveData<Boolean>()

    lateinit var db: MovinDB

    private val initializing = AtomicBoolean(true)

    fun createDb(context: Context) {
        if (initializing.compareAndSet(true, false).not()) {
            return
        }
        isDbCreated.value = false

        Completable.fromAction {
            db = Room.databaseBuilder(context, MovinDB::class.java, DB_NAME).build()
        }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ isDbCreated.value = true }, { it.printStackTrace() })
    }

}