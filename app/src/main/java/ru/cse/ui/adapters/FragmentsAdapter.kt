package ru.cse.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ru.cse.ui.fragments.SafekeepFragmentFinal
import ru.cse.ui.fragments.SafekeepFragmentNew
import ru.cse.ui.fragments.SafekeepFragmentWork


class SafekeepFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var listFragment: ArrayList<Fragment> = ArrayList()
    private var listTitle: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return listTitle[position]
    }

    private fun addFragment(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    fun setFragments() {
        addFragment(SafekeepFragmentNew(), "Новые")
        addFragment(SafekeepFragmentWork(), "В работе")
        addFragment(SafekeepFragmentFinal(), "Завершено")
    }
}

class WaybillFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var listFragment: ArrayList<Fragment> = ArrayList()
    private var listTitle: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return listTitle[position]
    }

    private fun addFragment(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    fun setFragments() {
        addFragment(SafekeepFragmentNew(), "Новые")
        addFragment(SafekeepFragmentWork(), "В работе")
        addFragment(SafekeepFragmentFinal(), "Завершено")
    }
}