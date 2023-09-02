# JSON API Viewer

A general purpose, Kotlin-based Android app for displaying the JSON array returned by a given API as a list of clickable card views. This app makes use of the [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architectural pattern.

## Example: JSONPlaceholder's `/photos` Endpoint

The URL `https://jsonplaceholder.typicode.com/photos` returns a JSON array of objects that looks like this:

```json
[
  {
    "albumId": 1,
    "id": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952",
    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
  },
  {
    "albumId": 1,
    "id": 2,
    "title": "reprehenderit est deserunt velit ipsam",
    "url": "https://via.placeholder.com/600/771796",
    "thumbnailUrl": "https://via.placeholder.com/150/771796"
  },
  {
    "albumId": 1,
    "id": 3,
    "title": "officia porro iure quia iusto qui ipsa ut modi",
    "url": "https://via.placeholder.com/600/24f355",
    "thumbnailUrl": "https://via.placeholder.com/150/24f355"
  },
...
]
```

The configuration for this example can be found at `examples/jsonplaceholder_photos.gradle.kts`, but the contents of this (or another) config file should be copied to `app/config.gradle.kts` before building the application.

For this particular use case, the configuration looks like this:
```kotlin
val appName by extra("JSONPlaceholder Photos")
val apiUrl by extra("https://jsonplaceholder.typicode.com/photos")
val titleField by extra("title")
val urlField by extra("url")
val thumbnailUrlField by extra("thumbnailUrl")
```

Variables `appName`, `apiUrl`, `titleField`, `urlField`, and `thumbnailUrlField` are imported by `app/build.gradle` and thereby made available to the app.

`appName` provides the name of the app to display in the app bar, `apiUrl` provides the URL to fetch data from, while `titleField`, `urlField`, and `thumbnailUrlField` are the JSON object fields to use for each card view's title, URL (the URL to navigate to when the card is clicked), and thumbnail URL.

https://github.com/kochcj1/json-api-viewer/assets/20493743/ac18f4c7-8719-42a0-af9b-c4c2c9050726
