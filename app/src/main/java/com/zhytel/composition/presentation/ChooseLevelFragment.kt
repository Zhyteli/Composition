package com.zhytel.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhytel.composition.R
import com.zhytel.composition.databinding.FragmentChooseLevelBinding
import com.zhytel.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            buttonLevelTest.setOnClickListener{
                launchGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener{
                launchGameFragment(Level.EASY)
            }
            buttonLevelMiddle.setOnClickListener{
                launchGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener{
                launchGameFragment(Level.HARD)
            }
        }
    }
    private fun launchGameFragment(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }
    companion object{

        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}