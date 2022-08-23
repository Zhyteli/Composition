package com.zhytel.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.findNavController
import com.zhytel.composition.R
import com.zhytel.composition.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindReqiuredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.number_of_responses),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tx_your_account),
        count
    )
}
@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage_of_correct_answers),
        count
    )
}
@BindingAdapter("storePercentage")
fun bindStorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.tx_percentage_of_correct_answers),
        getPercentOfRightAnswers(gameResult)
    )
}
private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}
@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner:Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}
private fun getSmileResId(winner:Boolean): Int {
    return if (winner) {
        R.drawable.ic_qubodup_cubikopp_smilies_1
    } else {
        R.drawable.ic_qubodup_cubikopp_smilies_11
    }
}
@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean){
    textView.setTextColor(getColorByState(textView.context,enough))
}
@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean){
    val color = getColorByState(progressBar.context, enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}
private fun getColorByState(context: Context,it: Boolean): Int {
    val colorId = if (it) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context,colorId)
}
@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int){
    textView.text = number.toString()
}
//@BindingAdapter("gameResult")
//fun bindGameResult(gameResult: GameResult){
//    findNavController().navigate(
//        GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult)
//    )
//}
