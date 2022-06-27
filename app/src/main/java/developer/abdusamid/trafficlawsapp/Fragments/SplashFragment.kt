package developer.abdusamid.trafficlawsapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdusamid.trafficlawsapp.MainActivity
import developer.abdusamid.trafficlawsapp.R
import developer.abdusamid.trafficlawsapp.databinding.FragmentSplashBinding
import developer.abdusamid.trafficlawsapp.Utils.Data

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        root = inflater.inflate(R.layout.fragment_splash, container, false)
        binding = FragmentSplashBinding.bind(root)

        binding.root.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }

        Data.isHome = false

        return root
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity?)?.isVisible(false)
        val anim = AnimationUtils.loadAnimation(root.context, R.anim.anim)
        binding.root.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                findNavController().navigate(R.id.mainFragment)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
}