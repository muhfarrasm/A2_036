package com.example.project_uas_036.ui.view.Buku

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_uas_036.model.Buku
import com.example.project_uas_036.ui.PenyediaViewModel
import com.example.project_uas_036.ui.customwidget.CoustumeTopAppBar
import com.example.project_uas_036.ui.navigation.DestinasiNavigasi
import com.example.project_uas_036.ui.viewmodel.Buku.DetailBukuViewModel
import com.example.project_uas_036.ui.viewmodel.Buku.DetailUiState

object DestinasiDetailBuku : DestinasiNavigasi {
    override val route = "detailBuku"
    override val titleRes = "Detail Buku"
    const val id_buku = "idBuku"
    val routesWithArg = "$route/{$id_buku}"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBukuViewScreen(
    navigateBack: () -> Unit,
    navigateToItemUpdate: () -> Unit,
    modifier: Modifier = Modifier,

    viewModel: DetailBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CoustumeTopAppBar(
                title = DestinasiDetailBuku.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getMahasiswabyNim()
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.d("DetailBukuViewScreen", "FloatingActionButton clicked, navigating to update screen")
                    navigateToItemUpdate()
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kontak"
                )
            }
        }
    ) { innerPadding ->
        DetailBukuStatus(
            modifier = Modifier.padding(innerPadding),
            detailUiState = viewModel.bukuDetailState,
            retryAction = { viewModel.getMahasiswabyNim() },

        )
    }
}

@Composable
fun DetailBukuStatus(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,

) {
    when (detailUiState) {
        is DetailUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is DetailUiState.Success -> {
            if (detailUiState.buku.id_buku.isEmpty()) {
                Box(
                    modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) { Text("Data tidak ditemukan.") }
            } else {
                ItemDetailBuku(
                    buku = detailUiState.buku,
                    modifier = modifier.fillMaxWidth(),

                )
            }
        }
        is DetailUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailBuku(
    modifier: Modifier = Modifier,
    buku: Buku,

) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMhs(judul = "Id Buku", isinya = buku.id_buku)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMhs(judul = "Nama", isinya = buku.nama_buku)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMhs(judul = "Deskripsi", isinya = buku.deskripsi_buku)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMhs(judul = "Tanggal Terbit", isinya = buku.tanggal_terbit)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMhs(judul = "Id Kategori", isinya = buku.id_kategori)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMhs(judul = "Id Penerbit", isinya = buku.id_penerbit)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMhs(judul = "Id Penulis", isinya = buku.id_penulis)


        }
    }
}

@Composable
fun ComponentDetailMhs(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = judul,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

