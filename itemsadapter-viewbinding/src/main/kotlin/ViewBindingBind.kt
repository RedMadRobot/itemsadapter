// Public API
@file:Suppress("unused")

package com.redmadrobot.itemsadapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.redmadrobot.extensions.viewbinding.getBinding

/** Binds data onto view using `ViewBinding`. */
public inline fun <reified VB : ViewBinding> ItemsAdapter.BindingContext.bind(
    @LayoutRes layoutId: Int,
    noinline bind: VB.() -> Unit
): ItemsAdapter.Item {
    return bind(layoutId, bind) { getBinding() }
}

@PublishedApi
internal fun <VB : ViewBinding> bind(
    @LayoutRes layoutId: Int,
    bind: VB.() -> Unit,
    getViewBinding: View.() -> VB
): ItemsAdapter.Item = ItemsAdapter.Item(layoutId) { it.getViewBinding().bind() }
