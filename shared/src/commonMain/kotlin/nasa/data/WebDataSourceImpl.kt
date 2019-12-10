package nasa

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import kotlinx.serialization.json.Json

@Suppress("EXPERIMENTAL_API_USAGE")
class WebDataSourceImpl : WebDataSource {

  val baseUrl: String = "https://api.nasa.gov/planetary/"
  val pictureOfTheDayPath = "apod"
//  val apiKey = "DEMO_KEY"
  val apiKey = nasaApiKey

  override suspend fun getPictureOfTheDay(): PictureOfTheDay {

    // create http client
    val client = HttpClient()

    // configure and send http-get request
    val response = client.get<HttpResponse>(HttpRequestBuilder().apply {
      url("$baseUrl/$pictureOfTheDayPath")
      parameter("api_key", apiKey)
    })

    // close underlying engine
    client.close()

    // parse http jsonBody to web entity
    val entity = Json.parse(PictureOfTheDayWebEntity.serializer(), response.readText())

    // transform web entity to model
    return PictureOfTheDay(entity.date, entity.explanation, entity.title, entity.url, entity.media_type)
  }
}

// Actually we're able to just return the get()-call and Ktor's JsonFeature will automatically do the
// JSON parsing for us. However, this currently doesn't work with Kotlin/Native as it doesn't support reflection
// and we have to manually use PopularMoviesEntity.serializer()

//private val client = HttpClient(clientEngine) {
//    install(JsonFeature) {
//        serializer = KotlinxSerializer()
//    }
//}