//package citics.sharing.activity.choice
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import citics.sharing.activity.base.BaseActivity
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ChonMaCanHoActivity :
//    BaseActivity<ActivityChonmacanhoBinding, BaseViewModel>() {
//    override val viewModel: BaseViewModel by viewModels()
//    override val bindingInflater: (LayoutInflater) -> ActivityChonmacanhoBinding
//        get() = ActivityChonmacanhoBinding::inflate
//
//    companion object {
//        private const val KEY_PROJECT_ID = "KEY_PROJECT_ID"
//        private const val KEY_MA_CAN_HO = "KEY_MA_CAN_HO"
//        fun newIntent(context: Context, projectID: String, macanho: String): Intent {
//            val intent = Intent(context, ChonMaCanHoActivity::class.java)
//            intent.putExtra(KEY_PROJECT_ID, projectID)
//            intent.putExtra(KEY_MA_CAN_HO, macanho)
//            return intent
//        }
//    }
//
//    override fun onConfigUI() {
//        super.onConfigUI()
//        val macanho = intent?.getStringExtra(KEY_MA_CAN_HO) ?: ""
//        val projectID = intent?.getStringExtra(KEY_PROJECT_ID) ?: ""
////        viewModel.getCanHoDetail(projectID, macanho)
//    }
//}