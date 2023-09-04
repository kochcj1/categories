# Categories App

A general purpose, Kotlin-based Android app for displaying data that's returned by virtually any API
that breaks up it data by category.

This app makes use of the [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architectural pattern.

## Example: recipes broken up by cuisine

Because this is a general purpose app, it requires a certain amount of configuration.

Start by creating a configuration file at `app/config.gradle.kts` (which is `.gitignore`d) that
looks something like this one:

```kotlin
val appName by extra("Recipes")
val chooseCategoryPrompt by extra("Choose a cuisine")
```

Next, create a file at `app/src/main/java/app/api/json/configuration/Configuration.kt` (which is
also`.gitignore`d) that looks like this:

```kotlin
package app.api.json.configuration

import app.api.json.model.items.Item
import app.api.json.model.items.Items
import com.google.gson.JsonParser
import okhttp3.Request

/**
 * The enumeration that specifies all potential categories.
 */
enum class CategoryType {
    American, Chinese, Greek, Indian, Italian, Japanese, Mexican, Thai
}

/**
 * Creates a category of the given type.
 */
class CategoryFactory {
    companion object {
        fun from(categoryType: CategoryType): Category {
            val image = when(categoryType) {
                CategoryType.American -> R.drawable.united_states
                CategoryType.Chinese -> R.drawable.china
                CategoryType.Greek -> R.drawable.greece
                CategoryType.Indian -> R.drawable.india
                CategoryType.Italian -> R.drawable.italy
                CategoryType.Japanese -> R.drawable.japan
                CategoryType.Mexican -> R.drawable.mexico
                CategoryType.Thai -> R.drawable.thailand
            }
            return Category(categoryType, image)
        }
    }
}

/**
 * Returns the object that will be used to make an HTTP request. There should be some way (e.g. a
 * query parameter) of telling the API what category of things to return.
 * 
 * Note that while the API used here is an AWS API Gateway, feel free to replace the URL and such as
 * necessary.
 */
fun getRequest(category: String): Request = Request.Builder()
    .url("https://<API Gateway ID>.execute-api.<AWS Region>.amazonaws.com/<API Gateway stage>/recipes?cuisine=$category")
    .header("x-api-key", "<API Key>")
    .build()

/**
 * Parse the HTTP response's body, returning a list of items.
 */
fun getItems(responseBody: String): Items {
    val items = JsonParser.parseString(responseBody).asJsonArray.map { jsonElement ->
        val jsonObject = jsonElement.asJsonObject
        Item(
            jsonObject["title"].asString,
            jsonObject["url"].asString,
            jsonObject["photoUrl"].asString
        )
    }
    return ArrayList(items)
}
```

https://github.com/kochcj1/json-api-viewer/assets/20493743/e3764505-da58-4c54-b661-e5a7c85948d3
