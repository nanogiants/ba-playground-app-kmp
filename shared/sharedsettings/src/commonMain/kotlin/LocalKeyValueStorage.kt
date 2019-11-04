interface LocalKeyValueStorage {
    fun writeText(text: String)
    fun readText(): String
}