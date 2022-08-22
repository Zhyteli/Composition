package com.zhytel.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.zhytel.composition.databinding.FragmentGameBinding
import com.zhytel.composition.domain.entity.GameResult
import com.zhytel.composition.domain.entity.Level

class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            GameViewModelFactory(
                args.level,
                requireActivity().application
            ))[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        parseArgs()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setOnClickListenersToOptions()
    }

    private fun setOnClickListenersToOptions(){
        for (tvOption in tvOptions){
            tvOption.setOnClickListener{
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel(){
        viewModel.question.observe(viewLifecycleOwner){
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until  tvOptions.size){
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner){
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner){
            binding.tvAnswersProgress.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner){
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner){
            binding.tvTimer.text = it
        }
        viewModel.minPresent.observe(viewLifecycleOwner){
            binding.progressBar.secondaryProgress = it
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner){
            binding.tvAnswersProgress.text = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishedFragment(it)
        }
    }

    private fun getColorByState(it: Boolean): Int {
        val colorId = if (it) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(),colorId)
    }

//    private fun parseArgs(){
//        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
//            level = it
//        }
//    }
    private fun launchGameFinishedFragment(gameResult: GameResult){
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
//            .addToBackStack(null)
//            .commit()
    findNavController().navigate(
        GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult)
    )
    }
}