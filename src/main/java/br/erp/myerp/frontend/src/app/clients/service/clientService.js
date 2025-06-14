import axios from "axios";

export async function getClients() {
  try {
    const response = await axios
      .get("http://localhost:8080/api/client", {
        headers: {
          Authorization: `eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJNeSBFUlAiLCJzdWIiOiJKV1QgVG9rZW4iLCJ1c2VybmFtZSI6InRlc3RlQGVtYWlsLmNvbSIsImF1dGhvcml0aWVzIjoiTjIiLCJpYXQiOjE3NDk4NTY1MDcsImV4cCI6MTc0OTg4NjUwN30.8ETvlRKtDiy-kor5Aesn0oT9n-W7xfTLq-Y3ZyX6EYFSplcdURuUIUc1tuwSZz4jn71tNVn3Aos94rrha6ohaQ`,
        },
      });

    return response.data;
  } catch (error) {
    console.error("Erro ao buscar clientes:", error);
    return [];
  }
}
