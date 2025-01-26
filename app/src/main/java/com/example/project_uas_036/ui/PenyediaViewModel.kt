package com.example.project_uas_036.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project_uas_036.PerpusApp
import com.example.project_uas_036.ui.viewmodel.Buku.DetailBukuViewModel
import com.example.project_uas_036.ui.viewmodel.Buku.HomeBukuViewModel
import com.example.project_uas_036.ui.viewmodel.Buku.InsertBukuViewModel
import com.example.project_uas_036.ui.viewmodel.Buku.UpdateBukuViewModel
import com.example.project_uas_036.ui.viewmodel.Kategori.DetailKategoriViewModel
import com.example.project_uas_036.ui.viewmodel.Kategori.HomeKategoriViewModel
import com.example.project_uas_036.ui.viewmodel.Kategori.InsertKategoriViewModel
import com.example.project_uas_036.ui.viewmodel.Kategori.UpdateKategoriViewModel

object PenyediaViewModel {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory = viewModelFactory {
        initializer { HomeBukuViewModel(aplikasiKontak().bukcontainer.kontakRepository) }
        initializer { InsertBukuViewModel(aplikasiKontak().bukcontainer.kontakRepository) }
        initializer { DetailBukuViewModel(createSavedStateHandle(),aplikasiKontak().bukcontainer.kontakRepository) }
        initializer { UpdateBukuViewModel(createSavedStateHandle(),aplikasiKontak().bukcontainer.kontakRepository) }

        initializer { HomeKategoriViewModel(aplikasiKontak().katcontainer.kontakKategoriRepository) }
        initializer { InsertKategoriViewModel(aplikasiKontak().katcontainer.kontakKategoriRepository) }
        initializer { DetailKategoriViewModel(createSavedStateHandle(),aplikasiKontak().katcontainer.kontakKategoriRepository) }
        initializer {
            Log.d("PenyediaViewModel", "Kategori Repository: ${aplikasiKontak().katcontainer.kontakKategoriRepository}")
            UpdateKategoriViewModel(createSavedStateHandle(),aplikasiKontak().katcontainer.kontakKategoriRepository) }
    }
}
fun CreationExtras.aplikasiKontak(): PerpusApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpusApp)

