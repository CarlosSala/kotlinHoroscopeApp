package com.example.horoscopeapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.horoscopeapp.R
import com.example.horoscopeapp.databinding.FragmentLuckBinding
import com.example.horoscopeapp.ui.core.listeners.OnSwipeTouchListener
import com.example.horoscopeapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {

        val currentLuck = randomCardProvider.getLucky()
        currentLuck?.let { luck ->

            val currentPrediction = getString(luck.text)
            binding.tvPredictionLuckFragment.text = currentPrediction
            binding.ivBigLuckFragment.setImageResource(luck.image)
            binding.tvShare.setOnClickListener { shareResult(currentPrediction) }
        }
    }

    private fun shareResult(currentPrediction: String) {

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, currentPrediction)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {

        binding.ivRouletteLuckFragment.setOnTouchListener(
            object : OnSwipeTouchListener(requireContext()) {

                override fun onSwipeLeft() = spinRoulette()

                override fun onSwipeRight() = spinRoulette()
            })
    }


    private fun spinRoulette() {

        val random = Random()
        val degrees = random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(
            binding.ivRouletteLuckFragment,
            View.ROTATION, 0f, degrees.toFloat()
        )

        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {

        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.ivCardBackSmallLuckFragment.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                growCard()
                binding.ivCardBackSmallLuckFragment.visibility = View.VISIBLE
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })

        binding.ivCardBackSmallLuckFragment.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                binding.ivCardBackSmallLuckFragment.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                binding.ivCardBackSmallLuckFragment.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })

        binding.ivCardBackSmallLuckFragment.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {

        // opacity
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                binding.clPreviewLuckFragment.isVisible = false
                binding.clPredictionLuckFragment.isVisible = true
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })

        binding.clPreviewLuckFragment.startAnimation(disappearAnimation)
        binding.clPredictionLuckFragment.startAnimation(appearAnimation)
    }
}