//import QuizListAdapter.MyViewHolder
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView.Adapter
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//import com.example.quizapp.QuizActivity
//import com.example.quizapp.QuizModel
//import com.example.quizapp.databinding.QuizItemRecyclerviewBinding
//
//class QuizListAdapter(private val quizModelList: List<QuizModel>):
//    Adapter<MyViewHolder>() {
//    class MyViewHolder(private val binding: QuizItemRecyclerviewBinding) : ViewHolder(binding.root){
//        fun bind(model: QuizModel){
//            binding.apply {
//                quizTitleText.text = model.title
//                quizSubtitleText.text = model.subtitle
//                quizTime.text = model.time + " min"
//                root.setOnClickListener {
//                    val intent = Intent(root.context, QuizActivity::class.java)
//                    QuizActivity.questionModelList = model.questionList
//                    QuizActivity.time = model.time
//                    root.context.startActivity(intent)
//                }
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = QuizItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//       return MyViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return quizModelList.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(quizModelList[position])
//    }
//}

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizActivity
import com.example.quizapp.QuizModel
import com.example.quizapp.databinding.QuizItemRecyclerviewBinding

class QuizListAdapter(private val quizModelList: List<QuizModel>) : RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: QuizItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quizModel: QuizModel) {
            binding.apply {
                quizTitleText.text = quizModel.title
                quizSubtitleText.text = quizModel.subtitle
                quizTime.text = "${quizModel.time} min"
                root.setOnClickListener {
                    val context = root.context
                    val intent = Intent(context, QuizActivity::class.java).apply {
                        QuizActivity.questionModelList = quizModel.questionList
                        QuizActivity.time = quizModel.time
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = QuizItemRecyclerviewBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = quizModelList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}

