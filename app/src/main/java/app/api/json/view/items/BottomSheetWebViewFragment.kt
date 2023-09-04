package app.api.json.view.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import app.api.json.R
import app.api.json.databinding.BottomSheetWebViewFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetWebViewFragment(private val url: String) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BottomSheetWebViewFragmentBinding.inflate(layoutInflater)
        val webView: WebView = binding.webView
        webView.loadUrl(url)
        initializeToolbar(binding)
        return binding.root
    }

    private fun initializeToolbar(binding: BottomSheetWebViewFragmentBinding) {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.baseline_close_24)
            setNavigationOnClickListener {
                // Close the bottom sheet when the close button is clicked
                dismiss()
            }
        }
    }
}
