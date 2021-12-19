package id.ac.ukdw.final_71190506

data class Penduduk (
    val id: String,
    val nik:String,
    val nama:String,
    val umur:Int,
    val alamat:String) {

    constructor(): this("","","",0,""){

    }
}