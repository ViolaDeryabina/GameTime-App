package com.example.gametime_app.presentation.components

import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gametime_app.R
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun PDFViewer(assetId: Int) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Asset(assetId)
    )

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier.fillMaxSize()
    )
}