package com.example.horoscopeapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscopeapp.databinding.FragmentHoroscopeBinding
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Virgo
import com.example.horoscopeapp.domain.model.HoroscopeModel
import com.example.horoscopeapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initUIState()
        initRV()
    }

    private fun initRV() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {

            val type: HoroscopeModel = when (it) {

                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }

            findNavController().navigate(

                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
            /*
                        Toast.makeText(context, getString(it.name), Toast.LENGTH_LONG).show()
            */
        })

        binding.rvFragmentHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }

        /*binding.rvFragmentHoroscope.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentHoroscope.adapter = horoscopeAdapter*/
    }

    private fun initUIState() {

        // coroutine special for fragments
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {

                    // change in horoscope

                    horoscopeAdapter.updateList(it)

                }
            }
        }
    }

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


}