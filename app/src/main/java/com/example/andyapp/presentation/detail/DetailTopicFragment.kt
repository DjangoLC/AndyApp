package com.example.andyapp.presentation.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.andyapp.R
import com.example.andyapp.data.models.Topic
import com.example.andyapp.databinding.FragmentDetailTopicBinding
import com.pdftron.pdf.PDFViewCtrl
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DetailTopicFragment : Fragment(R.layout.fragment_detail_topic) {

    private lateinit var binding: FragmentDetailTopicBinding
    private val args: DetailTopicFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailTopicBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPdf()
        binding.btnTakeQuiz.setOnClickListener {
            val action = DetailTopicFragmentDirections.actionDetailTopicFragmentToQuizFragment(args.topic)
            findNavController().navigate(action)
        }
    }

    private fun loadPdf() {
        val file = File.createTempFile("pdf", "")
        resources.openRawResource(getFile(args.topic)).use {
            copyInputStreamToFile(it, file)
        }.also {
            binding.pdfviewCtrl.openPDFUri(Uri.fromFile(file), null)
        }
    }

    private fun getFile(topic: Topic): Int {
        return when (topic.title) {
            "Supervisor de Construcción." -> {
                R.raw.supervisor
            }

            "Entrevista para Auxiliar de Recursos Humanos." -> {
                R.raw.aux_rh
            }

            "Entrevista para Ejecutivo de Ventas." -> {
                R.raw.coordinador_ventas
            }

            "Operario Eléctrico." -> {
                R.raw.operario
            }
            else -> {
                R.raw.operario
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.pdfviewCtrl.closeDoc()
    }

    private fun copyInputStreamToFile(inputStream: InputStream, file: File) {
        FileOutputStream(file, false).use { outputStream ->
            var read: Int
            val bytes = ByteArray(DEFAULT_BUFFER_SIZE)
            while (inputStream.read(bytes).also { read = it } != -1) {
                outputStream.write(bytes, 0, read)
            }
        }
    }

}