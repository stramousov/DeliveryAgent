package ru.cse.app.tasks

import android.os.AsyncTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.cse.app.common.DeliveryAgentApplication
import ru.cse.app.models.Safekeep
import ru.cse.ui.adapters.SafekeepAdapter

class SafekeepTask : AsyncTask<String, Void, SafekeepAdapter>() {

    private var api = DeliveryAgentApplication().api
    private lateinit var safekeepAdapter: SafekeepAdapter

    override fun doInBackground(vararg params: String?): SafekeepAdapter? {

        safekeepAdapter = SafekeepAdapter()

        api.Safekeeper()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onLoadSafekeep(it) },
                        { onError(it) }
                )

        return safekeepAdapter
    }

    private fun onLoadSafekeep(it: List<Safekeep>) {
        safekeepAdapter.setSafekeeps(it)
    }

    private fun onError(it: Throwable) {}
}