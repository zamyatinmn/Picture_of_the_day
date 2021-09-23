package com.geekbrains.pictureoftheday.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


/**
 * Created by Maxim Zamyatin on 23.09.2021
 */


abstract class ViewBindingFragment<T : ViewBinding>(
    private val inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : Fragment() {
    private var _ui: T? = null

    protected val ui: T
        get() = _ui!!

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _ui = inflateBinding.invoke(inflater, container, false)
        return ui.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }
}