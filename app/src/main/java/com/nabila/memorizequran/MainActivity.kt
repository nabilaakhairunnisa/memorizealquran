package com.nabila.memorizequran

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kanyidev.searchable_dropdown.SearchableExpandedDropDownMenuMaterial3
import com.nabila.memorizequran.data.local.entity.AyatEntity
import com.nabila.memorizequran.data.local.entity.SurahEntity
import com.nabila.memorizequran.ui.theme.MemorizeQuranTheme
import com.nabila.memorizequran.ui.theme.grey
import com.nabila.memorizequran.ui.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MemorizeQuranTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier.padding(innerPadding).fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        MainActivity(
                            context = this@MainActivity,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainActivity(
    context: Context,
    modifier: Modifier = Modifier,
    viewModel: MyViewModel = hiltViewModel()
) {
    val surahList by viewModel.surahList.collectAsState() //display surah list
    val detailSurah by viewModel.detailSurah.collectAsState() //data for display detail surah
    val toastMessage by viewModel.toastMessage.collectAsState() //get message
    val selectedSurah = remember { mutableStateOf<SurahEntity?>(null) } //save the selected surah
    val jumlahAyat = selectedSurah.value?.jumlahAyat ?: 0 //data for total ayat
    val ayatList = (1..jumlahAyat).map { it.toString() } //data for display number of ayat
    val ayatAwal = remember { mutableStateOf("") } //for save selected first ayat
    val ayatAkhir = remember { mutableStateOf("") } //for save selected last ayat
    val awal = ayatAwal.value.toIntOrNull() ?: 1 //convert selected first ayat to int
    val akhir = ayatAkhir.value.toIntOrNull() ?: awal //convert selected last ayat to int

    //data for display selected ayat in lazy column
    val listAyat = detailSurah?.ayat?.filter {
        it.nomorAyat in awal..akhir
    } ?: emptyList()

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column (
            modifier = Modifier
                .matchParentSize()
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ){

            GreyText("Pilih Surat", Modifier)
            MyDropDown(
                itemList = surahList.map { it.namaLatin },
                modifier = Modifier.fillMaxWidth(),
                onItemSelected = { nama ->
                    val surah = surahList.find { it.namaLatin == nama }
                    selectedSurah.value = surah
                    surah?.let { viewModel.getDetailSurah(it.nomor) }
                }
            )
            Row(Modifier.fillMaxWidth()) {
                GreyText("Ayat Awal", Modifier.weight(1f))
                GreyText("Ayat Akhir", Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth()) {
                Box(Modifier.weight(1f)) {
                    MyDropDown(
                        itemList = ayatList,
                        modifier = Modifier,
                        onItemSelected = { ayatAwal.value = it }
                    )
                }
                Box(Modifier.weight(1f)) {
                    MyDropDown(
                        itemList = ayatList,
                        modifier = Modifier,
                        onItemSelected = { ayatAkhir.value = it }
                    )
                }
            }
            Button(
                onClick = {
                    //this object will used to call function saveListAyat from view model
                    val listAyatEntity = listAyat.map { selectedAyat ->
                        //convert selected list ayat to AyatEntity
                        AyatEntity(
                            nomorAyat = selectedAyat.nomorAyat,
                            teksArab = selectedAyat.teksArab,
                            teksIndonesia = selectedAyat.teksIndonesia
                        )
                    }
                    viewModel.saveListAyatToDb(listAyatEntity)
                    viewModel.resetToastMessage()
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
            ) {
                Text("Simpan Ayat")
            }
        }
    }
}

@Composable
fun MyDropDown(
    itemList: List<String>,
    modifier: Modifier,
    onItemSelected: (String) -> Unit = {}
) {
    SearchableExpandedDropDownMenuMaterial3(
        listOfItems = itemList,
        modifier = modifier.padding(5.dp),
        onDropDownItemSelected = { selected ->
            onItemSelected(selected)
        },
        dropdownItem = { name -> Text(name) }
    )
}

@Composable
fun GreyText(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = grey,
        modifier = modifier.padding(
            horizontal = 10.dp,
            vertical = 5.dp
        )
    )
}