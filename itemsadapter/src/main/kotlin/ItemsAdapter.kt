package com.redmadrobot.itemsadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * An adapter for simple lists.
 *
 * The adapter can work in looping mode if you set parameter [isLooping] to `true`.
 * This mode will work only if you've passed more than one item to adapter.
 *
 * **CAUTION:** Items list can't be modified after creation so this adapter should be
 * used only to show static data.
 * @see itemsAdapter
 */
public class ItemsAdapter(
    private val items: List<Item> = emptyList(),
    isLooping: Boolean = false,
) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val isLooping = isLooping && items.size > 1

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        if (isLooping) recyclerView.scrollToPosition(Int.MAX_VALUE / 2)
    }

    override fun getItemCount(): Int = if (isLooping) Int.MAX_VALUE else items.size

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position % items.size].bind(holder)
    }

    override fun getItemViewType(position: Int): Int = items[position % items.size].layoutId

    /** The simplest possible ViewHolder. */
    public class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view)

    /** Item holds [layoutId] used to inflate view and function [bind] used to bind data onto view. */
    public class Item(@get:LayoutRes internal val layoutId: Int, internal val bind: (View) -> Unit) {
        internal fun bind(holder: ViewHolder) {
            bind(holder.itemView)
        }
    }

    /**
     * BindingContext contains info about the current item and holds DSL methods for binding data onto view.
     * @property index Index of the current item.
     */
    public class BindingContext internal constructor(public val index: Int) {

        /** Binds data onto view. */
        public fun bind(@LayoutRes layoutId: Int, bind: View.() -> Unit = {}): Item {
            return Item(layoutId) { it.bind() }
        }

        /** Binds data onto view with type [V]. */
        @JvmName("bindTyped")
        public fun <V : View> bind(@LayoutRes layoutId: Int, bind: V.() -> Unit = {}): Item {
            @Suppress("UNCHECKED_CAST")
            return Item(layoutId) { (it as V).bind() }
        }
    }
}

/**
 * Creates and initializes [ItemsAdapter] with DSL.
 *
 * Function [binding] used to bind given [items] onto view using DSL.
 * The simplest case of usage is:
 * ```
 *  itemsAdapter(regions) { region ->
 *      bind(R.layout.view_region) {
 *          view_region_title.text = region.title
 *          view_region_description.text = region.description
 *      }
 *  }
 * ```
 *
 * If you have more than one view type, you can use operator `when`:
 * ```
 *  itemsAdapter(contactsItems) { item ->
 *      when (item) {
 *          is ContactsItem.Header -> bind(R.layout.view_contacts_header) {
 *              view_contacts_header_letter.text = item.letter
 *          }
 *
 *          is ContactsItem.Entry -> bind(R.layout.view_contacts_entry) {
 *              view_contacts_entry_name.text = item.name
 *              view_contacts_entry_phone.text = item.phone
 *          }
 *      }
 *  }
 * ```
 * @see ItemsAdapter
 * @see ItemsAdapter.BindingContext.bind
 */
public fun <T> itemsAdapter(
    items: List<T>,
    isLooping: Boolean = false,
    binding: ItemsAdapter.BindingContext.(T) -> ItemsAdapter.Item,
): ItemsAdapter {
    return ItemsAdapter(
        items = items.mapIndexed { index, item -> ItemsAdapter.BindingContext(index).binding(item) },
        isLooping = isLooping,
    )
}
