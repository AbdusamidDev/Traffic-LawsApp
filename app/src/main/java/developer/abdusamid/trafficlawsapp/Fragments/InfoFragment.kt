package developer.abdusamid.trafficlawsapp.Fragments

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.abdusamid.trafficlawsapp.R
import developer.abdusamid.trafficlawsapp.Utils.Data
import developer.abdusamid.trafficlawsapp.databinding.FragmentInfoBinding
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_info, container, false)
        binding.root.rv
        Data.isHome = false
        return root
    }

}