package ru.cse.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.cse.R
import ru.cse.app.common.DeliveryAgentApplication
import ru.cse.app.models.Safekeep
import ru.cse.databinding.SafekeepNewFragmentBinding
import ru.cse.ui.adapters.SafekeepAdapter
import ru.cse.utils.AppUtilities

class SafekeepFragmentNew : Fragment() {

    private var safekeepAdapter: SafekeepAdapter = SafekeepAdapter()

    private lateinit var fragmentView: View
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: SafekeepNewFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onLoadeSafekeeps()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SafekeepNewFragmentBinding.inflate(inflater, container, false)
        fragmentView = binding.root

        swipeRefreshLayout = fragmentView.findViewById(R.id.safekeepNewSwipe)
        recyclerView = fragmentView.findViewById(R.id.safekeepNewRecycler)

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        swipeRefreshLayout.setOnRefreshListener { onRunLoadeSafekeeps() }

        recyclerView.adapter = safekeepAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        return fragmentView
    }

    private fun onRunLoadeSafekeeps() {
        onShowProgress()
        onLoadeSafekeeps()
        onHideProgress()
    }

    private fun onLoadeSafekeeps() {
        val api = (activity!!.application as DeliveryAgentApplication).api

        api.Safekeeper()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onSafekeeperLoaded(it) },
                        { onError(it) }
                )
    }

    private fun onError(it: Throwable) {
        AppUtilities.showSnackbar(context!!, it.message.toString(), true)
    }

    private fun onSafekeeperLoaded(it: List<Safekeep>) {
        safekeepAdapter.setSafekeeps(it)
    }

    private fun onShowProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun onHideProgress() {
        swipeRefreshLayout.isRefreshing = false
    }
}

