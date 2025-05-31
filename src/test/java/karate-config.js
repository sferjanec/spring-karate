function fn() {
  var config = {};
  config.baseUrl = "http://localhost:8080"; // Ensure this matches your external JaCoCo-instrumented server
  karate.configure("baseUrl", config.baseUrl);
  return config;
}
