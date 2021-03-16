package eu.gsegado.heavyplateloader.customs

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView
import androidx.preference.DropDownPreference
import androidx.preference.PreferenceViewHolder

class DropDownPrefCustom : DropDownPreference {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?,
                defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun onBindViewHolder(view: PreferenceViewHolder?) {
        super.onBindViewHolder(view)
        val titleView = view?.findViewById(android.R.id.title) as TextView
        titleView.setTextColor(Color.WHITE)
        val summaryView = view.findViewById(android.R.id.summary) as TextView
        summaryView.setTextColor(Color.WHITE)
    }

}