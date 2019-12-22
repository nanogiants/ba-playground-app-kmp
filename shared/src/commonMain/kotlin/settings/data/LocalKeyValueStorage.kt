package settings.data

interface LocalKeyValueStorage {
  fun writeText(text: String)
  fun readText(): String
}