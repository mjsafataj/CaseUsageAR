package ir.topbest.objectdetection.utils

import android.content.ContentValues.TAG
import android.media.Image
import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException

@ExperimentalGetImage class TextReaderAnalyzer(
    private val textFoundListener: (String) -> Unit
) : ImageAnalysis.Analyzer {


    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image?.let { process(it, imageProxy) }
    }

    private fun process(image: Image, imageProxy: ImageProxy) {
        try {
            readTextFromImage(InputImage.fromMediaImage(image, 90), imageProxy)
        } catch (e: IOException) {
            Log.d(TAG, "Failed to load the image")
            e.printStackTrace()
        }
    }

    private fun readTextFromImage(image: InputImage, imageProxy: ImageProxy) {
        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            .process(image)
            .addOnSuccessListener { visionText ->
                processTextFromImage(visionText)
                imageProxy.close()
            }
            .addOnFailureListener { error ->
                Log.d(TAG, "Failed to process the image")
                error.printStackTrace()
                imageProxy.close()
            }
    }

    private fun processTextFromImage(visionText: Text) {
        for (block in visionText.textBlocks) {
            // You can access whole block of text using block.text
            for (line in block.lines) {
                // You can access whole line of text using line.text
                for (element in line.elements) {
                    textFoundListener(element.text)
                }
            }
        }
    }
}