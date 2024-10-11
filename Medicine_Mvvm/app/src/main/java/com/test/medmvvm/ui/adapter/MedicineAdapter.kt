package com.test.medmvvm.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.medmvvm.databinding.ItemMedicineBinding
import com.test.medmvvm.local_db.AssociatedDrug


/**
 * Adapter for displaying a list of medicines in a RecyclerView.
 *
 * @property medlist The list of medicine items to be displayed.
 * @property onMedicineSelectListener Listener for handling medicine selection events.
 */
@SuppressLint("NotifyDataSetChanged")
class MedicineAdapter(
    private var medlist: ArrayList<AssociatedDrug>,
    private val onMedicineSelectListener: OnMedicineSelectListener
) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    /**
     * Updates the list of medicines and notifies the adapter of the data change.
     *
     * @param newCountries The new list of medicines to be displayed.
     */
    fun updateMedicineList(newCountries: List<AssociatedDrug>) {
        medlist.clear() // Clear the existing list
        medlist.addAll(newCountries) // Add new medicines
        notifyDataSetChanged() // Notify the adapter about data change
    }

    /**
     * Creates a new ViewHolder for a medicine item.
     *
     * @param parent The parent ViewGroup where the new ViewHolder will be added.
     * @param viewType The view type of the new ViewHolder.
     * @return A new instance of MedicineViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMedicineBinding.inflate(inflater, parent, false) // Inflate item layout
        return MedicineViewHolder(binding) // Return new ViewHolder
    }

    /**
     * Returns the total number of medicine items in the list.
     *
     * @return The size of the medicine list.
     */
    override fun getItemCount() = medlist.size

    /**
     * Binds the data to the ViewHolder at the specified position.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(medlist[position]) // Bind medicine data to ViewHolder
        holder.itemView.setOnClickListener {
            onMedicineSelectListener.onClick(medlist[position]) // Handle item click
        }
    }

    /**
     * ViewHolder for displaying individual medicine items.
     *
     * @property binding The binding for the item view.
     */
    class MedicineViewHolder(private val binding: ItemMedicineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the medicine data to the item view.
         *
         * @param model The medicine item to be displayed.
         */
        fun bind(model: AssociatedDrug) {
            binding.tvName.text = "Name: ${model.name}"
            binding.tvDose.text = "Dose: ${model.dose}"
            binding.tvStrength.text = "Strength: ${model.strength}"
        }
    }
}

/**
 * Listener interface for handling medicine selection events.
 *
 * @property clickListener The lambda function to be invoked on medicine selection.
 */
class OnMedicineSelectListener(val clickListener: (country: AssociatedDrug) -> Unit) {
    /**
     * Invokes the click listener for the selected medicine.
     *
     * @param country The selected medicine item.
     */
    fun onClick(country: AssociatedDrug) = clickListener(country)
}
