package developer.abdusamid.trafficlawsapp.Fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdusamid.trafficlawsapp.MainActivity
import developer.abdusamid.trafficlawsapp.R
import developer.abdusamid.trafficlawsapp.databinding.FragmentShowBinding
import kotlinx.android.synthetic.main.fragment_show.view.*
import developer.abdusamid.trafficlawsapp.Utils.Data

class ShowFragment : Fragment() {

    lateinit var root: View
    lateinit var binding: FragmentShowBinding

    override fun onStart() {
        super.onStart()
        (activity as MainActivity?)?.isVisible(false)
        Data.isHome = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_show, container, false)
        binding = FragmentShowBinding.inflate(LayoutInflater.from(context))

        val byte = arguments?.getString("image")
        val name = arguments?.getString("name", "null")
        val about = arguments?.getString("about", "null")

        root.image_view.setImageURI(Uri.parse(byte))
        root.header_title.text = name
        root.name.text = name
        root.txt_msg.text = about

        root.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return root
    }
}