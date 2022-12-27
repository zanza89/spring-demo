package com.example.demo
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.atomic.AtomicLong;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

data class Greeting(val id: Long, val content: String)
data class Location(val lat: Double, val lon: Double)
data class Weather(val locationName: String, val temperature: Int, val description : String, val location: Location, val iconUrl: String, val providerUrl: String)
@RestController
class HtmlController {
	val counter = AtomicLong()
	@GetMapping("/greeting")
	fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
		Greeting(counter.incrementAndGet(), "Hello, $name")
	@GetMapping("/zeit")
	fun zeit() = java.util.Date()
	@GetMapping("/weather")
	fun weather(request: HttpServletRequest,
				@RequestParam(value = "location", defaultValue = "") location: String,
				@RequestParam(value = "lat", defaultValue = "0.0") lat: String,
				@RequestParam(value = "lon", defaultValue = "0.0") lon: String) = Weather(if (location.isEmpty()) "unknown" else location, 20, "Sonnig", Location(lat.toDouble(), lon.toDouble()), "${serverUrl(request)}/04n.png", serverUrl(request))

	private fun serverUrl(request: HttpServletRequest) = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString()
}