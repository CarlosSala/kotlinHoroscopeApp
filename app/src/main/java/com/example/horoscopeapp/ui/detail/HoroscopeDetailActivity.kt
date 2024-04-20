package com.example.horoscopeapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscopeapp.R
import com.example.horoscopeapp.databinding.ActivityHoroscopeDetailBinding
import com.example.horoscopeapp.domain.model.HoroscopeModel.Aquarius
import com.example.horoscopeapp.domain.model.HoroscopeModel.Aries
import com.example.horoscopeapp.domain.model.HoroscopeModel.Cancer
import com.example.horoscopeapp.domain.model.HoroscopeModel.Capricorn
import com.example.horoscopeapp.domain.model.HoroscopeModel.Gemini
import com.example.horoscopeapp.domain.model.HoroscopeModel.Leo
import com.example.horoscopeapp.domain.model.HoroscopeModel.Libra
import com.example.horoscopeapp.domain.model.HoroscopeModel.Pisces
import com.example.horoscopeapp.domain.model.HoroscopeModel.Sagittarius
import com.example.horoscopeapp.domain.model.HoroscopeModel.Scorpio
import com.example.horoscopeapp.domain.model.HoroscopeModel.Taurus
import com.example.horoscopeapp.domain.model.HoroscopeModel.Virgo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        horoscopeDetailViewModel.getHoroscope(args.type)

        initUI()

    }

    private fun initUI() {
        initUIState()
        initListener()
    }

    private fun initListener() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initUIState() {

        // get access to viewModel with state flow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {

                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                        else -> {}
                    }
                }
            }
        }
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun successState(state: HoroscopeDetailState.Success) {

        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction


        val image = when (state.horoscopeModel) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Gemini -> R.drawable.detail_gemini
            Cancer -> R.drawable.detail_cancer
            Leo -> R.drawable.detail_leo
            Virgo -> R.drawable.detail_virgo
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Sagittarius -> R.drawable.detail_sagittarius
            Capricorn -> R.drawable.detail_capricorn
            Aquarius -> R.drawable.detail_aquarius
            Pisces -> R.drawable.detail_pisces
        }

        binding.ivDetail.setImageResource(image)
    }
}
