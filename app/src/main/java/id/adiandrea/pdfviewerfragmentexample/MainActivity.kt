package id.adiandrea.pdfviewerfragmentexample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.pdf.viewer.fragment.PdfViewerFragment

class MainActivity : AppCompatActivity() {

    private lateinit var pdfViewerFragment: PdfViewerFragment

    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        findViewById<View>(R.id.pdf_viewer).visibility = View.VISIBLE
        pdfViewerFragment.documentUri = uri
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pdfViewerFragment = supportFragmentManager.findFragmentById(R.id.pdf_viewer) as PdfViewerFragment
        findViewById<View>(R.id.pdf_viewer).visibility = View.INVISIBLE

        findViewById<Button>(R.id.button).setOnClickListener {
            filePickerLauncher.launch("application/pdf")
        }
    }
}