package nasa.data

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json
import nasa.domain.PictureOfTheDay
import nasa.domain.PictureOfTheDayWebEntity
import nasa.nasa.data.WebDataUtils

@Suppress("EXPERIMENTAL_API_USAGE")
class WebDataSourceImpl : WebDataSource {

//  private val apiKey = nasaApiKey
  private val apiKey = "DEMO_KEY"

  override suspend fun getPictureOfTheDay(): PictureOfTheDay {

    // create http client
    val client = HttpClient()

    // configure and send http-get request
    val httpStatement = client.get<HttpStatement>(HttpRequestBuilder().apply {
      url(WebDataUtils.NasaApi.pictureOfTheDayUrl)
      parameter("api_key", apiKey)
    })
    val response = httpStatement.execute()

    // close underlying engine
    client.close()

    // parse http jsonBody to web entity
    val entity =
      Json.parse(PictureOfTheDayWebEntity.serializer(), response.readText())

    var showImage = entity.media_type == WebDataUtils.mediaTypeImage
    if(entity.copyright != "") {
      showImage = false // do not show non public images
    }

    // transform web entity to model
    return PictureOfTheDay(
      entity.date,
      entity.explanation,
      entity.title,
      entity.url,
      showImage
    )
  }
}
