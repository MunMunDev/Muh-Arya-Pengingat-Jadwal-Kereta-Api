package com.example.muharya_pengingatjadwalkeretaapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.muharya_pengingatjadwalkeretaapi.R
import com.example.muharya_pengingatjadwalkeretaapi.data.model.PesananModel
import com.example.muharya_pengingatjadwalkeretaapi.utils.KonversiRupiah
import com.example.muharya_pengingatjadwalkeretaapi.utils.TanggalDanWaktu

class PesananAdapter(
    val arrayPesanan: ArrayList<PesananModel>,
    val listener:onClickMenuListener
): RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {

    class PesananViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val clBody : ConstraintLayout
        val tvDariStasiun: TextView
        val tvDariKota : TextView
        val tvWaktuBerangkat: TextView
        val tvSampaiStasiun : TextView
        val tvSampaiKota : TextView
        val tvJumlahTiket : TextView
//        val tvHarga : TextView
        val tvTanggal : TextView

        init {
            clBody = v.findViewById(R.id.clBody)
            tvDariStasiun = v.findViewById(R.id.tvDariStasiun)
            tvDariKota = v.findViewById(R.id.tvDariKota)
            tvWaktuBerangkat = v.findViewById(R.id.tvWaktuBerangkat)
            tvSampaiStasiun = v.findViewById(R.id.tvSampaiStasiun)
            tvSampaiKota = v.findViewById(R.id.tvSampaiKota)
            tvJumlahTiket = v.findViewById(R.id.tvJumlahTiket)
//            tvHarga = v.findViewById(R.id.tvHarga)
            tvTanggal = v.findViewById(R.id.tvTanggal)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_pesanan, parent, false)
        return PesananViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayPesanan.size
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        val valueArray = arrayPesanan[position]
        val tanggalDanWaktu = TanggalDanWaktu()

        holder.apply {
            tvDariStasiun.text = valueArray.dari_stasiun
            tvDariKota.text = valueArray.dari_kota_kab
            val arrayWaktuKeberangkatan = valueArray.waktu.split(":")
            tvWaktuBerangkat.text = "${arrayWaktuKeberangkatan[0]}:${arrayWaktuKeberangkatan[1]} Wita"
//            tvWaktuBerangkat.text = valueArray.waktu
            tvSampaiStasiun.text = valueArray.sampai_stasiun
            tvSampaiKota.text = valueArray.sampai_kota_kab
            tvJumlahTiket.text = "${valueArray.jumlah_tiket} Tiket"
//            tvHarga.text = KonversiRupiah().rupiah(valueArray.total_harga.trim().toLong())
//            tvTanggal.text = valueArray.tanggal
            tvTanggal.text = tanggalDanWaktu.konversiBulanSingkatan(valueArray.tanggal)
        }

        holder.clBody.setOnClickListener {
            listener.onClick(valueArray, it)
        }

//        if(valueArray.id_pesanan!="-"){
//            holder.tvDariStasiun.text = valueArray.dari_stasiun
//            holder.tvDariKota.text = valueArray.dari_kota
//            holder.tvWaktuBerangkat.text = valueArray.waktu
//            holder.tvSampaiStasiun.text = valueArray.sampai_stasiun
//            holder.tvSampaiKota.text = valueArray.sampai_kota
//            holder.tvJumlahTiket.text = valueArray.jumlah_tiket
//            holder.tvHarga.text = valueArray.total_harga
//            holder.tvTanggal.text = valueArray.tanggal
//
//            holder.clBody.setOnClickListener {
//                listener.onClick(valueArray)
//            }
//        }else{
//            holder.clBody.visibility = View.GONE
//        }
    }

    interface onClickMenuListener{
        fun onClick(listPesanan: PesananModel, it:View)
    }
}