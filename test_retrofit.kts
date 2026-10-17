import okhttp3.HttpUrl

val baseUrl = HttpUrl.parse("https://generativelanguage.googleapis.com/")!!
val relativeUrl = "v1beta/models/gemini-1.5-flash:generateContent"
val resolved = baseUrl.resolve(relativeUrl)
println("Resolved URL: $resolved")
