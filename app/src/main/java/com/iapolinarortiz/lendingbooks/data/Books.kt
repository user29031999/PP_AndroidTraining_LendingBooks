package com.iapolinarortiz.lendingbooks.data

class Books(var name: String, var category: String) {

    override fun toString(): String {
        return name
    }

    companion object {
        const val HORROR = "Horror"
        const val NOVEL = "Novel"
        const val BIOS = "Bios"
        const val SCIFI = "Science Fiction"
    }
}